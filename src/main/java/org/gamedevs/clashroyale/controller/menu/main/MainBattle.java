package org.gamedevs.clashroyale.controller.menu.main;

//import animatefx.animation.*;
import animatefx.animation.BounceIn;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import org.gamedevs.clashroyale.MainConfig;
import org.gamedevs.clashroyale.model.container.gamedata.GameIconContainer;
import org.gamedevs.clashroyale.model.container.gamedata.UserAccountContainer;
import org.gamedevs.clashroyale.model.container.scene.MenuDataContainer;
import org.gamedevs.clashroyale.model.utils.console.Console;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Main battle menu controller
 * @author Pouya Mohammadi - CE@AUT 9829039
 * @version 1.1
 */
public class MainBattle implements Initializable {

    /**
     * only instance of this class
     */
    private static MainBattle mainBattle = null;

    // fx:id
    @FXML
    private Button battleBtn;
    @FXML
    private Button profileBtn;
    @FXML
    private Button lastGamesBtn;
    @FXML
    private ImageView arenaImg;
    @FXML
    private ImageView levelImg;
    @FXML
    private TextField coins;
    @FXML
    private Label xpLabel;
    @FXML
    private ProgressBar xpBar;

    // Updatable properties
    private TextField coinsUpdatable;
    private Label xpLabelUpdatable;
    private ProgressBar xpBarUpdatable;
    private ImageView leveImgUpdatable;
    private ImageView arenaImgUpdatable;

    /**
     * Initialize the requirements
     * @param url 'not used'
     * @param resourceBundle 'not used'
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getMainBattle().setCoinsUpdatable(coins);
        getMainBattle().setXpLabelUpdatable(xpLabel);
        getMainBattle().setLeveImgUpdatable(levelImg);
        getMainBattle().setArenaImgUpdatable(arenaImg);
        getMainBattle().setXpBarUpdatable(xpBar);
    }

    /**
     * Initializes the basic values,
     * in view properties!
     */
    public void init(){
        setLevel();
    }

    /**
     * updates xp
     * this method does not effect account information!
     * @param additionalXP will be added to xp
     * @param currentXP is the amount of current xp
     */
    public void updateXP(int additionalXP, int currentXP){

    }

    /**
     * updates number of coins
     * this method does not effect account information!
     * @param additionalCoins will be added to coins text field
     */
    public void updateCoins(int additionalCoins){
        int currentCoins;
        try {
            currentCoins = Integer.parseInt(coinsUpdatable.getText());
        }catch (Exception e){
            Console.getConsole().printTracingMessage("Failed to get current number of coins! failed to update coins");
            return;
        }
        Thread coinThread = (new Thread(() -> {
            for(int i = currentCoins; i <= (currentCoins + additionalCoins); i++){
                try {
                    Thread.sleep(1);
                } catch (InterruptedException ignored) {}
                int finalI = i;
                Platform.runLater(() -> {
                    coinsUpdatable.setText(finalI + "");
                });
            }
        }));
        coinThread.setDaemon(true);
        coinThread.start();
    }

    /**
     * Brings battle popup
     */
    @FXML
    private void bringBattlePopup(){
        Thread thread = (new Thread(() -> {
//            new BounceIn(battleBtn).play();
            try {
                Thread.sleep(MainConfig.STD_BUTTON_ANIMATION_LATENCY);
            } catch (InterruptedException ignored) {}
            Platform.runLater(() -> {
                Scene mainBattleMenuScene = battleBtn.getScene();
                AnchorPane mainBattleMenu = (AnchorPane) mainBattleMenuScene.getRoot();
                mainBattleMenu.getChildren().add(MenuDataContainer.getMenuDataContainer().getBattlePopupMenu());
            });
        }));
        thread.setDaemon(true);
        thread.start();
    }

    /**
     * Brings profile view popup
     */
    @FXML
    private void bringProfilePopup(){
//        new BounceIn(profileBtn).play();
        Scene mainBattleMenuScene = profileBtn.getScene();
        AnchorPane mainBattleMenu = (AnchorPane) mainBattleMenuScene.getRoot();
        mainBattleMenu.getChildren().add(MenuDataContainer.getMenuDataContainer().getProfilePopupMenu());
    }

    /**
     * Brings last game popup
     */
    @FXML
    private void bringLastGamesPopup(){
        new BounceIn(lastGamesBtn).play();
    }

    /**
     * Sets level xp and images
     * this method does not effect account information!
     */
    private void setLevel(){
        int xp = UserAccountContainer.getUserAccountContainer().getAccount().getTotalXP();
        String labelText = "";
        if(xp < 1000){
            leveImgUpdatable.setImage(GameIconContainer.getGameIconContainer().getLevelImages().get(0));
            labelText = xp + "/" + 1000;
            xpBarUpdatable.setProgress((xp)/1000.0);
            arenaImgUpdatable.setImage(GameIconContainer.getGameIconContainer().getArenaImages().get(0));
        }
        else if(xp < 6000){
            leveImgUpdatable.setImage(GameIconContainer.getGameIconContainer().getLevelImages().get(1));
            labelText = (xp - 1000) + "/" + 5000;
            xpBarUpdatable.setProgress((xp - 1000)/5000.0);
            arenaImgUpdatable.setImage(GameIconContainer.getGameIconContainer().getArenaImages().get(1));
        }
        else if(xp < 16000){
            leveImgUpdatable.setImage(GameIconContainer.getGameIconContainer().getLevelImages().get(2));
            labelText = (xp - 6000) + "/" + 10000;
            xpBarUpdatable.setProgress((xp - 6000)/10000.0);
            arenaImgUpdatable.setImage(GameIconContainer.getGameIconContainer().getArenaImages().get(0));
        }
        else if(xp < 36000){
            leveImgUpdatable.setImage(GameIconContainer.getGameIconContainer().getLevelImages().get(3));
            labelText = (xp - 16000) + "/" + 20000;
            xpBarUpdatable.setProgress((xp - 16000)/20000.0);
            arenaImgUpdatable.setImage(GameIconContainer.getGameIconContainer().getArenaImages().get(1));
        }
        else if(xp < 66000){
            leveImgUpdatable.setImage(GameIconContainer.getGameIconContainer().getLevelImages().get(4));
            labelText = (xp - 36000) + "/" + 30000;
            xpBarUpdatable.setProgress((xp - 36000)/30000.0);
            arenaImgUpdatable.setImage(GameIconContainer.getGameIconContainer().getArenaImages().get(2));
        }
        else if(xp < 106000){
            leveImgUpdatable.setImage(GameIconContainer.getGameIconContainer().getLevelImages().get(5));
            labelText = (xp - 66000) + "/" + 40000;
            xpBarUpdatable.setProgress((xp - 66000)/40000.0);
            arenaImgUpdatable.setImage(GameIconContainer.getGameIconContainer().getArenaImages().get(3));
        }
        xpLabelUpdatable.setText(labelText);
    }

    // Setters
    private void setCoinsUpdatable(TextField coinsUpdatable) {
        this.coinsUpdatable = coinsUpdatable;
    }
    private void setXpLabelUpdatable(Label xpLabelUpdatable) {
        this.xpLabelUpdatable = xpLabelUpdatable;
    }
    private void setLeveImgUpdatable(ImageView leveImgUpdatable) {
        this.leveImgUpdatable = leveImgUpdatable;
    }
    private void setArenaImgUpdatable(ImageView arenaImgUpdatable) {
        this.arenaImgUpdatable = arenaImgUpdatable;
    }
    private void setXpBarUpdatable(ProgressBar xpBarUpdatable) {
        this.xpBarUpdatable = xpBarUpdatable;
    }

    /**
     * @return only instance of this class
     */
    public static MainBattle getMainBattle(){
        if(mainBattle == null)
            mainBattle = new MainBattle();
        return mainBattle;
    }

}
