package com.sahajai.interview.tambola.models;

import com.sahajai.interview.tambola.strategies.GameWinningStrategy;
import com.sahajai.interview.tambola.strategies.impl.EarlyNGameWinningStrategy;

public class EarlyNGame extends Game {
    private final int n;

    public EarlyNGame(String name, int n) {
        super(name);
        this.n = n;
    }

    public int getN() {
        return n;
    }

    @Override
    public GameType gameType() {
        return GameType.EARLY_N;
    }

    @Override
    public GameWinningStrategy gameWinningStrategy() {
        return new EarlyNGameWinningStrategy(n);
    }
}
