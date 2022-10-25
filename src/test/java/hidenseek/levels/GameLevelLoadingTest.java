package hidenseek.levels;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import hidenseek.controller.LevelHandler;
import hidenseek.controller.LevelHandlerImpl;
import hidenseek.model.entities.Key;
import hidenseek.model.entities.Monster;
import hidenseek.model.entities.Player;
import hidenseek.model.entities.PowerUp;
import hidenseek.model.entities.Wall;
import hidenseek.model.statistics.StatisticsManagerImpl;


class GameLevelLoadingTest {

    final LevelHandler levelHandler = new LevelHandlerImpl(new StatisticsManagerImpl());
    
    @Test
    void testWallsEntitiesPresent() {
       levelHandler.reset();
       while(levelHandler.getCurrentLevel().isPresent()) {
           
           assertTrue(levelHandler.getCurrentLevel().get().getEntities().stream().map(c-> c.getModel()).anyMatch(e -> Wall.class.isInstance(e)));
           levelHandler.next();
       }
    }
    
    @Test
    void testPlayerEntityPresent() {
        levelHandler.reset();
        while(levelHandler.getCurrentLevel().isPresent()) {
            
            assertTrue(levelHandler.getCurrentLevel().get().getEntities().stream().map(c-> c.getModel()).anyMatch(e -> Player.class.isInstance(e)));
            levelHandler.next();
        }
    }
    
    @Test
    void testMonsterEntityPresent() {
        levelHandler.reset();
        while(levelHandler.getCurrentLevel().isPresent()) {
            
            assertTrue(levelHandler.getCurrentLevel().get().getEntities().stream().map(c-> c.getModel()).anyMatch(e -> Monster.class.isInstance(e)));
            levelHandler.next();
        }
    }
    
    @Test
    void testPowerUpEntityPresent() {
        levelHandler.reset();
        while(levelHandler.getCurrentLevel().isPresent()) {
            
            assertTrue(levelHandler.getCurrentLevel().get().getEntities().stream().map(c-> c.getModel()).anyMatch(e -> PowerUp.class.isInstance(e)));
            levelHandler.next();
        }
    }
    
    @Test
    void testKeyEntityPresent() {
        levelHandler.reset();
        while(levelHandler.getCurrentLevel().isPresent()) {
            
            assertTrue(levelHandler.getCurrentLevel().get().getEntities().stream().map(c-> c.getModel()).anyMatch(e -> Key.class.isInstance(e)));
            levelHandler.next();
        }
    }
    
}
