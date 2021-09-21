package ru.netology.manager;

import ru.netology.domain.Ticket;
import ru.netology.repository.TicketRepository;

import java.util.Arrays;
import java.util.Comparator;

public class TicketManager {
    private TicketRepository repository;

    public TicketManager(TicketRepository repository) {
        this.repository = repository;
    }

    public Ticket[] searchBy(String from, String to, Comparator<Ticket> comparator) {
        Ticket[] tickets = repository.findAll();
        Ticket[] findedTickets = new Ticket[0];
        for (Ticket ticket : tickets) {
            if (ticket.matches(from, to)) {
                int length = findedTickets.length + 1;
                Ticket[] tmp = new Ticket[length];
                System.arraycopy(findedTickets, 0, tmp, 0, findedTickets.length);
                int lastIndex = tmp.length - 1;
                tmp[lastIndex] = ticket;
                findedTickets = tmp;
            }
        }
        Arrays.sort(findedTickets, comparator);
        return findedTickets;
    }
}
