package hidenseek.model.components;

import java.util.HashSet;
import java.util.Set;

import hidenseek.model.entities.CollectableEntity;


public class InventoryComponentImpl extends AbstractObservableComponent implements InventoryComponent{

    private final Set<CollectableEntity> inventory;
    
    public InventoryComponentImpl() {
        this.inventory = new HashSet<>();
    }

    @Override
    public void addCollectible(final CollectableEntity entity) {
        this.inventory.add(entity);
    }

    @Override
    public int getQuantity(final Class<? extends CollectableEntity> type) {
        return (int)this.inventory.stream()
                .filter(entity -> type.isInstance(entity))
                .count();
    }

}
