package hidenseek.model.components.physics;

import java.util.Set;
import java.util.function.Predicate;

import hidenseek.model.components.UpgradableComponent;

public interface MoveComponent extends UpgradableComponent {

    /**
     * @return the entity speed
     */
    double getSpeed();
    
    /**
     * Set the entity speed
     * @param speed
     */
    void setSpeed(double speed);
    
    /**
     * Add a force to the entity
     * @param force
     */
    void addForce(Force force);
    
    /**
     * Remove a force from the entity.
     * If the force is not present nothing happens.
     * @param force
     */
    void removeForce(Force force);
    
    /**
     * Remove all the forces that respect the remove condition from the entity.
     * @param force
     */
    void removeForce(Predicate<Force> removeCondition);
    
    /**
     * @return the list of entity's forces
     */
    Set<Force> getForces();
    
    /**
     * @return the resultant of all the entity's forces
     */
    Force getResultantForce();
    
}
