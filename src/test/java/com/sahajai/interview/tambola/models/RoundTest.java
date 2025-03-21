package com.sahajai.interview.tambola.models;

import com.sahajai.interview.tambola.exceptions.MissingGamesException;
import com.sahajai.interview.tambola.exceptions.InvalidRoundMetadataException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RoundTest {
    private Round round;
    private Player player1;
    private Player player2;
    private Game game1;
    private Game game2;

    @BeforeEach
    void setUp(){
        player1 = new Player(1, "Alice");
        player2 = new Player(2, "Bob");
        game1 = new TopLineGame("Top Line");
        game2 = new FullHouseGame("Full House");
    }

    @Test
    void testRoundCreation_Success() throws MissingGamesException, InvalidRoundMetadataException {
        round = Round.getBuilder()
                .withId("Round 1")
                .withGames(Arrays.asList(game1, game2))
                .build();
        assertNotNull(round, "Round should be created successfully");
        assertEquals("Round 1", round.getId(), "Round Id should match");
        assertEquals(0, round.getPlayers().size(), "Round should have 0 players");
        assertEquals(2, round.getGames().size(), "Round should have 2 games");
        assertEquals(RoundState.INACTIVE, round.getState(), "Initial state should be INACTIVE");
    }

    @Test
    void testAnnouncedNumber() throws MissingGamesException, InvalidRoundMetadataException {
        round = Round.getBuilder()
                .withId("Round 1")
                .withGames(Arrays.asList(game1, game2))
                .build();
        round.announcedNumber(25);
        round.announcedNumber(50);

        Set<Integer> announcedNumbers = round.getAnnouncedNumbers();
        assertTrue(announcedNumbers.contains(25), "25 should be in announced numbers");
        assertTrue(announcedNumbers.contains(50), "50 should be in announced numbers");
        assertEquals(50, round.getLastAnnouncedNumber(), "Last announced number should be 50");
    }

    @Test
    void testRoundCreation_InvalidRoundMetadata_OutsideRange_ThrowsException() {
        InvalidRoundMetadataException exception = assertThrows(InvalidRoundMetadataException.class, () ->
                new Round.RoundBuilder()
                        .withId("Invalid Round")
                        .withGame(game1)
                        .withTicketRows(10)
                        .withTicketCols(10)
                        .withTicketMinNumber(1)
                        .withTicketMaxNumber(90)
                        .build()
        );
        assertEquals("Round Metadata is invalid!", exception.getMessage());
    }

    @Test
    void testRoundCreation_InvalidRoundMetadata_InvalidBounds_ThrowsException() {
        InvalidRoundMetadataException exception = assertThrows(InvalidRoundMetadataException.class, () ->
                new Round.RoundBuilder()
                        .withId("Invalid Round")
                        .withGame(game1)
                        .withTicketRows(10)
                        .withTicketCols(10)
                        .withTicketMinNumber(90)
                        .withTicketMaxNumber(1)
                        .build()
        );
        assertEquals("Round Metadata is invalid!", exception.getMessage());
    }

    @Test
    void testRoundCreation_InvalidRound_ThrowsException() {
        MissingGamesException exception = assertThrows(MissingGamesException.class, () ->
                new Round.RoundBuilder()
                        .withId("Invalid Round")
                        .build()
        );
        assertEquals("Cannot start round without any games!", exception.getMessage());
    }

    @Test
    void testRound_RegisterPlayer() throws MissingGamesException, InvalidRoundMetadataException {
        round = Round.getBuilder()
                .withId("Round 1")
                .withGames(Arrays.asList(game1, game2))
                .build();
        assertEquals(0, round.getPlayers().size(), "Initially round should have 0 players");
        assertEquals(2, round.getGames().size(), "Round should have 2 games");
        assertEquals(RoundState.INACTIVE, round.getState(), "Initial state should be INACTIVE");
        assertEquals(0, player1.getTickets().size(), "Initially unregistered players should have 0 tickets");
        assertEquals(0, player2.getTickets().size(), "Initially unregistered players should have 0 tickets");
        round.registerPlayer(player1, 1);
        round.registerPlayer(player2, 2);
        assertEquals(2, round.getPlayers().size(), "After registration round should have 2 players");
        assertEquals(1, player1.getTickets().size(), "After registration player1 should have 1 ticket");
        assertEquals(2, player2.getTickets().size(), "After registration player2 should have 1 ticket");

    }
}
