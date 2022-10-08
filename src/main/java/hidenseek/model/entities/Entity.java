package hidenseek.model.entities;

import java.util.Optional;
import java.util.Set;

import hidenseek.model.components.Component;

public interface Entity {

    void attach(Component comp);
    
    <C extends Component> void detach(Class<C> component);
    
    Set<Component> getComponents();
    
    <C extends Component> Optional<C> getComponent(Class<C> component);
}
