package org.gamedevs.clashroyale.controller.menu.signup;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import org.gamedevs.clashroyale.model.account.AccountLoader;
import org.gamedevs.clashroyale.model.loader.view.OnWaitLoader;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for login scene
 *
 * @author Pouya Mohammadi - Hosna Hoseini
 * 9829039 -CE@AUT   9823010 -CE@AUT
 * @version 1.0
 */
public class SignUpController implements Initializable {

    @FXML
    private Button loginButton;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField usernameField;
    @FXML
    private Label errorLabel;
    @FXML
    private Label tryAgainLabel;

    private FadeTransition fadeOutErrorLabel = new FadeTransition(Duration.millis(5000), errorLabel);
    private FadeTransition fadeOutTryAgainLabel = new FadeTransition(Duration.millis(5000), errorLabel);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        errorLabel.setVisible(false);
        tryAgainLabel.setVisible(false);
        fadeOutErrorLabel.setNode(errorLabel);
        fadeOutErrorLabel.setFromValue(1.0);
        fadeOutErrorLabel.setToValue(0);
        fadeOutTryAgainLabel.setNode(tryAgainLabel);
        fadeOutTryAgainLabel.setFromValue(1.0);
        fadeOutTryAgainLabel.setToValue(0);
    }

    @FXML
    void loginHandling(ActionEvent event) throws IOException {
        // Using account loader
        OnWaitLoader onWaitLoader = new OnWaitLoader();
        onWaitLoader.display(loginButton.getScene());
        Thread thread = (new Thread(() -> {
            AccountLoader.getAccountLoader().loader(usernameField.getText(), passwordField.getText());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ignored) {}
            Platform.runLater(() -> {
                onWaitLoader.disappear();
            });
            if(!AccountLoader.getAccountLoader().isAccountLoaded()){
                Platform.runLater(() -> {
                    errorLabel.setVisible(true);
                    tryAgainLabel.setVisible(true);
                    fadeOutErrorLabel.playFromStart();
                    fadeOutTryAgainLabel.playFromStart();
                });
            }
            // Do loading process
        }));
        thread.setDaemon(true);
        thread.start();

    }

    @FXML
    void signupHandling(ActionEvent event) throws IOException {
        // Using account builder

    }

}


