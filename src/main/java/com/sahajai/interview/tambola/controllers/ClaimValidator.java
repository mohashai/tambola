package com.sahajai.interview.tambola.controllers;

import com.sahajai.interview.tambola.models.*;

public class ClaimValidator {
    private final Round round;

    public ClaimValidator(Round round) {
        this.round = round;
    }

    public Round getRound() {
        return round;
    }

    public ClaimState validateClaim(Claim claim) {
        for(Game game: round.getGames()) {
            if(game.gameType().equals(claim.getGameType())
                    && !game.getState().equals(GameState.OVER)
                    && isWinningGame(game, claim.getTicket())) {
                return ClaimState.ACCEPTED;
            }
        }
        return ClaimState.REJECTED;
    }

    private boolean isWinningGame(Game game, Ticket ticket) {
        return game.gameWinningStrategy().isWinningTicket(ticket, getRound().getLastAnnouncedNumber())
                && getRound().getAnnouncedNumbers().containsAll(ticket.getMarkedNumbers());
    }
}
