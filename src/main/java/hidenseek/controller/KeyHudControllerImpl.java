package hidenseek.controller;

import hidenseek.model.components.InventoryComponent;
import hidenseek.model.entities.Entity;
import hidenseek.model.entities.Key;
import hidenseek.view.huds.KeyHudView;

/**
 * Hud controller for the key counter interface
 */
public class KeyHudControllerImpl extends AbstractHudController<KeyHudView> {
 
    public KeyHudControllerImpl(final Entity model, final KeyHudView view) {
        super(model, view);
    }
    
    @Override
    public void update() {
        this.getModel().getComponent(InventoryComponent.class)
                .ifPresent(inventory -> this.getView().updateKeys(inventory.getQuantity(Key.class)));
    }

}
