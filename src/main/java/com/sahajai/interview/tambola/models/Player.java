package com.sahajai.interview.tambola.models;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final int id;
    private final String name;
    private List<Ticket> tickets;

    public Player(int id, String name, List<Ticket> tickets) {
        this.id = id;
        this.name = name;
        this.tickets = tickets;
    }

    public Player(int id, String name) {
        this.id = id;
        this.name = name;
        this.tickets = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void markNumber(int num) {
        tickets.forEach(ticket -> ticket.markNumber(num));
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public void addTicket(Ticket ticket) {
        if (this.tickets == null) {
            this.tickets = new ArrayList<>();
        }
        this.tickets.add(ticket);
    }
}
