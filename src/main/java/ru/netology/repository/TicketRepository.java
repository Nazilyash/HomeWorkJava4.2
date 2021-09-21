package ru.netology.repository;

import ru.netology.domain.Ticket;
import ru.netology.exception.NotFoundException;

public class TicketRepository {
    private Ticket[] tickets = new Ticket[0];

    public void save(Ticket item) {
        int length = tickets.length + 1;
        Ticket[] tmp = new Ticket[length];
        System.arraycopy(tickets, 0, tmp, 0, tickets.length);
        int lastIndex = tmp.length - 1;
        tmp[lastIndex] = item;
        tickets = tmp;
    }

    public Ticket[] findAll() {
        return tickets;
    }

    public Ticket findById(int id) {
        for (Ticket ticket : tickets) {
            if (ticket.getId() == id) {
                return ticket;
            }
        }
        return null;
    }

    public void removeById(int id) {
        int length;
        Ticket ticketForDelete = findById(id);
        if (ticketForDelete == null) {
            throw new NotFoundException("Ticket with id: " + id + " not found");
        } else {
            length = tickets.length - 1;
        }
        Ticket[] tmp = new Ticket[length];
        int index = 0;
        for (Ticket ticket : tickets) {
            if (ticket.getId() != id) {
                tmp[index] = ticket;
                index++;
            }
        }
        tickets = tmp;
    }
}
