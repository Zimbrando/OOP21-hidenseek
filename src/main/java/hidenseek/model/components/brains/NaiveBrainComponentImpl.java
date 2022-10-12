package hidenseek.model.components.brains;

import java.util.Set;
import java.util.stream.Collectors;

import hidenseek.model.components.AbstractComponent;
import hidenseek.model.components.BrainComponent;
import hidenseek.model.components.HeartComponent;
import hidenseek.model.components.SenseComponent;
import hidenseek.model.entities.Entity;

public class NaiveBrainComponentImpl extends AbstractComponent implements BrainComponent {


    @Override
    public void neuroImpulse() {
        // check owner
        if(this.getOwner().isEmpty()) {
            return;
        }
        // check sense components
        final Entity owner = this.getOwner().get();
        if(owner.getComponent(SenseComponent.class).isEmpty()) {
            return;
        }
        // check heart component
        if(owner.getComponent(HeartComponent.class).isEmpty()) {
            return;
        }
        // get heart component
        final HeartComponent heart = owner.getComponent(HeartComponent.class).get();
        // get all sense components
        final Set<SenseComponent> senseComponents = owner.getComponents().stream().filter(c -> SenseComponent.class.isInstance(c)).map(c -> SenseComponent.class.cast(c)).collect(Collectors.toSet());
        // use all senses
        final Set<Entity> entities = senseComponents.stream().flatMap(c -> c.world().stream()).collect(Collectors.toSet());
        // use heart to determine actions
        switch(heart.getHeart()) {
            case GOOD: {
                System.out.println("escaping from all bad-hearted entities");
                break;
            }
            case EVIL: {
                System.out.println("following all good-hearted entities");
                break;
            }
        }
        
    }
    
}
