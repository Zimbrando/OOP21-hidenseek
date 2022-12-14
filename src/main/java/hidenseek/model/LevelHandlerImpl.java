package hidenseek.model;

import java.util.List;
import java.util.Optional;

import hidenseek.model.statistics.StatisticsManager;
import hidenseek.model.statistics.numeric.NumericStatistic;
import hidenseek.model.statistics.score.ScoreStatistic;

public class LevelHandlerImpl implements LevelHandler {
    
    private final List<GameLevel> gameLevels;
    private int currentLevel;
    
    public LevelHandlerImpl(final StatisticsManager statisticsManager) {
        final GameLevel level1 = new GameLevelImpl(1);        
        final GameLevel level2 = new GameLevelImpl(2);        
        final GameLevel level3 = new GameLevelImpl(3);
        final GameLevel level4 = new GameLevelImpl(4);
        
        this.gameLevels = List.of(level1, level2, level3, level4);
        this.currentLevel = ((NumericStatistic)statisticsManager.getStatistic("curr_level").findFirst().get()).getProperty().getValue();

        if(this.currentLevel <= 0 || this.currentLevel >= 5) {
            this.currentLevel = 1;
        }
        
        gameLevels.forEach(gameLevel -> {
            statisticsManager.addStatistic(new ScoreStatistic("actual_score", Integer.toString(gameLevel.getLevelID()), "Current score"));
            statisticsManager.addStatistic(new ScoreStatistic("best_score", Integer.toString(gameLevel.getLevelID()), "Best score"));
            statisticsManager.addStatistic(new NumericStatistic("total_attempts", Integer.toString(gameLevel.getLevelID()), "Total attempts"));
        });
    }
    
    @Override
    public void next() {
        this.currentLevel++;
    }

    @Override
    public boolean hasNext() {
        return this.currentLevel < this.gameLevels.size();
    }
    
    @Override
    public void reset() {
        this.currentLevel = 1;
    }


    @Override
    public Optional<GameLevel> getCurrentLevel() {
        if (this.currentLevel > this.gameLevels.size()) {
            return Optional.empty();
        }
        return Optional.of(this.gameLevels.get(this.currentLevel - 1));
    }

    
}
