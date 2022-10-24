package hidenseek.model;
import java.util.Set;

import hidenseek.controller.HudController;
import hidenseek.controller.entities.EntityController;

public class GameLevelImpl implements GameLevel {

    private final int levelID;
    private final String levelName;
    private final int levelMaximumTime;
    final GameLevelLoader levelLoader;
    
    public GameLevelImpl(final int levelID) {
        this.levelID = levelID;
        
        this.levelLoader = new GameLevelLoader(levelID);
        
        this.levelName = levelLoader.getLevelName();
        this.levelMaximumTime = levelLoader.getLevelMaxTime();
    }

    @Override
    public int getLevelID() {
        return levelID;
    }

    @Override
    public Set<EntityController> getEntities() {
        return this.levelLoader.getEntities();
    }

    @Override
    public Set<HudController> getHuds() {
        return this.levelLoader.getHuds();
    }

    @Override
    public int getKeysNumber() {
        return this.levelLoader.getKeysNumber();
    }

    @Override
    public String getLevelName() {
        return levelName;
    }

    @Override
    public int getLevelMaximumTime() {
        return levelMaximumTime;
    }
    
}

