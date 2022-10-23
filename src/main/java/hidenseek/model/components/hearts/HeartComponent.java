package hidenseek.model.components.hearts;

import hidenseek.model.components.Component;
import hidenseek.model.entities.Entity;
import hidenseek.model.enums.Heart;

public interface HeartComponent extends Component {
    Heart getHeart();

    <T extends Entity> boolean hates(final T e);

    <T extends Entity> boolean loves(final T e);
}
