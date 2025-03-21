package com.sahajai.interview.tambola.models;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    @Test
    void testPlayerCreation_WithTickets() {
        RoundMetadata metadata = new RoundMetadata();
        Ticket ticket1 = new Ticket("ticket1", metadata);
        Ticket ticket2 = new Ticket("ticket2", metadata);

        Player player = new Player(1, "Alice");
        player.addTickets(Arrays.asList(ticket1, ticket2));
        assertEquals(1, player.getId(), "Player ID should be 1");
        assertEquals("Alice", player.getName(), "Player name should be Alice");
        assertEquals(2, player.getTickets().size(), "Player should have 2 tickets");
    }

    @Test
    void testPlayerCreation_WithoutTickets() {
        Player playerWithoutTickets = new Player(2, "Bob");

        assertEquals(2, playerWithoutTickets.getId(), "Player ID should be 2");
        assertEquals("Bob", playerWithoutTickets.getName(), "Player name should be Bob");
        assertNotNull(playerWithoutTickets.getTickets(), "Tickets list should not be null");
        assertTrue(playerWithoutTickets.getTickets().isEmpty(), "Player should have no tickets initially");
    }

    @Test
    void testMarkNumber_AppliesToAllTickets() {
        Player player3 = new Player(3, "Smith");
        RoundMetadata metadata = new RoundMetadata(3, 5, 1, 15);
        Ticket t1 = new Ticket("t1", metadata);
        Ticket t2 = new Ticket("t2", metadata);
        player3.addTicket(t1);
        player3.addTicket(t2);

        player3.markNumber(10);

        assertTrue(t1.isNumberMarked(10), "Number should be marked in ticket1");
        assertTrue(t2.isNumberMarked(10), "Number should be marked in ticket2");
    }

    @Test
    void testMarkNumber_NoTickets() {
        Player playerWithoutTickets = new Player(3, "Charlie");
        assertDoesNotThrow(() -> playerWithoutTickets.markNumber(10),
                "Marking number should not throw exception even if player has no tickets");
    }
}
