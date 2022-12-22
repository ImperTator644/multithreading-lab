package authorwriter;

import java.io.BufferedReader;
import java.io.IOException;

public class WriterThread extends Thread {

    private TextService txt;
    private String txtToWrite = "";

    public WriterThread(TextService txt) {
        this.txt = txt;
    }

    public void run() {

        try {
            txtToWrite = txt.getTextToWrite();
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (txtToWrite != null) {
            try {
                System.out.println("-> " + txtToWrite);
                txtToWrite = txt.getTextToWrite();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}