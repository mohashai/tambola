package com.sahajai.interview.tambola.strategies;

import com.sahajai.interview.tambola.models.Ticket;
import com.sahajai.interview.tambola.strategies.impl.TopLineGameWinningStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TopLineGameWinningStrategyTest {
    private Ticket ticket;
    private GameWinningStrategy strategy;

    @BeforeEach
    void setUp() {
        ticket = new Ticket("ticket1");
        strategy = new TopLineGameWinningStrategy();
    }

    @Test
    void testWinningTicket_TopLineMarked() {
        // Mark all numbers in the top row
        int lastNumber = -1;
        for (int i = 0; i < 5; i++) {
            int num = ticket.getGrid()[0][i].getNumber();
            ticket.markNumber(num);
            lastNumber = num;
        }

        // Assert that the ticket is now a winning ticket
        assertTrue(strategy.isWinningTicket(ticket, lastNumber), "Top line should be a winning ticket");
    }

    @Test
    void testNonWinningTicket_TopLineNotFullyMarked() {
        // Mark only a few numbers in the top row
        int num = ticket.getGrid()[0][0].getNumber();
        ticket.markNumber(num);

        // Assert that the ticket is NOT a winning ticket
        assertFalse(strategy.isWinningTicket(ticket, num), "Top line should not be a winning ticket if not all numbers are marked");
    }

    @Test
    void testValidTopLineButWrongLastAnnouncedNumber() {
        // Mark all numbers in the top row
        for (int i = 0; i < 5; i++) {
            int num = ticket.getGrid()[0][i].getNumber();
            ticket.markNumber(num);
        }

        int wrongLastNumber = ticket.getGrid()[1][0].getNumber(); // A number not from top line

        // Assert that the ticket does NOT win due to wrong last announced number
        assertFalse(strategy.isWinningTicket(ticket, wrongLastNumber),
                "Winning should be rejected if the last announced number does not match the last marked number");
    }

    @Test
    void testNotWinningTicket_LastMarkedNumberNotFromTopLine() {
        // Mark all numbers in the top row
        for (int i = 0; i < 5; i++) {
            int num = ticket.getGrid()[0][i].getNumber();
            ticket.markNumber(num);
        }
        int num = ticket.getGrid()[2][0].getNumber();
        ticket.markNumber(num);

        // Assert that the ticket is now a winning ticket
        assertFalse(strategy.isWinningTicket(ticket, num), "Winning should be rejected if last announced number is not from the top line");
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
