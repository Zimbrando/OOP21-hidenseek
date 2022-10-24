package hidenseek.model.enums;

/**
 * 
 * The file system names of every interface of the game.  
 *
 */

public enum GuiPanes {
    MAIN_MENU("MainMenuGui.fxml"),
    STATS_MENU("GameStatsGui.fxml"),
    GAMEWIN_MENU("GameWinGui.fxml"),
    GAMEOVER_MENU("GameOverGui.fxml"),
    GAME_GUI("GameGui.fxml");
    
    private String guiFileSystemName;
    
    GuiPanes(final String name){
        this.guiFileSystemName = name;
    }
    
    /**
     * 
     * @return The name of the enum value.
     */
    
    public String getFileName() {
        return this.guiFileSystemName; 
    }
    
}

