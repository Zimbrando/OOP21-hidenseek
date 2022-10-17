package hidenseek.controller;

import hidenseek.model.components.InventoryComponent;
import hidenseek.model.entities.Entity;
import hidenseek.model.entities.Key;
import hidenseek.view.KeyHudView;

public class KeyHudControllerImpl extends AbstractHudController<KeyHudView> {
 
    public KeyHudControllerImpl(final Entity model, final KeyHudView view) {
        super(model, view);
    }
    
    @Override
    public void update() {
        this.model.getComponent(InventoryComponent.class)
                .ifPresent(inventory -> this.view.updateKeys(inventory.getQuantity(Key.class)));
    }

}
