package hidenseek.controller;

import java.util.List;
import java.util.Optional;

import hidenseek.model.GameLevel;
import hidenseek.model.GameLevel2Impl;
import hidenseek.model.GameLevelImpl;

public class LevelHandlerImpl implements LevelHandler {
    
    private final List<GameLevel> gameLevels;
    private int currentLevel = 0;
    
    public LevelHandlerImpl() {
        GameLevel level1 = new GameLevelImpl();
        GameLevel level2 = new GameLevel2Impl();
        this.gameLevels = List.of(level1, level2);
    }
    
    @Override
    public void next() {
        this.currentLevel++;
        System.out.println(this.currentLevel);
    }

    @Override
    public boolean hasNext() {
        return this.currentLevel < this.gameLevels.size() - 1;
    }

    @Override
    public Optional<GameLevel> getCurrentLevel() {
        if (this.currentLevel >= this.gameLevels.size()) {
            return Optional.empty();
        }
        return Optional.of(this.gameLevels.get(this.currentLevel));
    }

}
