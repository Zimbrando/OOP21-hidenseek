package hidenseek.model.worlds;

import java.util.HashSet;
import java.util.Set;

import hidenseek.model.entities.Entity;

public abstract class AbstractEntityWorldImpl implements EntityWorld {

    private final Set<Entity> entities;
    
    public AbstractEntityWorldImpl() {
        this.entities = new HashSet<>();
    }
    
    @Override
    public void addEntity(final Entity entity) {
        this.entities.add(entity);
    }
    
    @Override
    public void removeEntity(final Entity e) {
        this.entities.remove(e);
    }
    
    @Override
    public Set<Entity> world() {
        return this.entities;
    }
    
    @Override
    public void clearEntities() {
        this.entities.clear();
    }
    
}
