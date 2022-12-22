package tickets.ticketreservsynchro;

import tickets.Ticket;
import tickets.TicketReservation;
import tickets.ticketreservation.TicketReservationMain;

import java.util.Random;

public class SynchroReserveTicketThread implements Runnable{

    private int hasTicket = -1;
    private final Random random = new Random();
    private final TicketReservation ticketReservation = TicketReservationSynchroService.getInstance();

    @Override
    public void run() {
        Ticket[] tickets = TicketSynchroReservationMain.getTickets();
        int ticketNumber;
        while(true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            ticketNumber = random.nextInt(tickets.length);
            if(hasTicket == -1){
                while(!ticketReservation.reserveTicket(tickets[ticketNumber], Thread.currentThread())){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                hasTicket = ticketNumber;
            }
            else{
                ticketReservation.leaveTicket(tickets[hasTicket], Thread.currentThread());
                hasTicket = -1;
            }
        }
    }
}
