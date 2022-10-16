package hidenseek.model.components;

import hidenseek.model.entities.CollectableEntity;

public interface InventoryComponent extends Component {
    
    void addCollectible(CollectableEntity entity);
    
    int getQuantity(Class<? extends CollectableEntity> type);
}
