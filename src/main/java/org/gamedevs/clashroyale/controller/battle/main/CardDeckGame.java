package org.gamedevs.clashroyale.controller.battle.main;

import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import org.gamedevs.clashroyale.model.game.battle.tools.Elixir;

public class CardDeckGame {

    @FXML
    private GridPane cardGridPane;

    @FXML
    private Label elixirLabel;

    @FXML
    private ImageView nextCardImageView;

    @FXML
    private ProgressBar elixirProgressBar;

    private DoubleProperty value = Elixir.getPlayer1Elixir().elixirValueProperty();

    public void initialize(){
        elixirProgressBar.progressProperty().bind(value.divide(10));
        elixirLabel.textProperty().bind(value.asString("%.0f"));
    }
}
