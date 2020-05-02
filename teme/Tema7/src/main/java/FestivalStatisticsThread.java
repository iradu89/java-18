import java.util.LinkedList;
import java.util.List;

class FestivalStatisticsThread implements Runnable {
    private FestivalGate gate;
    private List<FestivalAttendeeThread> copyOfAttendeeList; //helper variable

    public FestivalStatisticsThread(FestivalGate gate) {
        this.gate = gate;
    }

    @Override
    @SuppressWarnings("InfiniteLoopStatement") //for the infinite loop
    public void run() {
        while (true) {
            if (!gate.getFestivalAttendees().isEmpty()) { //won't read data if it is empty
                try {
                    Thread.sleep(5000);
                    /*
                    Making a copy of the attendee list instead of query-ing it several times
                    because it is more efficient accessing the list only once
                    */
                    copyOfAttendeeList = new LinkedList<>(gate.getFestivalAttendees());
                    System.out.println(getPeopleEntered() + " people have entered.");
                    System.out.println(getNumberOfFullTickets() + " people have full tickets.");
                    System.out.println(getNumberOfFreePasses() + " people have free passes.");
                    System.out.println(getNumberOfFullVipPasses() + " people have VIP passes.");
                    System.out.println(getNumberOfOneDayPasses() + " people have one-day passes.");
                    System.out.println(getNumberOfOneDayVipPasses() + " people have one-day VIP passes.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private int getPeopleEntered() {
        return gate.getFestivalAttendees().size();
    }

    private int getNumberOfFullTickets() {
        return filterTheList(TicketType.FULL);
    }

    private int getNumberOfFreePasses() {
        return filterTheList(TicketType.FREE_PASS);
    }

    private int getNumberOfFullVipPasses() {
        return filterTheList(TicketType.FULL_VIP);
    }

    private int getNumberOfOneDayPasses() {
        return filterTheList(TicketType.ONE_DAY);
    }

    private int getNumberOfOneDayVipPasses() {
        return filterTheList(TicketType.ONE_DAY_VIP);
    }

    /*
    Even though the list was synchronized before, it is better this way with the copy
    because there was a very small risk of the list being updated between each number of tickets print
    Could have synchronized the whole block in order to avoid this, however that means all the other threads
    would have to wait for this one to finish accessing & processing the list 6 times.
     */
    private int filterTheList(TicketType ticketType) {
        return (int) copyOfAttendeeList.stream()
                .filter(ticket -> ticket.getTicketType().equals(ticketType))
                .count();
    }
}
