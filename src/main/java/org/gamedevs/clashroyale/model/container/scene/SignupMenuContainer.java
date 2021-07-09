package org.gamedevs.clashroyale.model.container.scene;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

/**
 * This class contains signup menu data!
 * @author Pouya Mohammadi - CE@AUT 9829039
 * @version 1.0
 */
public class SignupMenuContainer {

    /**
     * Only instance of SignupMenuContainer,
     * singleton pattern.
     */
    private static SignupMenuContainer instance = null;

    /**
     * rootScene;
     */
    private Scene rootScene;
    /**
     * signup menu anchor pane container
     */
    private AnchorPane signupMenu;

    /**
     * Constructor of SignupMenuContainer
     */
    private SignupMenuContainer(){
        rootScene = null;
        signupMenu = null;
    }

    // Getters
    public Scene getRootScene() {
        return rootScene;
    }
    public AnchorPane getSignupMenu() {
        return signupMenu;
    }

    // Setters
    public void setRootScene(Scene rootScene) {
        this.rootScene = rootScene;
    }
    public void setSignupMenu(AnchorPane signupMenu) {
        this.signupMenu = signupMenu;
    }

    /**
     * @return only instance of this class
     */
    public static SignupMenuContainer getMenuData() {
        if(instance == null)
            instance = new SignupMenuContainer();
        return instance;
    }

}
