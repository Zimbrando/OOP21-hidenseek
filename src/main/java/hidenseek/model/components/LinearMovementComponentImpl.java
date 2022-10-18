package hidenseek.model.components;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Predicate;

final public class LinearMovementComponentImpl extends AbstractObservableComponent implements MoveComponent {

    private static final double DEFAULT_SPEED = 1;
    private double speed = DEFAULT_SPEED;
    private Set<Force> forces = new HashSet<Force>();

    @Override
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    @Override
    public double getSpeed() {
        return speed;
    }
    
    @Override
    public void addForce(Force force) {
        forces.add(force);
    }

    @Override
    public void removeForce(Force force) {
        forces.remove(force);
    }

    @Override
    public void removeForce(Predicate<Force> removeCondition) {
        Iterator<Force> itr = forces.iterator();
        while (itr.hasNext()) {
            Force t = itr.next();
            if (removeCondition.test(t)) {
                itr.remove();
            }
        }
    }

    @Override
    public Set<Force> getForces(){
        return forces;
    }

    @Override
    public Force getResultantForce() {
        double resultantX = 0;
        double resultantY = 0;
        
        for(Force force : forces) {
            resultantX += force.getXComponent();
            resultantY += force.getYComponent();
        }
        
        if(resultantX == 0 && resultantY == 0) {
            return new Force("resultant", 0, 0);
        }
        
        double resultantIntensity = Math.sqrt(Math.pow(resultantX, 2) + Math.pow(resultantY, 2));
        double resultantDirection = Math.toDegrees(Math.atan(resultantY/resultantX));       
        
        if(resultantX < 0) {
            resultantDirection += 180;
        }
        return new Force("resultant", resultantIntensity , resultantDirection);
    }

    @Override
    public void reset() {
        this.setSpeed(DEFAULT_SPEED);
    }

    @Override
    public boolean isUpgraded() {
        return this.getSpeed() != DEFAULT_SPEED;
    }


}