package hidenseek.view.entities;

import hidenseek.utils.ViewUtils;
import hidenseek.view.GraphicsDevice;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;


public class MonsterViewImpl extends AbstractEntityMovableView implements MonsterView {

    private static Image MONSTER_TOP = ViewUtils.loadImage("/assets/monster-top.png");
    private static Image MONSTER_RIGHT = ViewUtils.loadImage("/assets/monster-right.png");
    private static Image MONSTER_BOTTOM = ViewUtils.loadImage("/assets/monster-bottom.png");
    private static Image MONSTER_LEFT= ViewUtils.loadImage("/assets/monster-left.png");
    private static final int SPRITE_WIDTH = 40;
    private static final int SPRITE_HEIGHT = 40;
    
    @Override
    public void draw(final GraphicsDevice device) {
        
        Image monsterSprite = MONSTER_TOP;
        switch (this.getDirection()) {
        case UP:
            monsterSprite = MONSTER_TOP;
            break;
        case DOWN:
            monsterSprite = MONSTER_BOTTOM;
            break;
        case RIGHT:
            monsterSprite = MONSTER_RIGHT;
            break;
        case LEFT:
            monsterSprite = MONSTER_LEFT;
            break;
        }
        device.drawImage(monsterSprite, SPRITE_WIDTH, SPRITE_HEIGHT, this.getPosition());
    }

}