package tickets.ticketreservsynchro;

import tickets.TicketReservation;
import tickets.Ticket;

public class TicketReservationSynchroService implements TicketReservation {
    private static TicketReservationSynchroService instance;

    private TicketReservationSynchroService(){}

    public static TicketReservationSynchroService getInstance(){
        if (instance == null){
            instance = new TicketReservationSynchroService();
        }
        return instance;
    }

    public synchronized boolean reserveTicket(Ticket ticket, Thread thread){
        if(!ticket.isReserved()){
            ticket.setReserved(true);
            ticket.setClient(thread.getName());
            System.out.println(thread.getName() + " zarezerwowal bilet nr " + ticket.getName());
            return true;
        }
        System.out.println(thread.getName() + " czeka na zarezerwowanie biletu nr " + ticket.getName());
        return false;
    }

    public void leaveTicket(Ticket ticket, Thread thread){
        if(ticket.getClient().equals(thread.getName())) {
            ticket.setReserved(false);
            System.out.println(thread.getName() + " zwolnil rezerwacje biletu nr " + ticket.getName());
        }
    }
}
