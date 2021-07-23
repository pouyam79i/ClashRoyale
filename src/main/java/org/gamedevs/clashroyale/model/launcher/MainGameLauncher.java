package org.gamedevs.clashroyale.model.launcher;

import javafx.application.Platform;
import org.gamedevs.clashroyale.MainConfig;
import org.gamedevs.clashroyale.controller.menu.main.DeckScene;
import org.gamedevs.clashroyale.controller.menu.main.LastGamesPopup;
import org.gamedevs.clashroyale.controller.menu.main.MainBattle;
import org.gamedevs.clashroyale.controller.menu.main.ProfilePopup;
import org.gamedevs.clashroyale.model.container.gamedata.UserAccountContainer;
import org.gamedevs.clashroyale.model.container.scene.MenuDataContainer;
import org.gamedevs.clashroyale.model.loader.view.OnWaitLoader;
import org.gamedevs.clashroyale.model.media.MusicPlayer;
import org.gamedevs.clashroyale.model.media.Musics;
import org.gamedevs.clashroyale.model.utils.console.Console;
import org.gamedevs.clashroyale.model.utils.multithreading.Runnable;

/**
 * This class launches the main game sequence,
 * which brings the game until main menu!
 * @author Pouya Mohammadi - CE@AUT 9829039
 * @version 1.0
 */
public class MainGameLauncher extends Runnable {

    /**
     * Constructor of MainGameLauncher
     * Sets requirements!
     */
    public MainGameLauncher(){
        threadName = "MainGameLauncher";
    }

    /**
     * This thread brings the game until main menu
     */
    @Override
    public void run() {
        printTraceMessage("launcher sequence started");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {}
        // If we dont have any logged in account!
        if(UserAccountContainer.getUserAccountContainer().getAccount() == null){
            printTraceMessage("No account detected! Loading signup menu!");
            Platform.runLater(() -> {
                OnWaitLoader.getOnWaitLoader().disappear();
                MenuDataContainer.getMenuDataContainer().getRootPane().getChildren().add(
                        MenuDataContainer.getMenuDataContainer().getSignupMenu()
                );
            });
        }
        // Waiting to log an account
        while (UserAccountContainer.getUserAccountContainer().getAccount() == null){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }
        printTraceMessage("Signed in account account detected! Loading main menu!");
        try {
            Thread.sleep(MainConfig.STD_BUTTON_ANIMATION_LATENCY);
        } catch (InterruptedException ignored) {}
        // Initializing main view of game
        Platform.runLater(() -> {
            try {
                LastGamesPopup.getLastGamesPopup().init();
                ProfilePopup.getProfilePopup().init();
                MainBattle.getMainBattle().init();
                DeckScene.getInstance().init();
            }catch (Exception e){
                Console.getConsole().printTracingMessage("Failed to initialize main menus: " + e.getMessage());
                if(MainConfig.DEBUG_MODE){
                    e.printStackTrace();
                }
            }
            // Going to main menu
            MusicPlayer.getMusicPlayer().play(Musics.MAIN_MENU);
            OnWaitLoader.getOnWaitLoader().disappear();
            MenuDataContainer.getMenuDataContainer().getRootPane().getChildren().remove(
                    MenuDataContainer.getMenuDataContainer().getSignupMenu()
            );
            MenuDataContainer.getMenuDataContainer().getRootPane().getChildren().add(
                    MenuDataContainer.getMenuDataContainer().getMainMenuRootGroup()
            );
        });
        this.shutdown();
    }

}
