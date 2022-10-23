package hidenseek.model.components.hearts;

import java.util.Optional;
import java.util.Set;

import hidenseek.model.components.RewardComponent;
import hidenseek.model.entities.Entity;
import hidenseek.model.enums.Heart;

public class EvilHeartComponentImpl extends AbstractHeartComponent {

    final private Set<Heart> hated = Set.of(Heart.GOOD); // hated hearts
    
    public EvilHeartComponentImpl() {
        super(Heart.EVIL);
    }
    
    @Override
    public <T extends Entity> boolean hates(final T e) {
        // get heartComponent
        final Optional<HeartComponent> optHeartComponent = e.getComponent(HeartComponent.class);
        // if HeartComponent is present AND heart is in hated list - is hated
        return optHeartComponent.isPresent() && this.hated.contains(optHeartComponent.get().getHeart());
    }


    @Override
    public <T extends Entity> boolean loves(final T e) {
        // none to love
        return false;
    }
    
}
    
    
