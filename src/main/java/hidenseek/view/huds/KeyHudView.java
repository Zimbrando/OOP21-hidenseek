package hidenseek.view.huds;

public interface KeyHudView extends HudView {
    
    /**
     * Set the current keys obtained
     * @param nKeys
     *          Current keys obtained
     */
    void updateKeys(int nKeys);
    
    /**
     * Sets the number of keys obtainable
     * @param maxKeys
     *          Number of keys in the level
     */
    void setMaxKeys(int maxKeys);
}
