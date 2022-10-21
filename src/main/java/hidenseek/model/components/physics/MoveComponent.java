package hidenseek.model.components.physics;

import java.util.Set;
import java.util.function.Predicate;

import hidenseek.model.components.UpgradableComponent;

public interface MoveComponent extends UpgradableComponent {

    void setSpeed(double speed);
    double getSpeed();
    
    void addForce(Force force);
    void removeForce(Force force);
    void removeForce(Predicate<Force> removeCondition);
    Set<Force> getForces();

    Force getResultantForce();
    
}
