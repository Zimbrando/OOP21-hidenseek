package hidenseek.model.components;

import java.util.Set;
import java.util.function.Predicate;

public interface MoveComponent extends Component {

    void addForce(Force force);
    void removeForce(Force force);
    void removeForce(Predicate<Force> removeCondition);
    Set<Force> getForces();

    Force getResultantForce();
    
}
