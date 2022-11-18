package ticketreservation;

import java.util.Random;

public class ReserveTicketThread implements Runnable{

    private int hasTicket = -1;
    private final Random random = new Random();
    private final TicketReservationService ticketReservationService = TicketReservationService.getInstance();

    @Override
    public void run() {
        Ticket[] tickets = TicketReservationMain.getTickets();
        int ticketNumber;
        while(true){
            try {
                Thread.sleep(random.nextInt(3000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            ticketNumber = random.nextInt(tickets.length);
            if(hasTicket == -1){
                while(!ticketReservationService.reserveTicket(tickets[ticketNumber], Thread.currentThread())){
                    try {
                        Thread.sleep(random.nextInt(1500));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                hasTicket = ticketNumber;
            }
            else{
                ticketReservationService.leaveTicket(tickets[hasTicket], Thread.currentThread());
                hasTicket = -1;
            }
        }
    }
}
