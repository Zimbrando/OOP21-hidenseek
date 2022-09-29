package hidenseek.model.components;

public interface LifeComponent extends Component {

    int getHealth();
    
    int getMaxHealth();
    
    void hurt(int damage);
}
