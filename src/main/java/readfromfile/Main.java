package readfromfile;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        int numberOfThreads = 0;
        try {
            numberOfThreads = Integer.parseInt(args[0]);
        } catch (NumberFormatException numberFormatException) {
            numberFormatException.printStackTrace();
        }
        ExecutorService exec = Executors.newFixedThreadPool(numberOfThreads);

        for (int i = 0; i < numberOfThreads; i++){
            exec.execute(new ReadFileThread());
        }
        exec.shutdown();
    }
}
