package hidenseek.view;

import hidenseek.model.components.CollisionComponent;
import hidenseek.model.components.MoveComponent;
import hidenseek.model.entities.Monster;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class MonsterViewImpl implements PlayerView{

    String spriteUrl = "sprites/player-top.png";
    
    @Override
    public void draw(final GraphicsDevice device, final Point2D position) {

        double spriteDirection = model.getComponent(MoveComponent.class).get().getResultantForce().getDirection();
        double spriteIntensity = model.getComponent(MoveComponent.class).get().getResultantForce().getIntensity();

        if(spriteIntensity > 0) {
            spriteUrl = "sprites/player-top.png";
        }
        if(spriteIntensity > 0 && spriteDirection < 270) {
            spriteUrl = "sprites/player-left.png";
        }
        if(spriteIntensity > 0 && spriteDirection < 180) {
            spriteUrl = "sprites/player-bottom.png";
        }
        if(spriteIntensity > 0 && spriteDirection < 90) {
            spriteUrl = "sprites/player-right.png";
        }
        
        device.drawImage(new Image(spriteUrl), 40, 40, position.add(new Point2D(5, 5)));
        

        //TODO: remove this, only used for test
        //Draw Hitbox
        Point2D[] hitbox = model.getComponent(CollisionComponent.class).get().getHitbox().toArray(new Point2D[0]);       

        //Point2D position = model.getComponent(PositionComponent.class).get().getPosition();
        
        for(int i=0; i<hitbox.length; i++) {
            Point2D prevEntityPoint = (i == 0 ? hitbox[hitbox.length-1] : hitbox[i-1]).add(position);
            Point2D currEntityPoint = hitbox[i].add(position);
            
            device.drawLine(prevEntityPoint, currEntityPoint, Color.MAGENTA);
        }
    }

    //TODO: remove this, only used for test
    private final Monster model;
    public MonsterViewImpl(Monster monster) {
        this.model = monster;
    }

}