package com.sahajai.interview.tambola.models;

import com.sahajai.interview.tambola.strategies.GameWinningStrategy;

public abstract class Game {
    private final String name;
    private GameState state;

    public Game(String name) {
        this.name = name;
        this.state = GameState.ACTIVE;
    }

    public String getName() {
        return name;
    }

    public GameState getState() {
        return state;
    }

    public void updateState(GameState state) {
        this.state = state;
    }

    public abstract GameType gameType();
    public abstract GameWinningStrategy gameWinningStrategy();


}
