package hidenseek.view.huds;

import hidenseek.view.GraphicsDevice;

public interface HudView {

    /**
     * Draw in the device this view
     * @param device
     *          The device used to draw
     */
    void draw(GraphicsDevice device);
}
