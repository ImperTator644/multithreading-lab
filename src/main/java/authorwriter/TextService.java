package authorwriter;

import java.io.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TextService {

    Lock lock = new ReentrantLock();
    Condition txtWritten = lock.newCondition();
    Condition txtSupplied = lock.newCondition();

    String txt = "";
    boolean newTxt = false;
    File file = new File("teksty.txt");
    FileWriter fileWriter;
    FileReader fileReader;

    {
        try {
            fileWriter = new FileWriter(file);
            fileReader = new FileReader(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    BufferedWriter bw = new BufferedWriter(fileWriter);
    BufferedReader br = new BufferedReader(fileReader);

    // Metoda ustalająca tekst do pliku, wywołuje Autor (uzytkownik wpisuje tekst do konsoli)
    void setTextToWrite(String s) throws IOException {
        lock.lock();
        try {
            if (!txt.equals("")) {
                while (newTxt == true)
                    txtWritten.await();
            }
            if (s.equals("X")) {
                bw.close();
                txt = "X";
                txtSupplied.signal();
                return;
            }
            bw.write(s);
            bw.newLine();
            bw.flush();
            newTxt = true;
            txtSupplied.signal();
        } catch (InterruptedException exc) {
            System.out.println("Error sending a message " + exc.getMessage());
            bw.close();
        } finally {
            lock.unlock();
        }
    }

    // Metoda pobrania tekstu z pliku, wywołuje Writer i wypisuje na konsoli
    String getTextToWrite() throws IOException {
        lock.lock();
        try {
            while (!newTxt) {
                if (txt.equals("X")) {
                    br.close();
                    return null;
                }
                txtSupplied.await();
            }
            newTxt = false;
            txtWritten.signal();
            return br.readLine();
        } catch (InterruptedException exc) {
            System.out.println("Error getting a message " + exc.getMessage());
            br.close();
            return null;
        } finally {
            lock.unlock();
        }
    }
}
