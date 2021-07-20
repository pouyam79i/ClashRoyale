package org.gamedevs.clashroyale.controller.menu.main;

import animatefx.animation.BounceIn;
import animatefx.animation.FadeIn;
import animatefx.animation.FadeOut;
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
    private Label errorLabel;
    @FXML
    private Label errorLabel2;
    @FXML
    private ProgressBar xpBar;

    // Updatable properties
    private TextField coinsUpdatable;
    private Label xpLabelUpdatable;
    private ProgressBar xpBarUpdatable;
    private ImageView leveImgUpdatable;
    private ImageView arenaImgUpdatable;
    private Label errorLabelUpdatable;
    private Label errorLabel2Updatable;

    // Other fields
    private int currentXP;
    private double levelUpXP;

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
        getMainBattle().setErrorLabelUpdatable(errorLabel);
        getMainBattle().setErrorLabel2Updatable(errorLabel2);
        getMainBattle().setLevelUpXP(1);
        getMainBattle().setCurrentXP(1);
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
     */
    public void updateXP(int additionalXP){
        int currentXP = getMainBattle().getCurrentXP();
        double levelUpXP = getMainBattle().getLevelUpXP();
        Thread thread = (new Thread(() -> {
            for (int i = 1; i <= additionalXP; i++){
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ignored) {}
                if((i + currentXP) >= ((int) levelUpXP)){
                    Platform.runLater(() -> {
                        setLevel();
                        new BounceIn(leveImgUpdatable).play();
                    });
                    return;
                }
                else {
                    String label = (i + currentXP) + "/" + ((int) levelUpXP);
                    int finalI = i;
                    Platform.runLater(() -> {
                        xpLabelUpdatable.setText(label);
                        xpBarUpdatable.setProgress(((finalI + currentXP) / levelUpXP));
                    });
                }
            }
            Platform.runLater(() -> {
                setLevel();
                new BounceIn(leveImgUpdatable).play();
            });
        }));
        thread.setDaemon(true);
        thread.start();
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
     * This method displays error label,
     * when failed to bring up battle field!
     */
    public void displayErrorLabel(){
        Thread thread = (new Thread(() -> {
            Platform.runLater(() -> {
                errorLabelUpdatable.setVisible(true);
                errorLabel2Updatable.setVisible(true);
                new FadeIn(errorLabelUpdatable).play();
                new FadeIn(errorLabel2Updatable).play();
            });
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ignored) {}
            Platform.runLater(() -> {
                new FadeOut(errorLabelUpdatable).play();
                new FadeOut(errorLabel2Updatable).play();
            });
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ignored) {}
            Platform.runLater(() -> {
                errorLabelUpdatable.setVisible(false);
                errorLabel2Updatable.setVisible(false);
            });
        }));
        thread.setDaemon(true);
        thread.start();
    }

    /**
     * Brings battle popup
     */
    @FXML
    private void bringBattlePopup(){
        Thread thread = (new Thread(() -> {
            new BounceIn(battleBtn).play();
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
        new BounceIn(profileBtn).play();
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
    private void setLevel() {
        int level = UserAccountContainer.getUserAccountContainer().getAccount().getLevel();
        int currentXP = UserAccountContainer.getUserAccountContainer().getAccount().getCurrentLevelXP();
        double levelUpXP = UserAccountContainer.getUserAccountContainer().getAccount().getLevelUpXP();
        getMainBattle().setLevelUpXP(levelUpXP);
        getMainBattle().setCurrentXP(currentXP);
        String labelText = "";
        leveImgUpdatable.setImage(GameIconContainer.getGameIconContainer().getLevelImages().get(level - 1));
        arenaImgUpdatable.setImage(GameIconContainer.getGameIconContainer().getArenaImages().get(level - 1));
        if (level < 6) {
            labelText = currentXP + "/" + ((int) levelUpXP);
            xpBarUpdatable.setProgress(currentXP / levelUpXP);
        }else {
            labelText = "" + currentXP;
            xpBarUpdatable.setProgress(1);
        }
        xpLabelUpdatable.setText(labelText);
    }

    // Getters
    private int getCurrentXP() {
        return currentXP;
    }
    private double getLevelUpXP() {
        return levelUpXP;
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
    private void setCurrentXP(int currentXP) {
        this.currentXP = currentXP;
    }
    private void setLevelUpXP(double levelUpXP) {
        this.levelUpXP = levelUpXP;
    }
    private void setErrorLabelUpdatable(Label errorLabelUpdatable) {
        this.errorLabelUpdatable = errorLabelUpdatable;
    }
    private void setErrorLabel2Updatable(Label errorLabel2Updatable) {
        this.errorLabel2Updatable = errorLabel2Updatable;
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
