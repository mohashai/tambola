package com.sahajai.interview.tambola.strategies.impl;

import com.sahajai.interview.tambola.models.Ticket;
import com.sahajai.interview.tambola.models.TicketNumber;
import com.sahajai.interview.tambola.strategies.GameWinningStrategy;

public class FullHouseGameWinningStrategy implements GameWinningStrategy {
    @Override
    public boolean isWinningTicket(Ticket ticket, int lastAnnouncedNumber) {
        return ticket != null && ticket.getMarkedNumbers().size() == ticket.getTicketNumbers().size() && ticket.getLastMarkedNumber() == lastAnnouncedNumber;
    }
}
