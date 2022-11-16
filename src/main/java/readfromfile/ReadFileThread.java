package readfromfile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFileThread implements Runnable{

    private Scanner scanner;
    private static final String FILE_NAME = "src/main/resources/plik_tekstowy.txt";

    @Override
    public void run() {
        try {
            scanner = new Scanner(new File(FILE_NAME));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
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
        }
        scanner.close();
    }
}
