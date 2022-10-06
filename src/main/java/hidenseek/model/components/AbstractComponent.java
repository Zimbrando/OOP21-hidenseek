package hidenseek.model.components;

import java.util.Objects;
import java.util.Optional;

import hidenseek.model.Entity;

public abstract class AbstractComponent implements Component {

    private Optional<Entity> owner = Optional.empty();
    
    @Override
    public void detach() {
        this.owner = Optional.empty();
    }

    @Override
    public Optional<Entity> getOwner() {
        return this.owner;
    }

    @Override
    public void attach(Entity e) {
        e.attach(this);
    }

    @Override
    public void setOwner(Entity e) {
        if (this.owner.isPresent()) throw new IllegalStateException("Owner already set");
        this.owner = Optional.ofNullable(e);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.getClass());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof AbstractComponent))
            return false;
        AbstractComponent other = (AbstractComponent) obj;
        return Objects.equals(owner, other.owner);
    }
    
}
