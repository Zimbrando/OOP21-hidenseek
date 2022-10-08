package hidenseek.model.entities;

import java.util.Set;
import java.util.HashSet;
import java.util.Optional;

import hidenseek.model.components.Component;

public abstract class AbstractEntity implements Entity {

    Set<Component> components;
    
    public AbstractEntity() {
        this.components = new HashSet<Component>();
    }
    
    @Override
    public void attach(Component comp) {
        this.components.add(comp);
        comp.setOwner(this);
    }

    @Override
    public Set<Component> getComponents() {
        return new HashSet<Component>(components);
    }

    @Override
    public <C extends Component> void detach(Class<C> component) {
        this.components.removeIf(c -> component.isInstance(c));
    }

    @Override
    public <C extends Component> Optional<C> getComponent(Class<C> component) {
        return this.components.stream()
                .filter(c -> component.isInstance(c))
                .map(c -> component.cast(c))
                .findFirst();
    }
    
}
