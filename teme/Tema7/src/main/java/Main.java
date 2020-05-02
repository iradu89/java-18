public class Main {
    public static void main(String[] args) {
        FestivalGate gate = new FestivalGate();
        FestivalStatisticsThread statsThread = new FestivalStatisticsThread(gate);
        new Thread(statsThread).start();
        gate.openPriorityGate();
    }
}
