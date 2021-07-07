package org.gamedevs.clashroyale.controller.menu.signup;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.gamedevs.clashroyale.model.cards.CardDeck;
import org.gamedevs.clashroyale.model.SignUpModel;
import org.gamedevs.clashroyale.model.player.Human;
import org.gamedevs.clashroyale.model.utils.FileIO.FileIO;
import org.gamedevs.clashroyale.model.utils.console.Console;

import java.io.IOException;

/**
 * Controller for login scene
 *
 * @author Pouya Mohammadi - Hosna Hoseini
 * 9829039 -CE@AUT   9823010 -CE@AUT
 * @version 1.0
 */
public class SignUpController {

    @FXML
    private AnchorPane mainAnchorPane;

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

    SignUpModel signUpModel = new SignUpModel();

    public void initialize() {
        errorLabel.setVisible(false);
        tryAgainLabel.setVisible(false);
    }

    @FXML
    void loginHandling(ActionEvent event) throws IOException {
        String pass = passwordField.getText();
        String username = usernameField.getText();
        if (!pass.equals("") && !username.equals("")) {
            if (signUpModel.login(username, pass)) {
                Console.getConsole().println(username + "logged in");
                changeScene(event);
            }else {
                errorLabel.setText("wrong username or password");
                errorLabel.setVisible(true);
                tryAgainLabel.setVisible(true);
                passwordField.setText("");
                usernameField.setText("");
            }
        } else
            tryAgainLabel.setVisible(true);
    }

    @FXML
    void signUpHandling(ActionEvent event) throws IOException {
        String pass = passwordField.getText();
        String username = usernameField.getText();
        if (!pass.equals("") && !username.equals("")) {
            if (signUpModel.signUp(username, pass)) {
                changeScene(event);
                Console.getConsole().println(username + "sign up");
            }else {
                errorLabel.setText("username is already taken");
                errorLabel.setVisible(true);
                tryAgainLabel.setVisible(true);
                passwordField.setText("");
                usernameField.setText("");
            }
        } else
            tryAgainLabel.setVisible(true);


    }

    private void changeScene(ActionEvent event) throws IOException {
        //TODO:change scene
//        Parent root = FXMLLoader.load(getClass().getResource("C:\\Users\\RAI\\Desktop\\university\\term4\\ap\\FinalProject\\ClashRoyale\\ClashRoyale\\src\\main\\resources\\org\\gamedevs\\clashroyale\\view\\fxml\\menu\\main_battle.fxml"));
//        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
    }
}


