package hidenseek.model.components;

import java.util.Optional;

import hidenseek.model.Entity;

public class ComponentImpl implements Component {

    private Optional<Entity> owner = Optional.empty();
    
    @Override
    public void detach() {
        this.owner = Optional.empty();
    }

    @Override
    public Optional<Entity> getOwner() {
        return this.owner;
    }
    
}
