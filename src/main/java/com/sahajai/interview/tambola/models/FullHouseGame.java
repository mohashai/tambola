package com.sahajai.interview.tambola.models;

import com.sahajai.interview.tambola.strategies.GameWinningStrategy;
import com.sahajai.interview.tambola.strategies.impl.FullHouseGameWinningStrategy;
import com.sahajai.interview.tambola.strategies.impl.TopLineGameWinningStrategy;

public class FullHouseGame extends Game {

    public FullHouseGame(String name) {
        super(name);
    }

    @Override
    public GameType gameType() {
        return GameType.FULL_HOUSE;
    }

    @Override
    public GameWinningStrategy gameWinningStrategy() {
        return new FullHouseGameWinningStrategy();
    }
}
