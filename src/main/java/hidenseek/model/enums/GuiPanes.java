package hidenseek.model.enums;

public enum GuiPanes {
    MAIN_MENU("MainMenuGui.fxml"),
    STATS_MENU("GameStatsGui.fxml"),
    GAMEOVER_MENU("GameOverGui.fxml"),
    GAME_GUI("GameGui.fxml");
    
    
    private String guiFileSystemName;
    
    GuiPanes(final String name){
        this.guiFileSystemName = name;
    }
    
    public String getFileName() {
        return this.guiFileSystemName; 
    }
    
}

