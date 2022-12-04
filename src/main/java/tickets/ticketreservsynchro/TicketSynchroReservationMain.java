package tickets.ticketreservsynchro;

import lombok.Getter;
import tickets.ticketreservation.ReserveTicketThread;
import tickets.Ticket;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TicketSynchroReservationMain {

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
            exec.execute(new SynchroReserveTicketThread());
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
