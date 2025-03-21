package com.sahajai.interview.tambola.strategies;

import com.sahajai.interview.tambola.models.Ticket;
import com.sahajai.interview.tambola.strategies.impl.EarlyNGameWinningStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EarlyNGameWinningStrategyTest {
    private Ticket ticket;
    private GameWinningStrategy strategy;

    @BeforeEach
    void setUp() {
        ticket = new Ticket("ticket1");
        strategy = new EarlyNGameWinningStrategy(5);
    }

    @Test
    void testWinningTicket_AnyFiveMarked() {
        // Mark any 5 numbers
        int lastNumber = -1;
        for (int i = 0; i < 5; i++) {
            int num = ticket.getGrid()[0][i].getNumber();
            ticket.markNumber(num);
            lastNumber = num;
        }

        // Assert that the ticket is now a winning ticket
        assertTrue(strategy.isWinningTicket(ticket, lastNumber), "Early Five should be a winning ticket");
    }

    @Test
    void testNonWinningTicket_LessThanFiveMarked() {
        // Mark only a few numbers
        int num = ticket.getGrid()[0][0].getNumber();
        ticket.markNumber(num);

        int num2 = ticket.getGrid()[1][1].getNumber();
        ticket.markNumber(num2);

        // Assert that the ticket is NOT a winning ticket
        assertFalse(strategy.isWinningTicket(ticket, num2), "EarlyN should not be a winning ticket if not all numbers are marked");
    }

    @Test
    void testNonWinningTicket_MoreThanFiveMarked() {
        // Mark any 5 numbers
        for (int i = 0; i < 5; i++) {
            int num = ticket.getGrid()[0][i].getNumber();
            ticket.markNumber(num);
        }
        int num2 = ticket.getGrid()[1][1].getNumber();
        ticket.markNumber(num2);

        // Assert that the ticket is NOT a winning ticket
        assertFalse(strategy.isWinningTicket(ticket, num2), "EarlyN should not be a winning ticket if more than N numbers are marked");
    }

    @Test
    void testValidEarlyFiveButWrongLastAnnouncedNumber() {
        // Mark all numbers in the top row
        for (int i = 0; i < 5; i++) {
            int num = ticket.getGrid()[0][i].getNumber();
            ticket.markNumber(num);
        }

        int wrongLastNumber = ticket.getGrid()[1][0].getNumber();

        // Assert that the ticket does NOT win due to wrong last announced number
        assertFalse(strategy.isWinningTicket(ticket, wrongLastNumber),
                "Winning should be rejected if the last announced number does not match the last marked number");
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
