package com.sahajai.interview.tambola.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class TicketTest {
    private Ticket ticket;

    @BeforeEach
    void setUp() {
        RoundMetadata metadata = new RoundMetadata();
        ticket = new Ticket("ticket1", metadata);
    }

    @Test
    void testTicketGeneration() {
        assertNotNull(ticket.getGrid(), "Grid should not be null");
        assertEquals(3, ticket.getGrid().length, "Grid should have correct number of rows");
        assertEquals(5, ticket.getGrid()[0].length, "Grid should have correct number of columns");
    }

    @Test
    void testTicketNumbersWithinRange() {
        Set<Integer> ticketNumbers = ticket.getTicketNumbers();
        assertFalse(ticketNumbers.isEmpty(), "Ticket numbers should not be empty");
        assertTrue(ticketNumbers.stream().allMatch(num -> num >= 1 && num <= 90), "Ticket numbers should be within range 1-90");
    }

    @Test
    void testUniqueNumbersInTicket() {
        Set<Integer> ticketNumbers = ticket.getTicketNumbers();
        assertEquals(3 * 5, ticketNumbers.size(), "Ticket should contain unique numbers");
    }

    @Test
    void testMarkNumber() {
        int numberToMark = ticket.getTicketNumbers().iterator().next();
        assertFalse(ticket.isNumberMarked(numberToMark), "Number should not be marked initially");

        ticket.markNumber(numberToMark);
        assertTrue(ticket.isNumberMarked(numberToMark), "Number should be marked after calling markNumber()");
    }

    @Test
    void testMarkNonExistentNumber() {
        assertFalse(ticket.isNumberMarked(100), "Number outside range should not be marked");
        ticket.markNumber(100);
        assertFalse(ticket.isNumberMarked(100), "Number outside range should not be marked even after calling markNumber()");
    }

    @Test
    void testGetMarkedNumbers() {
        Iterator<Integer> itr = ticket.getTicketNumbers().iterator();
        int num1 = itr.next();
        int num2 = itr.next();

        ticket.markNumber(num1);
        ticket.markNumber(num2);

        Set<Integer> markedNumbers = ticket.getMarkedNumbers();
        assertEquals(2, markedNumbers.size(), "Should contain two marked numbers");
        assertTrue(markedNumbers.contains(num1), "Marked numbers should contain num1");
        assertTrue(markedNumbers.contains(num2), "Marked numbers should contain num2");
    }
}
