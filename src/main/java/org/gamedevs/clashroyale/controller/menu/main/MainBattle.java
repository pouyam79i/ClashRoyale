package org.gamedevs.clashroyale.controller.menu.main;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import org.gamedevs.clashroyale.model.container.gamedata.GameIconContainer;
import org.gamedevs.clashroyale.model.container.gamedata.UserAccountContainer;
import org.gamedevs.clashroyale.model.container.scene.MenuDataContainer;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Main battle menu controller
 * @author Pouya Mohammadi - CE@AUT 9829039
 * @version 1.0
 */
public class MainBattle implements Initializable {

    private static MainBattle mainBattle = null;

    /**
     * Battle button,
     * used to bring battle popup
     */
    @FXML
    private Button battleBtn;
    /**
     * Profile button,
     * using to bring profile popup view
     */
    @FXML
    private Button profileBtn;
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

    private Label xpLabelUpdatable;
    private ProgressBar xpBarUpdatable;
    private ImageView leveImgUpdatable;
    private ImageView arenaImgUpdatable;

    /**
     * Brings battle popup
     */
    @FXML
    private void bringBattlePopup(){
        Scene mainBattleMenuScene = battleBtn.getScene();
        AnchorPane mainBattleMenu = (AnchorPane) mainBattleMenuScene.getRoot();
        mainBattleMenu.getChildren().add(MenuDataContainer.getMenuDataContainer().getBattlePopupMenu());
    }

    /**
     * Brings profile view popup
     */
    @FXML
    private void bringProfilePopup(){
        Scene mainBattleMenuScene = profileBtn.getScene();
        AnchorPane mainBattleMenu = (AnchorPane) mainBattleMenuScene.getRoot();
        mainBattleMenu.getChildren().add(MenuDataContainer.getMenuDataContainer().getProfilePopupMenu());
    }

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

    public void update(){
        setLevel();
    }

    public static MainBattle getMainBattle(){
        if(mainBattle == null)
            mainBattle = new MainBattle();
        return mainBattle;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getMainBattle().setXpLabelUpdatable(xpLabel);
        getMainBattle().setLeveImgUpdatable(levelImg);
        getMainBattle().setArenaImgUpdatable(arenaImg);
        getMainBattle().setXpBarUpdatable(xpBar);
    }
}
