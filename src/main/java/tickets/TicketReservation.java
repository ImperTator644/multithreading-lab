package tickets;

public interface TicketReservation {

    boolean reserveTicket(Ticket ticket, Thread thread);

    void leaveTicket(Ticket ticket, Thread thread);
}
