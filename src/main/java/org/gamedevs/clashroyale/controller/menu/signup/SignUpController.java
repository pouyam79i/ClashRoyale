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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.gamedevs.clashroyale.model.account.AccountBuilder;
import org.gamedevs.clashroyale.model.account.AccountLoader;
import org.gamedevs.clashroyale.model.loader.view.OnWaitLoader;

/**
 * Controller for login scene
 * @author Pouya Mohammadi - Hosna Hoseini
 *         9829039 -CE@AUT   9823010 -CE@AUT
 * @version 1.1
 */
public class SignUpController implements Initializable {

    @FXML
    private Button loginButton;
    @FXML
    private Button signUpButton;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField usernameField;
    @FXML
    private Label errorLabelLogin;
    @FXML
    private Label errorLabelSignup;
    @FXML
    private Label tryAgainLabel;

    // Fade out transition for error label - login
    private FadeTransition fadeOutErrorLabelLogin = new FadeTransition(Duration.millis(5000), errorLabelLogin);
    // Fade out transition for error label - signup
    private FadeTransition fadeOutErrorLabelSignup = new FadeTransition(Duration.millis(5000), errorLabelSignup);
    // Fade out transition for try again label
    private FadeTransition fadeOutTryAgainLabel = new FadeTransition(Duration.millis(5000), tryAgainLabel);

    /**
     * Initialized requirements for animations here
     * @param url 'not used'
     * @param resourceBundle 'not used'
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        errorLabelSignup.setVisible(false);
        errorLabelLogin.setVisible(false);
        tryAgainLabel.setVisible(false);
        fadeOutErrorLabelSignup.setNode(errorLabelSignup);
        fadeOutErrorLabelSignup.setFromValue(1.0);
        fadeOutErrorLabelSignup.setToValue(0);
        fadeOutErrorLabelLogin.setNode(errorLabelLogin);
        fadeOutErrorLabelLogin.setFromValue(1.0);
        fadeOutErrorLabelLogin.setToValue(0);
        fadeOutTryAgainLabel.setNode(tryAgainLabel);
        fadeOutTryAgainLabel.setFromValue(1.0);
        fadeOutTryAgainLabel.setToValue(0);
    }

    /**
     * login action handler
     * @param event 'not used'
     * @throws IOException if failed
     */
    @FXML
    void loginHandling(ActionEvent event) throws IOException {
        // Using account loader
        OnWaitLoader onWaitLoader = OnWaitLoader.getOnWaitLoader();
        onWaitLoader.display(loginButton.getScene());
        Thread thread = (new Thread(() -> {
            AccountLoader.getAccountLoader().loadAccount(usernameField.getText(), passwordField.getText());
            Platform.runLater(() -> {
                onWaitLoader.disappear();
            });
            if(!AccountLoader.getAccountLoader().isAccountLoaded()){
                Platform.runLater(() -> {
                    errorLabelSignup.setVisible(false);
                    errorLabelLogin.setVisible(true);
                    tryAgainLabel.setVisible(true);
                    fadeOutErrorLabelLogin.playFromStart();
                    fadeOutTryAgainLabel.playFromStart();
                });
            }
            // Do loading process
        }));
        thread.setDaemon(true);
        thread.start();
    }

    /**
     * sign up action handler
     * @param event 'not used'
     * @throws IOException if failed
     */
    @FXML
    void signupHandling(ActionEvent event) throws IOException {
        // Using account builder
        OnWaitLoader onWaitLoader = OnWaitLoader.getOnWaitLoader();
        onWaitLoader.display(signUpButton.getScene());
        Thread thread = (new Thread(() -> {
            AccountBuilder.getAccountBuilder().buildNewAccount(usernameField.getText(), passwordField.getText());
            Platform.runLater(() -> {
                onWaitLoader.disappear();
            });
            if(!AccountBuilder.getAccountBuilder().isAccountLoaded()){
                Platform.runLater(() -> {
                    errorLabelSignup.setVisible(true);
                    errorLabelLogin.setVisible(false);
                    tryAgainLabel.setVisible(true);
                    fadeOutErrorLabelSignup.playFromStart();
                    fadeOutTryAgainLabel.playFromStart();
                });
            }
            // Do loading process
        }));
        thread.setDaemon(true);
        thread.start();
    }

}
