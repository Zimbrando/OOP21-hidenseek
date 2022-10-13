package hidenseek.model.components.hearts;

import java.util.Optional;
import java.util.Set;

import hidenseek.model.entities.Entity;
import hidenseek.model.enums.Heart;

public class GoodHeartComponentImpl extends AbstractHeartComponent {

    final private Set<Heart> hated = Set.of(Heart.EVIL); // hated hearts
    
    public GoodHeartComponentImpl() {
        super(Heart.GOOD); // my heart
    }
    
    @Override
    public <T extends Entity> boolean hateCheck(final T e) {
        // get heartComponent
        final Optional<HeartComponent> optHeartComponent = e.getComponent(HeartComponent.class);
        // if HeartComponent is present AND heart is in hated list - is hated
        return optHeartComponent.isPresent() && this.hated.contains(optHeartComponent.get().getHeart());
    }
    
}
