package org.gamedevs.clashroyale.controller.menu.main;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
 * Profile view popup handler
 * @author Pouya Mohammadi - CE@AUT 9829039
 * @version 1.0
 */
public class ProfilePopup implements Initializable {

    private static ProfilePopup profilePopup = null;

    /**
     * With this button we return to main menu.
     */
    @FXML
    private Button backBtn;
    @FXML
    private TextField nameTF;
    @FXML
    private TextField cupsTF;
    @FXML
    private ImageView levelImg;
    @FXML
    private ImageView arenaImg;
    @FXML
    private ProgressBar xpBar;

    private TextField nameTFUpdatable;
    private TextField cupsTFUpdatable;
    private ProgressBar xpBarUpdatable;
    private ImageView leveImgUpdatable;
    private ImageView arenaImgUpdatable;

    /**
     * Returns back to battle menu,
     * also removes the popup menu from root scene.
     */
    @FXML
    public void backToMain(){
        AnchorPane battleMainRoot = (AnchorPane) backBtn.getScene().getRoot();
        battleMainRoot.getChildren().remove(MenuDataContainer.getMenuDataContainer().getProfilePopupMenu());
    }

    private void setLevel(){
        int xp = UserAccountContainer.getUserAccountContainer().getAccount().getTotalXP();
        if(xp < 1000){
            leveImgUpdatable.setImage(GameIconContainer.getGameIconContainer().getLevelImages().get(0));
            xpBarUpdatable.setProgress((xp)/1000.0);
            arenaImgUpdatable.setImage(GameIconContainer.getGameIconContainer().getArenaImages().get(0));
        }
        else if(xp < 6000){
            leveImgUpdatable.setImage(GameIconContainer.getGameIconContainer().getLevelImages().get(1));
            xpBarUpdatable.setProgress((xp - 1000)/5000.0);
            arenaImgUpdatable.setImage(GameIconContainer.getGameIconContainer().getArenaImages().get(1));
        }
        else if(xp < 16000){
            leveImgUpdatable.setImage(GameIconContainer.getGameIconContainer().getLevelImages().get(2));
            xpBarUpdatable.setProgress((xp - 6000)/10000.0);
            arenaImgUpdatable.setImage(GameIconContainer.getGameIconContainer().getArenaImages().get(0));
        }
        else if(xp < 36000){
            leveImgUpdatable.setImage(GameIconContainer.getGameIconContainer().getLevelImages().get(3));
            xpBarUpdatable.setProgress((xp - 16000)/20000.0);
            arenaImgUpdatable.setImage(GameIconContainer.getGameIconContainer().getArenaImages().get(1));
        }
        else if(xp < 66000){
            leveImgUpdatable.setImage(GameIconContainer.getGameIconContainer().getLevelImages().get(4));
            xpBarUpdatable.setProgress((xp - 36000)/30000.0);
            arenaImgUpdatable.setImage(GameIconContainer.getGameIconContainer().getArenaImages().get(2));
        }
        else if(xp < 106000){
            leveImgUpdatable.setImage(GameIconContainer.getGameIconContainer().getLevelImages().get(5));
            xpBarUpdatable.setProgress((xp - 66000)/40000.0);
            arenaImgUpdatable.setImage(GameIconContainer.getGameIconContainer().getArenaImages().get(3));
        }
        nameTFUpdatable.setText(UserAccountContainer.getUserAccountContainer().getAccount().getUsername());
    }

    public void init(){
        setLevel();
    }

    private void setNameTFUpdatable(TextField nameTFUpdatable) {
        this.nameTFUpdatable = nameTFUpdatable;
    }
    private void setCupsTFUpdatable(TextField cupsTFUpdatable) {
        this.cupsTFUpdatable = cupsTFUpdatable;
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

    public static ProfilePopup getProfilePopup() {
        if(profilePopup == null)
            profilePopup = new ProfilePopup();
        return profilePopup;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getProfilePopup().setNameTFUpdatable(nameTF);
        getProfilePopup().setCupsTFUpdatable(cupsTF);
        getProfilePopup().setLeveImgUpdatable(levelImg);
        getProfilePopup().setArenaImgUpdatable(arenaImg);
        getProfilePopup().setXpBarUpdatable(xpBar);
    }

}
