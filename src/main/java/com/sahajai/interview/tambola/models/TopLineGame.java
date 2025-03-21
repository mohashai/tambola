package com.sahajai.interview.tambola.models;

import com.sahajai.interview.tambola.strategies.GameWinningStrategy;
import com.sahajai.interview.tambola.strategies.impl.TopLineGameWinningStrategy;

public class TopLineGame extends Game {

    public TopLineGame(String name) {
        super(name);
    }

    @Override
    public GameType gameType() {
        return GameType.TOP_LINE;
    }

    @Override
    public GameWinningStrategy gameWinningStrategy() {
        return new TopLineGameWinningStrategy();
    }
}
