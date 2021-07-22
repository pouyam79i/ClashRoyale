package org.gamedevs.clashroyale.model.launcher;

import javafx.application.Platform;
import org.gamedevs.clashroyale.model.container.gamedata.UserAccountContainer;
import org.gamedevs.clashroyale.model.container.scene.MenuDataContainer;
import org.gamedevs.clashroyale.model.loader.view.OnWaitLoader;
import org.gamedevs.clashroyale.model.media.MusicPlayer;
import org.gamedevs.clashroyale.model.media.Musics;
import org.gamedevs.clashroyale.model.utils.io.FileConfig;
import org.gamedevs.clashroyale.model.utils.multithreading.Runnable;

/**
 * This launcher is used to logout from current account!
 * @author Pouya Mohammadi - CE@AUT 9829039
 * @version 1.0
 */
public class LogoutLauncher extends Runnable {

    /**
     * Constructor of LogoutLauncher
     */
    public LogoutLauncher(){
        threadName = "LogoutLauncher";
    }

    /**
     * This method runs sequence of log out
     */
    @Override
    public void run() {
        printTraceMessage("In log out...");
        Platform.runLater(() -> {
            OnWaitLoader.getOnWaitLoader().display(MenuDataContainer.getMenuDataContainer().getRootScene());
        });
        MusicPlayer.getMusicPlayer().play(Musics.BEFORE_MAIN_MENU);
        Platform.runLater(() -> {
            MenuDataContainer.getMenuDataContainer().getRootPane().getChildren().remove(
                    MenuDataContainer.getMenuDataContainer().getProfilePopupMenu()
            );
            MenuDataContainer.getMenuDataContainer().getRootPane().getChildren().remove(
                    MenuDataContainer.getMenuDataContainer().getMainMenuRootGroup()
            );
        });
        UserAccountContainer.getUserAccountContainer().setAccount(null);
        new FileConfig().write(FileConfig.ACCOUNT_FILENAME, "");
        new FileConfig().write(FileConfig.ACCOUNT_PASSWORD, "");
        new MainGameLauncher().start();
        this.shutdown();
    }

}
