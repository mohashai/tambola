package com.sahajai.interview.tambola.strategies.impl;

import com.sahajai.interview.tambola.models.Ticket;
import com.sahajai.interview.tambola.strategies.GameWinningStrategy;

public class EarlyNGameWinningStrategy implements GameWinningStrategy {
    private final int n;

    public EarlyNGameWinningStrategy(int n) {
        this.n = n;
    }
    @Override
    public boolean isWinningTicket(Ticket ticket, int lastAnnouncedNumber) {
        return ticket != null && ticket.getMarkedNumbers().size() == n && ticket.getLastMarkedNumber() == lastAnnouncedNumber;
    }

    public int getN() {
        return n;
    }
}
