package hidenseek.model.enums;

public enum GuiPanes {
    MAIN_MENU("MainMenuGui.fxml"),
    SETTINGS_MENU("GameSettingsGui.fxml"),
    STATS_MENU("GameStatsGui.fxml"),
    GAMEOVER_MENU("GameOverGui.fxml"),
    GAME_GUI("GameGui.fxml");
    
    
    String guiSystemName;
    
    GuiPanes(String name){
        this.guiSystemName = name;
    }
    
}

