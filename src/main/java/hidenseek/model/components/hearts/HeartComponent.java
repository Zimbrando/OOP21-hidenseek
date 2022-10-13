package hidenseek.model.components;

import hidenseek.model.entities.Entity;
import hidenseek.model.enums.Heart;

public interface HeartComponent extends Component {
    Heart getHeart();
    
    <T extends Entity> boolean hates(final T e);
}
