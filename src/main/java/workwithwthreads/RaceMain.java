package workwithwthreads;


import java.util.*;
import java.util.concurrent.*;

public class RaceMain {
    private static final List<String> names = new ArrayList<>(Arrays.asList("Konrad", "Lukasz", "Kasia", "Patryk"));

    public static void main(String[] args) {

        boolean done = false;
        Scanner scanner = new Scanner(System.in);
        ArrayList<RaceThread> arrayList = new ArrayList<>();
        ArrayList<Future<Runner>> arrayListFuture = new ArrayList<>();
        ExecutorService exec = Executors.newFixedThreadPool(names.size());

        for(String name : names){
            arrayList.add(new RaceThread(name));
        }
        for(RaceThread r : arrayList){
            arrayListFuture.add(exec.submit(r));
            System.out.println(r);
        }
        exec.shutdown();

        int answer,i = 0, iter = 1;

        while(!done){
            for(Future<Runner> r : arrayListFuture){
                if(r.isDone())iter++;
            }
            if(iter == names.size())break;
            else iter = 0;
            System.out.println("1 - check status\n" +
                    "2 - disqualify runner (cancel thread)\n" +
                    "3 - check runner's time (if done)\n" +
                    "4 - wait for the race to end");
            answer = scanner.nextInt();
            if(answer!=4) {
                System.out.println("Which Runner? (from 1 to " + names.size() +")");
                i = scanner.nextInt() - 1;
            }
            switch (answer) {
                case 1:
                    System.out.println(arrayList.get(i).getStatus());
                    break;
                case 2:
                    if(!arrayListFuture.get(i).isDone())arrayListFuture.get(i).cancel(true);
                    else System.out.println("Runner have already finished the race!");
                    break;
                case 3:
                    if(arrayListFuture.get(i).isDone()){
                        try {
                            System.out.println("Runner's time : " + arrayListFuture.get(i).get().getRunnerTime() + "min");
                        } catch (InterruptedException | ExecutionException e) {
                            throw new RuntimeException(e);
                        }
                    }else System.out.println("Runner haven't finish yet");
                    break;
                default:
                    done = true;
                    break;
            }
        }
        scanner.close();

        try{
            exec.awaitTermination(100,TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for(Future<Runner> r : arrayListFuture){
            try {
                if (r.isCancelled()) System.out.println("Runner disqualified! (Thread Cancelled!) " + r);
                else if (r.isDone()) System.out.println("Runner " + r.get().getRunnerName() +
                        " finished in time: " + r.get().getRunnerTime() + "min " + r);
                else System.out.println("Runner have not finish yet");
            } catch (ExecutionException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Executor Service Terminated: " + exec.isTerminated());
    }
}
