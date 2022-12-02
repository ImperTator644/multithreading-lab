package raceCondition;

import lombok.Getter;

@Getter
public class Race2 implements Runnable{
    private int value = 0;


    public void increment() {
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        value++;
    }

    public void decrement() {
        value--;
    }


    @Override
    public void run() {
        synchronized (this){
        this.increment();
        System.out.println("Value after increment "
                + Thread.currentThread().getName() + " " + this.getValue());
        this.decrement();
        System.out.println("Value after decrement "
                + Thread.currentThread().getName() + " " + this.getValue());
        }
    }
}
