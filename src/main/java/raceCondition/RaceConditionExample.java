package raceCondition;

public class RaceConditionExample {
    public static void main(String[] args) {
        Race2 counter = new Race2();
        Thread t1 = new Thread(counter, "Thread1");
        Thread t2 = new Thread(counter, "Thread2");
        Thread t3 = new Thread(counter, "Thread3");
        t1.start();
        t2.start();
        t3.start();

    }
}
