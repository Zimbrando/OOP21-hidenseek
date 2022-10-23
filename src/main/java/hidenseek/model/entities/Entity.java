package hidenseek.model.entities;

import java.util.Optional;
import java.util.Set;

import hidenseek.model.Triggerable;
import hidenseek.model.components.Component;

/**
 * Basic unit in a game world
 */
public interface Entity extends Triggerable {

    /**
     * Attach a component
     * @param comp
     *          The component
     */
    void attach(Component comp);
    
    /**
     * Detaches the component of this type
     * @param component
     *          The component type
     */
    <C extends Component> void detach(Class<C> component);
    
    /**
     * @return The components currently attached
     */
    Set<Component> getComponents();
    
    /**
     * @param component
     *          The component type
     * @return The actual component instance
     */
    <C extends Component> Optional<C> getComponent(Class<C> component);
    
    /**
     * @param component
     *          The component type
     * @return Returns true if component is present
     */
    <C extends Component> Boolean hasComponent(Class<C> component);
}
