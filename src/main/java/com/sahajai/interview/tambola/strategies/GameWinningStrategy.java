package com.sahajai.interview.tambola.strategies;

import com.sahajai.interview.tambola.models.Ticket;

public interface GameWinningStrategy {
    public boolean isWinningTicket(Ticket ticket, int lastAnnouncedNumber);
}
