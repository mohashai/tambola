package com.sahajai.interview.tambola.models;

import com.sahajai.interview.tambola.exceptions.InvalidRoundMetadataException;
import com.sahajai.interview.tambola.exceptions.MissingGamesException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Round {
    private final String id;
    private final RoundMetadata roundMetadata;
    private final List<Game> games;
    private final Set<Integer> announcedNumbers;
    private final List<Player> players;
    private int lastAnnouncedNumber;
    private RoundState state;

    private Round(RoundBuilder builder) {
        this.id = builder.id;
        this.roundMetadata = builder.roundMetadata;
        this.games = builder.games;
        this.announcedNumbers = new HashSet<>();
        this.lastAnnouncedNumber = -1;
        this.state = RoundState.INACTIVE;
        this.players = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public List<Player> getPlayers() {
        return players;
    }


    public List<Game> getGames() {
        return games;
    }

    public Set<Integer> getAnnouncedNumbers() {
        return announcedNumbers;
    }

    public void announcedNumber(int num) {
        announcedNumbers.add(num);
        lastAnnouncedNumber = num;
    }

    public void markGameComplete(GameType gameType) {
        games.stream()
                .filter(_game -> _game.gameType().equals(gameType))
                .findFirst()
                .ifPresent(game -> game.updateState(GameState.OVER));
    }

    public int getLastAnnouncedNumber() {
        return lastAnnouncedNumber;
    }

    public RoundState getState() {
        return state;
    }

    public void updateState(RoundState state) {
        this.state = state;
    }

    public RoundMetadata getRoundMetadata() {
        return roundMetadata;
    }

    public static RoundBuilder getBuilder() {
        return new RoundBuilder();
    }

    public void registerPlayer(Player player, int noOfTickets) {
        this.players.add(player);
        for (int i = 0; i < noOfTickets; i++) {
            String id = player.getId() + "-" + this.id + "-" + i;
            player.addTicket(new Ticket(id, roundMetadata));
        }
    }

    public static class RoundBuilder {
        private String id;
        private final RoundMetadata roundMetadata = new RoundMetadata();
        private List<Game> games;


        public RoundBuilder withId(String id) {
            this.id = id;
            return this;
        }

        public RoundBuilder withGames(List<Game> games) {
            this.games = games;
            return this;
        }

        public RoundBuilder withGame(Game game) {
            if (this.games == null) {
                this.games = new ArrayList<>();
            }
            this.games.add(game);
            return this;
        }

        public RoundBuilder withTicketRows(int rows) {
            this.roundMetadata.setTicketRows(rows);
            return this;
        }

        public RoundBuilder withTicketCols(int cols) {
            this.roundMetadata.setTicketCols(cols);
            return this;
        }

        public RoundBuilder withTicketMinNumber(int minNumber) {
            this.roundMetadata.setMinNumber(minNumber);
            return this;
        }

        public RoundBuilder withTicketMaxNumber(int maxNumber) {
            this.roundMetadata.setMaxNumber(maxNumber);
            return this;
        }

        public Round build() throws MissingGamesException, InvalidRoundMetadataException {
            if (games == null || games.isEmpty()) {
                throw new MissingGamesException("Cannot start round without any games!");
            }
            if (!roundMetadata.isValid()) {
                throw new InvalidRoundMetadataException("Round Metadata is invalid!");
            }
            return new Round(this);
        }
    }
}
