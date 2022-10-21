package hidenseek.view.entities;

import hidenseek.view.GraphicsDevice;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;


public class MonsterViewImpl extends AbstractEntityMovableView implements MonsterView {

    private static Image MONSTER_TOP = new Image("assets/monster-top.png");
    private static Image MONSTER_RIGHT = new Image("assets/monster-right.png");
    private static Image MONSTER_BOTTOM = new Image("assets/monster-bottom.png");
    private static Image MONSTER_LEFT= new Image("assets/monster-left.png");
    private static final int SPRITE_WIDTH = 40;
    private static final int SPRITE_HEIGHT = 40;
    
    @Override
    public void draw(final GraphicsDevice device) {

        switch (this.getDirection()) {
        case UP:
            device.drawImage(MONSTER_TOP, SPRITE_WIDTH, SPRITE_HEIGHT, this.getPosition().add(new Point2D(5, 5)));
            break;
        case DOWN:
            device.drawImage(MONSTER_BOTTOM, SPRITE_WIDTH, SPRITE_HEIGHT, this.getPosition().add(new Point2D(5, 5)));
            break;
        case RIGHT:
            device.drawImage(MONSTER_RIGHT, SPRITE_WIDTH, SPRITE_HEIGHT, this.getPosition().add(new Point2D(5, 5)));
            break;
        case LEFT:
            device.drawImage(MONSTER_LEFT, SPRITE_WIDTH, SPRITE_HEIGHT, this.getPosition().add(new Point2D(5, 5)));
            break;
        }
        
        
        //DEBUG: hitbox draw
        Point2D[] hitbox = new Point2D[] {
                new Point2D(0, 0),
                new Point2D(0, 50),
                new Point2D(50, 50),
                new Point2D(50, 0)
        };
        
        for(int i=0; i<hitbox.length; i++) {
            Point2D prevEntityPoint = (i == 0 ? hitbox[hitbox.length-1] : hitbox[i-1]).add(this.getPosition());
            Point2D currEntityPoint = hitbox[i].add(this.getPosition());
            device.drawLine(prevEntityPoint, currEntityPoint, javafx.scene.paint.Color.MAGENTA);
        }
    }

}