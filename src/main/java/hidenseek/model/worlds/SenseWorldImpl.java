package hidenseek.model.worlds;

import hidenseek.model.components.brains.BrainComponent;
import hidenseek.model.components.senses.SenseComponent;

public class SenseWorldImpl extends AbstractEntityWorldImpl {
    
    @Override
    public void update() {
        // handle senses
        this.world().stream()
        .filter(e -> e.getComponent(SenseComponent.class).isPresent())  // get all entities 
        .forEach(e -> 
                    this.world().stream()               // get all entities
                    .filter(e2 -> !e2.equals(e))        // all but itself
                    .forEach(e2 -> e.getComponent(SenseComponent.class).get().feel(e2)) // sense entities using senses 
                 );
        
        // handle brains
        this.world().stream()
        .filter(e -> e.getComponent(BrainComponent.class).isPresent())  // get all entities 
        .forEach(e -> e.getComponent(BrainComponent.class).get().neuroImpulse());
        
        
    }
    
}
