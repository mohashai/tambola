package com.sahajai.interview.tambola.strategies;

import com.sahajai.interview.tambola.models.Ticket;
import com.sahajai.interview.tambola.strategies.impl.FullHouseGameWinningStrategy;
import com.sahajai.interview.tambola.strategies.impl.TopLineGameWinningStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FullHouseGameWinningStrategyTest {
    private Ticket ticket;
    private GameWinningStrategy strategy;

    @BeforeEach
    void setUp() {
        ticket = new Ticket("ticket1");
        strategy = new FullHouseGameWinningStrategy();
    }

    @Test
    void testWinningTicket_FullHouseMarked() {
        // Mark all numbers
        int lastNumber = -1;
        for(int i = 0; i<3; i++) {
            for (int j = 0; j < 5; j++) {
                int num = ticket.getGrid()[i][j].getNumber();
                ticket.markNumber(num);
                lastNumber = num;
            }
        }

        // Assert that the ticket is now a winning ticket
        assertTrue(strategy.isWinningTicket(ticket, lastNumber), "Full House should be a winning ticket");
    }

    @Test
    void testNonWinningTicket_NotFullyMarked() {
        // Mark only a few numbers in the top row
        int lastNumber = -1;
        for(int i = 0; i<3; i++) {
            int num = ticket.getGrid()[i][0].getNumber();
            ticket.markNumber(num);
            lastNumber = num;
        }

        // Assert that the ticket is NOT a winning ticket
        assertFalse(strategy.isWinningTicket(ticket, lastNumber), "Not a Full House, should not be a winning ticket if not all numbers are marked");
    }

    @Test
    void testValidFullHouseButWrongLastAnnouncedNumber() {
        // Mark all numbers
        for(int i = 0; i<3; i++) {
            for (int j = 0; j < 5; j++) {
                int num = ticket.getGrid()[i][j].getNumber();
                ticket.markNumber(num);
            }
        }

        int wrongLastNumber = ticket.getGrid()[1][0].getNumber();

        // Assert that the ticket does NOT win due to wrong last announced number
        assertFalse(strategy.isWinningTicket(ticket, wrongLastNumber),
                "Winning should be rejected if the last announced number does not match the last marked number");
    }

    @Test
    void testEmptyTicket_NotWinning() {
        // No numbers marked
        assertFalse(strategy.isWinningTicket(ticket, 7), "An unmarked ticket should not be a winning ticket");
    }

    @Test
    void testNullGrid_ShouldReturnFalse() {
        // Manually set the grid to null (simulating an invalid state)
        assertFalse(strategy.isWinningTicket(null, 7), "Should return false if ticket is null");
    }


}
