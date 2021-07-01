package org.gamedevs.clashroyale.controller.menu.main;

import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import org.gamedevs.clashroyale.model.CardImageView;
import org.gamedevs.clashroyale.model.CardsImage;
import org.gamedevs.clashroyale.model.utils.console.Console;

import java.util.ArrayList;

/**
 * Controller for Deck scene in menu
 * @author Pouya Mohammadi - Hosna Hoseini
 *         9829039 -CE@AUT   9823010 -CE@AUT
 * @version 1.0
 */
public class DeckScene {
//    GameManager gameManager = new GameManager();


    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    private GridPane playCardGridPane;

    @FXML
    private GridPane choicesCardGridPane;

    @FXML
    private Label battleDeckLabel;

    @FXML
    private Label cardCollectionLabel;

    private ImageView source;


    public void initialize() {

        initPlayCards();
        initAvailableCards();

    }

    /**
     * put play card pic into scene
     */
    private void initPlayCards() {
        ObservableList<Node> gridCells = playCardGridPane.getChildren();

//        for(int j = 0; j < 12 ; j++) {
//            CardImageView cardImageView = new CardImageView();
//            cardImageView.
//            choicesCardGridPane.getChildren().add();
//
//        }
            int i = 0;
        ////////this part should be omitted when the card class has been made////////
        ArrayList<CardsImage> playCards = new ArrayList<>();
        playCards.add(CardsImage.ARROWS);
        playCards.add(CardsImage.ARCHERS);
        playCards.add(CardsImage.BARBARIANS);
        playCards.add(CardsImage.BABY_DRAGON);
        playCards.add(CardsImage.FIREBALL);
        playCards.add(CardsImage.GIANT);
        playCards.add(CardsImage.INFERNO_TOWER);
        playCards.add(CardsImage.CANNON);
        for (CardsImage cardsImage : playCards) {
            ((ImageView) gridCells.get(i)).setImage(new Image(cardsImage.getUrl()));
            i++;
        }
        ////////////////////////////////////////////////////////////////////////////

//        for (Card card : playCards()) {
//            ((ImageView) gridCells.get(i)).setImage(new Image(card.getImage().getUrl()));
//            i++;
//        }
    }

    /**
     * put available card pic into scene
     */
    private void initAvailableCards() {
        int i = 0;
        ObservableList<Node> gridCells;
        ArrayList<CardsImage> allCards = new ArrayList<>();

        allCards.add(CardsImage.MINI_PEKKA);
        allCards.add(CardsImage.RAGE);
        allCards.add(CardsImage.VALKYRIE);
        allCards.add(CardsImage.WIZARD);

        gridCells = choicesCardGridPane.getChildren();
        for (CardsImage cardsImage : allCards) {
            ((ImageView) gridCells.get(i)).setImage(new Image(cardsImage.getUrl()));
//            if(isNotInAvailableCards(cardsImage)){
            ColorAdjust colorAdjust = new ColorAdjust(0,-50,0,0);
            gridCells.get(i).setEffect(colorAdjust);
            i++;
        }
    }

//    /**
//     * to check if the card is available for this player or not
//     * @param cardsImage card
//     * @return true if it is NOT available
//     */
//    private boolean IsNotInAvailableCards(CardsImage cardsImage) {
//    }

    @FXML
    void pickCard(MouseEvent event) {
        source = (ImageView) event.getSource();
        Image image = ((ImageView) event.getSource()).getImage();
        Dragboard db = ((ImageView) event.getSource()).startDragAndDrop(TransferMode.MOVE);
        ClipboardContent cc = new ClipboardContent();
        cc.putImage(image);
        db.setContent(cc);
        event.consume();
    }

    @FXML
    void dragOver(DragEvent event) {
        event.acceptTransferModes(TransferMode.MOVE);
    }

    @FXML
    void putImage(DragEvent event) {
        Image oldImage = ((ImageView) event.getSource()).getImage();
        source.setImage(null);
        if(oldImage != null)
            source.setImage(oldImage);
        Image newImage = event.getDragboard().getImage();
        ((ImageView) event.getSource()).setImage(newImage);

    }

//    /**
//     * exit from this scene
//     */
//    private void exit() {
//        gameManager.getPlayer.updatePlayCard();
//        Stage stage;
//        stage = (Stage) usernameField.getScene().getWindow();
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(getClass().getResource("../../resources/org/gamedevs/clashroyale/view/fxml/menu.fxml"));
//        try {
//            loader.load();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        MenuController menuController = loader.getController();
//        Parent root = loader.getRoot();
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }

}
