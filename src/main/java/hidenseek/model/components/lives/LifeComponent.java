package hidenseek.model.components.lives;

import hidenseek.model.components.Component;

/**
 * The life of an Entity
 */
public interface LifeComponent extends Component {

    /**
     * @return
     *          The current health
     */
    int getHealth();
    
    /** 
     * @return
     *          The max health
     */
    int getMaxHealth();
    
    /**
     * Hurt this life by damage
     * @param damage
     *          The amount of damage
     */
    void hurt(int damage);
    
    /**
     * 
     * @return
     *          If the player is currently alive
     */
    boolean isAlive();
}
