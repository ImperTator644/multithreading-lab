package authorwriter;

public class Main {

    public static void main(String[] args) {

        TextService t = new TextService();
        Thread t1 = new AuthorThread(t);
        Thread t2 = new WriterThread(t);
        t1.start();
        t2.start();

    }
}
