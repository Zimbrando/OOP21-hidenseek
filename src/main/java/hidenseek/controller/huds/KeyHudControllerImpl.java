package hidenseek.controller.huds;

import java.util.Set;

import hidenseek.model.components.InventoryComponent;
import hidenseek.model.entities.Entity;
import hidenseek.model.entities.Key;
import hidenseek.view.huds.KeyHudView;

/**
 * Hud controller for the key counter interface
 */
public class KeyHudControllerImpl extends AbstractHudController<KeyHudView> {
 
    public KeyHudControllerImpl(final Set<Entity> model, final KeyHudView view) {
        super(model, view);
    }
    
    @Override
    public void update() {
        final int currentKeys = this.getModel().stream()
                .filter(entity -> entity.hasComponent(InventoryComponent.class))
                .mapToInt(entity -> entity.getComponent(InventoryComponent.class).get().getQuantity(Key.class))
                .sum();
        this.getView().updateKeys(currentKeys);
    }

}
