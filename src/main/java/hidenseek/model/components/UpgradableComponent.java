package hidenseek.model.components;


/**
 * Component that can be modified by {@link hidenseek.model.entities.PowerUp}s
 */
public interface UpgradableComponent extends Component {

    /**
     * Reset attributes
     */
    void reset();
    
    /**
     * @return True if the component is currently upgraded
     */
    boolean isUpgraded();
}
