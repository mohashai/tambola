package com.sahajai.interview.tambola.strategies.impl;

import com.sahajai.interview.tambola.models.Ticket;
import com.sahajai.interview.tambola.models.TicketNumber;
import com.sahajai.interview.tambola.strategies.GameWinningStrategy;

import java.util.Arrays;
import java.util.stream.Collectors;

public class BottomLineGameWinningStrategy implements GameWinningStrategy {
    @Override
    public boolean isWinningTicket(Ticket ticket, int lastAnnouncedNumber) {
        if (ticket != null
                && ticket.getGrid() != null
                && ticket.getGrid().length > 0
                && ticket.getLastMarkedNumber() == lastAnnouncedNumber) {
            TicketNumber[] bottomLine = ticket.getGrid()[ticket.getGrid().length - 1];
            boolean isAllMarked = Arrays.stream(bottomLine).allMatch(TicketNumber::isMarked);
            boolean isLastNumberFromBottomLine = Arrays.stream(bottomLine).anyMatch(tNum -> tNum.getNumber() == lastAnnouncedNumber);
            return isAllMarked && isLastNumberFromBottomLine;
        }
        return false;
    }
}
