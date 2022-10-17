package hidenseek.controller;

import hidenseek.model.entities.Entity;
import hidenseek.view.HudView;

abstract class AbstractHudController<V extends HudView> implements HudController {

    final Entity model;
    final V view;

    AbstractHudController(final Entity model, final V view) {
       this.model = model;
       this.view = view;
    }
    
    @Override
    public abstract void update();

    @Override
    public V getView() {
        return this.view;
    }

    @Override
    public Entity getModel() {
        return this.model;
    }

}
