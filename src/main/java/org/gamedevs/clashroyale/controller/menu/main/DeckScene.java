package org.gamedevs.clashroyale.controller.menu.main;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.gamedevs.clashroyale.model.Card.Card;
import org.gamedevs.clashroyale.model.Card.Null;
import org.gamedevs.clashroyale.model.Player;

import java.io.IOException;
import java.util.ArrayList;


/**
 * Controller for Deck scene in menu
 *
 * @author Hosna Hoseini
 * 9823010 -CE@AUT
 * @version 1.0
 */
public class DeckScene {

    //top grid pane which show cards which player wants to play with them
    @FXML
    private GridPane playCardGridPane;

    //bottom grid pane which show cards which player can choose to play which
    @FXML
    private GridPane availableCardGridPane;

    //player who uses this scene currently
    private Player player;

    //previous card that player chose
    private CardView source;

    //model of deck scene
//    private DeckSceneModel model = new DeckSceneModel();

    /**
     * an event handler which called to pick a card by dragging it
     */
    private EventHandler<MouseEvent> pickCard = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            source = (CardView) event.getSource();
            Image image = source.getImageView().getImage();
            Dragboard db = (source.getImageView()).startDragAndDrop(TransferMode.MOVE);
            ClipboardContent cc = new ClipboardContent();
            cc.putImage(image);
            db.setContent(cc);
            event.consume();
        }
    };

    /**
     * An event handler which called to drop a card and change it with the previous card
     */
    private EventHandler<DragEvent> putCard = new EventHandler<DragEvent>() {
        @Override
        public void handle(DragEvent event) {

            //change source image
            Image oldImage = ((CardView) event.getSource()).getImageView().getImage();
            source.getImageView().setImage(oldImage);

            //change destination image
            Image newImage = event.getDragboard().getImage();
            ((CardView) event.getSource()).getImageView().setImage(newImage);

            //change progress bar
            double tempProgressBar = ((CardView) event.getSource()).getProgressBar();
            ((CardView) event.getSource()).setProgressBar(source.getProgressBar());
            source.setProgressBar(tempProgressBar);

            //change id
            Card tempCard = ((CardView) event.getSource()).getCard();
            ((CardView) event.getSource()).setCard(source.getCard());
            source.setCard(tempCard);
        }
    };

    /**
     * An event handler to make an image acceptable for drag
     */
    private EventHandler<DragEvent> dragOver = new EventHandler<DragEvent>() {
        @Override
        public void handle(DragEvent event) {
            event.acceptTransferModes(TransferMode.MOVE);
        }
    };

//    /**
//     * constructor
//     *
//     */
//    public DeckScene() {
//        player = model.getPlayer();
//    }

    /**
     * fill the grids by player cards
     */
    public void initialize() {
        initPlayCards();
        initAvailableCards();
    }

//    /**
//     * exit from this stage
//     */
//    public void exit() {
//        givePlayCardsToModel();
//        Stage stage;
//        stage = (Stage) playCardGridPane.getScene().getWindow();
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

    /**
     * load and put play card pic into top grid
     */
    private void initPlayCards() {
        int i = 0;

        for (Card card : player.getPlayCards()) {
            CardView cardImageView = new CardView(card);
            playCardGridPane.add(cardImageView, i % 4, i / 4);
            i++;
        }
    }

    /**
     * put available card image into bottom grid
     */
    private void initAvailableCards() {

        int i = 0;
        for (Card card : player.getAvailableCards()) {
            CardView cardImageView = new CardView(card);
            availableCardGridPane.add(cardImageView, i % 4, i / 4);
            i++;
        }

        //null places
        while (i < 12) {
            CardView cardView = new CardView(new Null());
            cardView.setProgressBar(0);
            availableCardGridPane.add(cardView, i % 4, i / 4);
            i++;
        }

    }

//    /**
//     * update player new card
//     */
//    private void givePlayCardsToModel() {
//        ArrayList<Card> playCard = new ArrayList<>();
//        for(Node node : playCardGridPane.getChildren()){
//            playCard.add(((CardView)node).getCard());
//        }
//        model.setPlayCards(playCard);
//    }

    /**
     * A vbox to show card and progress bar in grid cell
     *
     * @author Hosna Hoseini
     * 9823010 -CE@AUT
     * @version 1.0
     */
    class CardView extends VBox {
        private Card card;
        private ImageView imageView = new ImageView();
        private ProgressBar progressBar;

        /**
         * constructor to make a new VBox
         *
         * @param card the card which we want to show
         */
        public CardView(Card card) {
            //init info of fields
            this.card = card;
            imageView.setImage(card.getImage());
            progressBar = new ProgressBar(card.getUpdateProgress());

            //set size
            imageView.setFitHeight(105);
            imageView.setFitWidth(85);
            progressBar.setPrefWidth(85);
            progressBar.setPrefHeight(15);

            //add to vbox
            getChildren().add(imageView);
            getChildren().add(progressBar);

            //set event handler if the card is unlock
            if (card.isActive()) {
                addEventFilter(MouseEvent.DRAG_DETECTED, pickCard);
                addEventFilter(DragEvent.DRAG_DROPPED, putCard);
                addEventFilter(DragEvent.DRAG_OVER, dragOver);
            } else {
                ColorAdjust colorAdjust = new ColorAdjust(0, -50, 0, 0);
                setEffect(colorAdjust);
            }
        }

        //Getter
        public ImageView getImageView() {
            return imageView;
        }

        //Getter
        public double getProgressBar() {
            return progressBar.getProgress();
        }

        //Setter
        public void setProgressBar(double value) {
            this.progressBar.setProgress(value);
        }

        //Getter
        public Card getCard() {
            return card;
        }

        //Setter
        public void setCard(Card card) {
            this.card = card;
        }
    }
}
