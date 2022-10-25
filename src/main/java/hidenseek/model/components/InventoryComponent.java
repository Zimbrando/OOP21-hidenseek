package hidenseek.model.components;

import hidenseek.model.entities.CollectableEntity;

/**
 * Component that monitors the amount of {@link CollectableEntity} collected by the owner
 */
public interface InventoryComponent extends Component {
    
    /**
     * Add a collectible in the inventory
     * @param entity
     */
    void addCollectible(CollectableEntity entity);
    
    /**
     * @param type
     *          The type of collectible
     * @return
     *          The amount of collectibles currently in the inventory
     */
    int getQuantity(Class<? extends CollectableEntity> type);
}
