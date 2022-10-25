package hidenseek.model.entities;

import java.util.Set;
import java.util.HashSet;
import java.util.Optional;

import hidenseek.model.components.Component;

/**
 * Base class for {@link Entity}
 */
public abstract class AbstractEntity implements Entity {

    Set<Component> components;
    
    public AbstractEntity() {
        this.components = new HashSet<>();
    }
    
    @Override
    public void attach(final Component comp) {
        comp.setOwner(this);
        this.components.add(comp);
    }

    @Override
    public Set<Component> getComponents() {
        return new HashSet<Component>(components);
    }

    @Override
    public <C extends Component> void detach(final Class<C> component) {
        this.components.removeIf(c -> component.isInstance(c));
    }

    @Override
    public <C extends Component> Optional<C> getComponent(final Class<C> component) {
        return this.components.stream()
                .filter(c -> component.isInstance(c))
                .map(c -> component.cast(c))
                .findFirst();
    }

    @Override
    public <C extends Component> Boolean hasComponent(final Class<C> component) {
        return this.components.stream().anyMatch(c -> component.isInstance(c));
    }
    
}
