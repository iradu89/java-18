import java.util.Random;

enum TicketType {
    //could have also used their order in the enum for the priority, but it is easier to read this way
    //priorities are arbitrary
    FREE_PASS(1),
    ONE_DAY(3),
    FULL(6),
    FULL_VIP(10),
    ONE_DAY_VIP(10);

    private int priority; //used for the threads priority

    //constructor
    TicketType(final int priority){
        this.priority = priority;
    }

    //generating a random ticket type
    public static TicketType getRandomTicketType(){
        return values()[new Random().nextInt(values().length)];
    }

    public int getPriority(){
        return this.priority;
    }
}
