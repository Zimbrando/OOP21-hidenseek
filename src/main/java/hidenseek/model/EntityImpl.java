package hidenseek.model;

import java.util.Set;
import java.util.HashSet;
import java.util.Optional;

import hidenseek.model.components.Component;

public class EntityImpl implements Entity {

    Set<Component> components;
    
    public EntityImpl() {
        this.components = new HashSet<Component>();
    }
    
    @Override
    public void attach(Component comp) {
        this.components.add(comp);
    }

    @Override
    public Set<Component> getComponents() {
        return new HashSet<Component>(components);
    }

    @Override
    public <C extends Component> void detach(Class<C> component) {
        this.components.remove(this.components.stream().filter(c -> component.isInstance(c)).findFirst().get());
    }

    @Override
    public <C extends Component> Optional<C> getComponent(Class<C> component) {
        Optional<Component> comp = this.components.stream().filter(c -> component.isInstance(c)).findFirst();
        return comp.isPresent() ? Optional.of((C)comp.get()) : Optional.empty();
    }
    
}
