package com.sahajai.interview.tambola.models;

public class Claim {
    private final Player player;
    private final Ticket ticket;
    private final GameType gameType;

    public Claim(Player player, Ticket ticket, GameType gameType) {
        this.player = player;
        this.ticket = ticket;
        this.gameType = gameType;
    }

    public Player getPlayer() {
        return player;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public GameType getGameType() {
        return gameType;
    }
}
