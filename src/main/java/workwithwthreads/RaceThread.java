package workwithwthreads;

import java.util.Random;
import java.util.concurrent.Callable;

public class RaceThread implements Callable<Runner> {

    private final String runnerName;
    private final Race race = Race.getInstance();
    private int time = 0;

    public RaceThread(String name){
        runnerName = name;
    }

    @Override
    public Runner call() {
        Random random = new Random();
        Runner runner = new Runner(runnerName,random.nextInt(12) + 4, Race.getRaceLength(), time);

        while(!runner.isDone()){
            if(Thread.currentThread().isInterrupted()){
                System.out.println(this + " interrupted!");
                return null;
            }
            try{
                Thread.sleep(300L * runner.getRunnerPace());
                time += runner.getRunnerPace();
            }catch (InterruptedException e){
                return null;
            }
            runner.setDistanceToFinish(runner.getDistanceToFinish() - 1000);
        }
        runner.setRunnerTime(time);
        return runner;
    }

    public Thread.State getStatus(){
        return Thread.currentThread().getState();
    }

    @Override
    public String toString() {
        return runnerName;
    }
}
