package tickets.ticketreservation;

import tickets.Ticket;
import tickets.TicketReservation;

public class TicketReservationService implements TicketReservation {
    private static TicketReservationService instance;

    private TicketReservationService(){}

    public static TicketReservationService getInstance(){
        if (instance == null){
            instance = new TicketReservationService();
        }
        return instance;
    }

    public boolean reserveTicket(Ticket ticket, Thread thread){
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
