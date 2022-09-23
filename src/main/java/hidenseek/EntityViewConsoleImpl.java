package hidenseek;

import javafx.scene.paint.Color;

class EntityViewConsoleImpl implements EntityView {
    
    private Color color;

    EntityViewConsoleImpl(final Color c) {
        this.color = c;
    }
    
    @Override
    public void update() {
        System.out.println("My color is: " + color.toString());
    }

}
