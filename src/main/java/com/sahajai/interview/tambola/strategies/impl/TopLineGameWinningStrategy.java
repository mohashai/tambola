package com.sahajai.interview.tambola.strategies.impl;

import com.sahajai.interview.tambola.models.Ticket;
import com.sahajai.interview.tambola.models.TicketNumber;
import com.sahajai.interview.tambola.strategies.GameWinningStrategy;

import java.util.Arrays;

public class TopLineGameWinningStrategy implements GameWinningStrategy {
    @Override
    public boolean isWinningTicket(Ticket ticket, int lastAnnouncedNumber) {
        if (ticket != null
                && ticket.getGrid() != null
                && ticket.getGrid().length > 0
                && ticket.getLastMarkedNumber() == lastAnnouncedNumber) {
            TicketNumber[] topLine = ticket.getGrid()[0];
            boolean isAllMarked = Arrays.stream(topLine).allMatch(TicketNumber::isMarked);
            boolean isLastNumberFromTopLine = Arrays.stream(topLine).anyMatch(tNum -> tNum.getNumber() == lastAnnouncedNumber);
            return isAllMarked && isLastNumberFromTopLine;
        }
        return false;
    }
}
