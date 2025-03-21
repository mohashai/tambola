package com.sahajai.interview.tambola.models;

import com.sahajai.interview.tambola.strategies.GameWinningStrategy;
import com.sahajai.interview.tambola.strategies.impl.BottomLineGameWinningStrategy;

public class BottomLineGame extends Game {

    public BottomLineGame(String name) {
        super(name);
    }

    @Override
    public GameType gameType() {
        return GameType.BOTTOM_LINE;
    }

    @Override
    public GameWinningStrategy gameWinningStrategy() {
        return new BottomLineGameWinningStrategy();
    }
}
