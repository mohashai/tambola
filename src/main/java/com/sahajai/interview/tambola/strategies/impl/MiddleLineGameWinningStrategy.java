package com.sahajai.interview.tambola.strategies.impl;

import com.sahajai.interview.tambola.models.Ticket;
import com.sahajai.interview.tambola.models.TicketNumber;
import com.sahajai.interview.tambola.strategies.GameWinningStrategy;

import java.util.Arrays;

public class MiddleLineGameWinningStrategy implements GameWinningStrategy {
    @Override
    public boolean isWinningTicket(Ticket ticket, int lastAnnouncedNumber) {
        if (ticket != null
                && ticket.getGrid() != null
                && ticket.getGrid().length > 0
                && ticket.getLastMarkedNumber() == lastAnnouncedNumber) {
            int middleLineIdx = ticket.getGrid().length / 2;
            TicketNumber[] middleLine = ticket.getGrid()[middleLineIdx];
            boolean isAllMarked = Arrays.stream(middleLine).allMatch(TicketNumber::isMarked);
            boolean isLastNumberFromMiddleLine = Arrays.stream(middleLine).anyMatch(tNum -> tNum.getNumber() == lastAnnouncedNumber);
            return isAllMarked && isLastNumberFromMiddleLine;
        }
        return false;
    }
}
