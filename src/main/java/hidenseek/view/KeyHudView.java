package hidenseek.view;

public interface KeyHudView extends HudView {
    
    /**
     * Set the current keys obtained
     * @param nKeys
     */
    void updateKeys(int nKeys);
    
    /**
     * Sets the number of keys obtainable
     * @param maxKeys
     */
    void setMaxKeys(int maxKeys);
}
