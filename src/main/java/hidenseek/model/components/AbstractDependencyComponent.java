package hidenseek.model.components;

import java.util.Set;

import hidenseek.model.entities.Entity;

public abstract class AbstractDependencyComponent extends AbstractComponent {

    final private Set<Class<? extends Component>> dependencies;
    
    public AbstractDependencyComponent(final Set<Class<? extends Component>> dependencies) {
        super();
        this.dependencies = dependencies;
    }
    
    /**
     * Get all dependency of this component
     * @return Set of Class of component from which it depends
     */
    public Set<Class<? extends Component>> getDependencies() {
        return this.dependencies;
    }
    
    
    /**
     * Check if owner has all the dependencies needed
     * @return true if all satisfied, false otherwise
     */
    public boolean checkDependencies() {
        // check owner
        if(this.getOwner().isEmpty()) {
            return false;
        }
        // check dependencies components
        final Entity owner = this.getOwner().get();
        return this.dependencies.stream().allMatch(c -> owner.getComponent(c).isPresent());
    }
    
    
    
}
