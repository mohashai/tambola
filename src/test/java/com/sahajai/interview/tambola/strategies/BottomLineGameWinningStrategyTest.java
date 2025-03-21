package com.sahajai.interview.tambola.strategies;

import com.sahajai.interview.tambola.models.Ticket;
import com.sahajai.interview.tambola.strategies.impl.BottomLineGameWinningStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BottomLineGameWinningStrategyTest {
    private Ticket ticket;
    private GameWinningStrategy strategy;

    @BeforeEach
    void setUp() {
        ticket = new Ticket("ticket1");
        strategy = new BottomLineGameWinningStrategy();
    }

    @Test
    void testWinningTicket_BottomLineMarked() {
        // Mark all numbers in the bottom row
        int lastNumber = -1;
        for (int i = 0; i < 5; i++) {
            int num = ticket.getGrid()[2][i].getNumber();
            ticket.markNumber(num);
            lastNumber = num;
        }

        // Assert that the ticket is now a winning ticket
        assertTrue(strategy.isWinningTicket(ticket, lastNumber), "Bottom line should be a winning ticket");
    }

    @Test
    void testNonWinningTicket_BottomLineNotFullyMarked() {
        // Mark only a few numbers in the bottom row
        int num = ticket.getGrid()[2][0].getNumber();
        ticket.markNumber(num);

        // Assert that the ticket is NOT a winning ticket
        assertFalse(strategy.isWinningTicket(ticket, num), "Bottom line should not be a winning ticket if not all numbers are marked");
    }

    @Test
    void testValidBottomLineButWrongLastAnnouncedNumber() {
        // Mark all numbers in the bottom row
        for (int i = 0; i < 5; i++) {
            int num = ticket.getGrid()[2][i].getNumber();
            ticket.markNumber(num);
        }

        int wrongLastNumber = ticket.getGrid()[1][0].getNumber(); // A number not from bottom line

        // Assert that the ticket does NOT win due to wrong last announced number
        assertFalse(strategy.isWinningTicket(ticket, wrongLastNumber),
                "Winning should be rejected if the last announced number does not match the last marked number");
    }

    @Test
    void testNotWinningTicket_LastMarkedNumberNotFromBottomLine() {
        // Mark all numbers in the bottom row
        for (int i = 0; i < 5; i++) {
            int num = ticket.getGrid()[2][i].getNumber();
            ticket.markNumber(num);
        }
        int num = ticket.getGrid()[1][0].getNumber();
        ticket.markNumber(num);

        // Assert that the ticket is now a winning ticket
        assertFalse(strategy.isWinningTicket(ticket, num), "Winning should be rejected if last announced number is not from the bottom line");
    }

    @Test
    void testEmptyTicket_NotWinning() {
        // No numbers marked
        assertFalse(strategy.isWinningTicket(ticket, 5), "An unmarked ticket should not be a winning ticket");
    }

    @Test
    void testNullGrid_ShouldReturnFalse() {
        // Manually set the grid to null (simulating an invalid state)
        assertFalse(strategy.isWinningTicket(null, 0), "Should return false if ticket is null");
    }


}
