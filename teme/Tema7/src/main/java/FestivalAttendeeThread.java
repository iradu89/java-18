import java.util.Random;

class FestivalAttendeeThread extends Thread {
    private TicketType ticketType;
    private FestivalGate gate;

    public FestivalAttendeeThread(TicketType ticketType, FestivalGate gate) {
        this.ticketType = ticketType;
        this.gate = gate;
    }

    @Override
    public void run() {
        try {
            /*
            Sleep is placed after the person entering the gate because the priority is easier to see.
            if sleep is placed before entering the gate, the attendee will still enter according to his priority
            however if a lower priority ticket takes a lower amount of time to enter, then he will enter before the higher priority one
            */
            gate.personEntersThroughGate(this);
            Thread.sleep(new Random().nextInt(1500));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //for testing
    @Override
    public String toString() {
        return "FestivalAttendeeThread{" +
                "ticketType=" + ticketType +
                " Priority " + this.getPriority() +
                ", gate=" + gate +
                '}';
    }

    public TicketType getTicketType() {
        return ticketType;
    }
}
