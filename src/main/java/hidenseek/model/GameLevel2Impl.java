package hidenseek.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import hidenseek.controller.HudController;
import hidenseek.controller.KeyHudControllerImpl;
import hidenseek.controller.entities.EntityController;
import hidenseek.controller.entities.EntityControllerImpl;
import hidenseek.controller.entities.MovableEntityControllerImpl;
import hidenseek.controller.entities.PlayerControllerImpl;
import hidenseek.controller.entities.WallControllerImpl;
import hidenseek.model.entities.Entity;
import hidenseek.model.entities.Key;
import hidenseek.model.entities.Monster;
import hidenseek.model.entities.PowerUp;
import hidenseek.model.entities.Wall;
import hidenseek.model.enums.PowerUpType;
import hidenseek.view.entities.KeyView;
import hidenseek.view.entities.KeyViewImpl;
import hidenseek.view.entities.MonsterViewImpl;
import hidenseek.view.entities.PowerUpView;
import hidenseek.view.entities.PowerUpViewImpl;
import hidenseek.view.entities.WallView;
import hidenseek.view.entities.WallViewImpl;
import hidenseek.view.huds.KeyHudView;
import hidenseek.view.huds.KeyHudViewImpl;
import javafx.geometry.Point2D;

/*
 * TODO DELETE THIS CLASS, USED ONLY TO TEST A DIFFERENT LEVEL!!!
 */
public class GameLevel2Impl implements GameLevel {

    private Optional<Entity> player = Optional.empty();
    
    private Set<Entity> getWalls() {

        return new LinkedHashSet<Entity>(){{
            add(new Wall(new Point2D(400, 220), 
                    new LinkedHashSet<Point2D>(){{
                        add(new Point2D(0, 0));
                        add(new Point2D(118, 0));
                        add(new Point2D(118, 17));
                        add(new Point2D(0, 17));
                    }}
                    ));

            add(new Wall(new Point2D(200, 45), 
                    new LinkedHashSet<Point2D>(){{
                        add(new Point2D(175, 243));
                        add(new Point2D(175, 17));
                        add(new Point2D(197, 17));
                        add(new Point2D(197, 0));
                        add(new Point2D(79, 0));
                        add(new Point2D(79, 17));
                        add(new Point2D(158, 17));
                        add(new Point2D(158, 176));
                        add(new Point2D(96, 176));
                        add(new Point2D(96, 92));
                        add(new Point2D(117, 92));
                        add(new Point2D(117, 75));
                        add(new Point2D(0, 75));
                        add(new Point2D(0, 92));
                        add(new Point2D(80, 92));
                        add(new Point2D(80, 192));
                        add(new Point2D(157, 192));
                        add(new Point2D(157, 243));
                    }}
                    ));

            add(new Wall(new Point2D(754, 600), 
                    new LinkedHashSet<Point2D>(){{
                        add(new Point2D(127, 213));
                        add(new Point2D(127, 29));
                        add(new Point2D(110, 29));
                        add(new Point2D(110, 110));
                        add(new Point2D(37, 110));
                        add(new Point2D(37, 0));
                        add(new Point2D(20, 0));
                        add(new Point2D(20, 110));
                        add(new Point2D(0, 110));
                        add(new Point2D(0, 127));
                        add(new Point2D(110, 127));
                        add(new Point2D(110, 213));
                    }}
                    ));

            add(new Wall(new Point2D(0, 75), 
                    new LinkedHashSet<Point2D>(){{
                        add(new Point2D(234, 118));
                        add(new Point2D(234, 0));
                        add(new Point2D(0, 0));
                        add(new Point2D(0, 17));
                        add(new Point2D(217, 17));
                        add(new Point2D(217, 118));
                    }}
                    ));

            add(new Wall(new Point2D(500, 200), 
                    new LinkedHashSet<Point2D>(){{
                        add(new Point2D(0, 0));
                        add(new Point2D(17, 0));
                        add(new Point2D(17, 118));
                        add(new Point2D(0, 118));
                    }}
                    ));

            add(new Wall(new Point2D(321, 567), 
                    new LinkedHashSet<Point2D>(){{
                        add(new Point2D(127, 308));
                        add(new Point2D(127, 110));
                        add(new Point2D(60, 110));
                        add(new Point2D(60, 0));
                        add(new Point2D(43, 0));
                        add(new Point2D(43, 110));
                        add(new Point2D(0, 110));
                        add(new Point2D(0, 127));
                        add(new Point2D(110, 127));
                        add(new Point2D(110, 308));
                    }}
                    ));

            add(new Wall(new Point2D(89, 532), 
                    new LinkedHashSet<Point2D>(){{
                        add(new Point2D(0, 0));
                        add(new Point2D(220, 0));
                        add(new Point2D(220, 17));
                        add(new Point2D(0, 17));
                    }}
                    ));
        }};
    }

    private Map<PowerUpType, List<Entity>> getPowerUps() {
        List<Point2D> positions = List.of(new Point2D(200, 600), new Point2D(300, 600), new Point2D(500, 600), new Point2D(800, 200));
        Map<PowerUpType, List<Entity>> powerups = new HashMap<>();
        positions.forEach(pos -> {
            PowerUpType type = PowerUpType.generateRandomType();
            if (powerups.containsKey(type)) {
                List<Entity> typeEntity = powerups.get(type);
                typeEntity.add(new PowerUp(type, pos));
                powerups.put(type, typeEntity);
            } else {
                List<Entity> typeEntity = new ArrayList<>(List.of(new PowerUp(type, pos)));
                powerups.put(type, typeEntity);    
            }
        });
        return powerups;
    }


    private Set<Entity> getKeys() {
        return Set.of(new Key(new Point2D(500, 500)), new Key(new Point2D(350, 750)));
    }

    @Override
    public Set<EntityController> getEntities() {
        Set<EntityController> controllers = new LinkedHashSet<>();

        this.getWalls().forEach(wall -> {
            controllers.add(new WallControllerImpl(wall, new WallViewImpl()));
        });

        this.getPowerUps().forEach((type, powerups) -> {
            powerups.forEach(powerup -> {
                controllers.add(new EntityControllerImpl<PowerUpView>(powerup, new PowerUpViewImpl(type)));        
            });
        });

        this.getKeys().forEach(key -> {
            controllers.add(new EntityControllerImpl<KeyView>(key, new KeyViewImpl()));
        });

        final EntityController player = new PlayerControllerImpl();
        player.setPosition(new Point2D(30, 30));
        controllers.add(player);
        this.player = Optional.of(player.getModel());
        
        final EntityController monster = new MovableEntityControllerImpl<>(new Monster(), new MonsterViewImpl());
        monster.setPosition(new Point2D(700, 400));
        controllers.add(monster);

        return controllers;
    }

    @Override
    public int keysInLevel() {
        return this.getKeys().size();
    }

    @Override
    public Set<HudController> getHuds() {
        Set<HudController> huds = new LinkedHashSet<>();
        KeyHudView keyHudView = new KeyHudViewImpl(new Point2D(1400, 40));
        keyHudView.setMaxKeys(this.keysInLevel());
        final HudController keyHud = new KeyHudControllerImpl(player.get(), keyHudView);
        huds.add(keyHud);
        return huds;
    }

}

