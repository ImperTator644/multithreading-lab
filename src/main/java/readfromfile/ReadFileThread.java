package readfromfile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class ReadFileThread implements Runnable{

    private Scanner scanner;
    private static final String FILE_NAME = "src/main/resources/plik_tekstowy.txt";

    @Override
    public void run() {
        openScanner();
        readFile();
        scanner.close();
    }

    private void readFile() {
        int counter = 0;
        while(scanner.hasNext()){
            if(counter<2){
                System.out.println(Thread.currentThread().getName() + "    " + scanner.nextLine());
                counter++;
            }
            else{
                counter = 0;
                Thread.yield();
            }
            try {
                Thread.sleep(new Random().nextInt(1000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void openScanner() {
        try {
            scanner = new Scanner(new File(FILE_NAME));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
