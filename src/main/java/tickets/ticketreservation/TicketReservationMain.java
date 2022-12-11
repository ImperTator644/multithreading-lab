package tickets.ticketreservation;

import lombok.Getter;
import tickets.Ticket;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TicketReservationMain {

    @Getter
    private static Ticket[] tickets;

    public static void main(String[] args) {
        try{
            setup(Integer.parseInt(args[0]));
        }
        catch(NumberFormatException e){
            e.printStackTrace();
        }

        int numberOfThreads = 0;
        try {
            numberOfThreads = Integer.parseInt(args[1]);
        } catch (NumberFormatException numberFormatException) {
            numberFormatException.printStackTrace();
        }
        ExecutorService exec = Executors.newFixedThreadPool(numberOfThreads);

        for (int i = 0; i < numberOfThreads; i++){
            exec.execute(new ReserveTicketThread());
        }

        exec.shutdown();
    }

    private static void setup(int num){
        tickets = new Ticket[num];
        for(int i=0; i<num;i++){
            tickets[i] = new Ticket(Integer.toString(i));
        }
    }
}
