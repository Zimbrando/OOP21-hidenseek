package hidenseek.model.components;


/**
 * Component that can be modified by {@link PowerUp}s
 */
public interface UpgradableComponent {

    /**
     * Reset attributes
     */
    void reset();
    
    /**
     * @return True if the component is currently upgraded
     */
    boolean isUpgraded();
}
