package hidenseek.model.components.hearts;

import hidenseek.model.components.AbstractComponent;
import hidenseek.model.entities.Entity;
import hidenseek.model.enums.Heart;

public abstract class AbstractHeartComponent extends AbstractComponent implements HeartComponent {

    final private Heart heart;
    
    public AbstractHeartComponent(final Heart heart) {
        this.heart = heart;
    }
    
    @Override
    public Heart getHeart() {
        return this.heart;
    }

    @Override
    public <T extends Entity> boolean hates(final T e) {
        return this.hateCheck(e);
    }
    

    abstract public <T extends Entity> boolean hateCheck(T e);
    
}
