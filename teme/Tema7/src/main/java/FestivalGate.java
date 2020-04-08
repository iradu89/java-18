import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;

class FestivalGate {
    private final List<FestivalAttendeeThread> festivalAttendees;
    //a queue which orders the elements in descending order based on the thread priority
    private final PriorityBlockingQueue<FestivalAttendeeThread> priorityQueue =
            new PriorityBlockingQueue<>(100, Comparator.comparing((FestivalAttendeeThread festivalAttendeeThread) ->
                    festivalAttendeeThread.getTicketType().getPriority()).reversed());

    public FestivalGate() {
        //the list is already synchronized so no need to add additional synchronized blocks
        festivalAttendees = Collections.synchronizedList(new LinkedList<>());
    }

    public void personEntersThroughGate(FestivalAttendeeThread festivalAttendeeThread) {
        festivalAttendees.add(festivalAttendeeThread);
    }

    public List<FestivalAttendeeThread> getFestivalAttendees() {
        return festivalAttendees;
    }

    /*
    10 festival attendees enter each second in descending order
    this means festivalAttendeeThreads with higher priority will enter first
     */
    @SuppressWarnings("InfiniteLoopStatement") //for the infinite loops
    public void openPriorityGate() {
        ExecutorService priorityPoolExecutor = Executors.newFixedThreadPool(20); //20 lanes available in the gate
        ExecutorService priorityScheduler = Executors.newSingleThreadExecutor(); //handles the priorityQueue
        ExecutorService festivalAttendeesGenerator = Executors.newSingleThreadExecutor(); //generates random attendees

        festivalAttendeesGenerator.execute(() -> {
            while (true) {
                try {
                    synchronized (priorityQueue) {
                        //fill up the priorityQueue with 10 festivalAttendees
                        for (int i = 0; i < 10; i++) {
                            //generate random festivalAttendee
                            FestivalAttendeeThread festivalAttendee = new FestivalAttendeeThread(TicketType.getRandomTicketType(), this);
                            //setting the thread priority based on the ticket
                            festivalAttendee.setPriority(festivalAttendee.getTicketType().getPriority());
                            priorityQueue.add(festivalAttendee);
                        }
                        Thread.sleep(1000); // wait 1 second between waves of 10 attendees
                        priorityQueue.wait(); //releases the lock on priorityQueue
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        priorityScheduler.execute(() -> {
            while (true) {
                synchronized (priorityQueue) {
                    try {
                        if (!priorityQueue.isEmpty()) {
                            //keeps taking the first element of the priorityQueue until it is empty
                            //the priorityPoolExecutor executes the thread sometime in the future(when a thread is free out of the 20 available)
                            priorityPoolExecutor.execute(priorityQueue.take());
                        } else {
                            priorityQueue.notify(); //notifies the AttendeeGenerator that it can wake up
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
