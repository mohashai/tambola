package com.sahajai.interview.tambola.controllers;

import com.sahajai.interview.tambola.exceptions.InvalidRoundMetadataException;
import com.sahajai.interview.tambola.exceptions.MissingGamesException;
import com.sahajai.interview.tambola.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class ClaimValidatorTest {
    ClaimValidator validator;
    Player player1;
    Player player2;
    Round round;

    @BeforeEach
    void setup() throws MissingGamesException, InvalidRoundMetadataException {
        player1 = new Player(1, "Alice");
        player2 = new Player(2, "Bob");
        Game game1 = new TopLineGame("Top Line");
        Game game2 = new FullHouseGame("Full House");

        round = Round.getBuilder()
                .withId("Round 1")
                .withGames(Arrays.asList(game1, game2))
                .build();

        round.registerPlayer(player1, 1);
        round.registerPlayer(player2, 2);
        validator = new ClaimValidator(round);
    }

    @Test
    void testClaimValidator_AcceptClaim() {
        TicketNumber[] topLine = player1.getTickets().get(0).getGrid()[0];
        for(TicketNumber tNum: topLine) {
            round.announcedNumber(tNum.getNumber());
            player1.markNumber(tNum.getNumber());
        }
        Claim claim = new Claim(player1, player1.getTickets().get(0), GameType.TOP_LINE);
        ClaimState claimState =  validator.validateClaim(claim);
        assertEquals(ClaimState.ACCEPTED, claimState, "Claim State for valid claim should be Accepted");
    }

    @Test
    void testClaimValidator_RejectClaim_LateClaim() {
        TicketNumber[] topLine = player1.getTickets().get(0).getGrid()[0];
        for(TicketNumber tNum: topLine) {
            round.announcedNumber(tNum.getNumber());
            player1.markNumber(tNum.getNumber());
        }
        round.announcedNumber(100);
        Claim claim = new Claim(player1, player1.getTickets().get(0), GameType.TOP_LINE);
        ClaimState claimState =  validator.validateClaim(claim);
        assertEquals(ClaimState.REJECTED, claimState, "Claim State for late claim should be Rejected");
    }

    @Test
    void testClaimValidator_RejectClaim_InvalidGame() {
        TicketNumber[] topLine = player1.getTickets().get(0).getGrid()[0];
        for(TicketNumber tNum: topLine) {
            round.announcedNumber(tNum.getNumber());
            player1.markNumber(tNum.getNumber());
        }

        Claim claim = new Claim(player1, player1.getTickets().get(0), GameType.BOTTOM_LINE);
        ClaimState claimState =  validator.validateClaim(claim);
        assertEquals(ClaimState.REJECTED, claimState, "Claim State for invalid game should be Rejected");
    }

    @Test
    void testClaimValidator_RejectClaim_CompletedGame() {
        TicketNumber[] topLine = player1.getTickets().get(0).getGrid()[0];
        for(TicketNumber tNum: topLine) {
            round.announcedNumber(tNum.getNumber());
            player1.markNumber(tNum.getNumber());
        }
        round.markGameComplete(GameType.TOP_LINE);
        Claim claim = new Claim(player1, player1.getTickets().get(0), GameType.TOP_LINE);
        ClaimState claimState =  validator.validateClaim(claim);
        assertEquals(ClaimState.REJECTED, claimState, "Claim State for completed game should be Rejected");
    }

    @Test
    void testClaimValidator_RejectClaim_InvalidMarking() {
        TicketNumber[] topLine = player1.getTickets().get(0).getGrid()[0];
        for(TicketNumber tNum: topLine) {
            player1.markNumber(tNum.getNumber());
        }
        Claim claim = new Claim(player1, player1.getTickets().get(0), GameType.TOP_LINE);
        ClaimState claimState =  validator.validateClaim(claim);
        assertEquals(ClaimState.REJECTED, claimState, "Claim State for invalid marking should be Rejected");
    }
}
