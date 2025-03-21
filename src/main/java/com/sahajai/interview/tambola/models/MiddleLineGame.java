package com.sahajai.interview.tambola.models;

import com.sahajai.interview.tambola.strategies.GameWinningStrategy;
import com.sahajai.interview.tambola.strategies.impl.MiddleLineGameWinningStrategy;
import com.sahajai.interview.tambola.strategies.impl.TopLineGameWinningStrategy;

public class MiddleLineGame extends Game {

    public MiddleLineGame(String name) {
        super(name);
    }

    @Override
    public GameType gameType() {
        return GameType.MIDDLE_LINE;
    }

    @Override
    public GameWinningStrategy gameWinningStrategy() {
        return new MiddleLineGameWinningStrategy();
    }
}
