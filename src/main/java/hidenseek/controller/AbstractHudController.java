package hidenseek.controller;

import hidenseek.model.entities.Entity;
import hidenseek.view.huds.HudView;

/**
 * 
 * Base class for all {@link HudController}
 * 
 * @param <V>
 *       Type of {@link HudView} attached
 */
abstract class AbstractHudController<V extends HudView> implements HudController {
    
    final private Entity model;
    final private V view;

    AbstractHudController(final Entity model, final V view) {
       this.model = model;
       this.view = view;
    }
    
    @Override
    public abstract void update();


    public V getView() {
        return this.view;
    }   
    
    /**
     * @return The relative Entity model
     */
    protected Entity getModel() {
        return this.model;
    }

}
