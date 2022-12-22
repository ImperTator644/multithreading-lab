package authorwriter;

import java.io.IOException;
import java.util.Scanner;

public class AuthorThread extends Thread {

    private TextService txt;
    Scanner scanner = new Scanner(System.in);

    public AuthorThread(TextService txt) {
        this.txt = txt;
    }


    public void run() {

        String tekst = scanner.nextLine();
        while (!tekst.equals("X")) {
            try {
                txt.setTextToWrite(tekst);
            } catch (IOException e) {
                e.printStackTrace();
            }
            tekst = scanner.nextLine();
        }
        try {
            scanner.close();
            System.out.println("Exit");
            txt.setTextToWrite("X");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
