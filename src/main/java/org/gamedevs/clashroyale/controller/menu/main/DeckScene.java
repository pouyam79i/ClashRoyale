package org.gamedevs.clashroyale.controller.menu.main;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.gamedevs.clashroyale.model.CardsImage;

import java.util.ArrayList;

/**
 * Controller for Deck scene in menu
 *
 * @author Pouya Mohammadi - Hosna Hoseini
 * 9829039 -CE@AUT   9823010 -CE@AUT
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

    protected CardView source;

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

    private EventHandler<DragEvent> putCard = new EventHandler<DragEvent>() {
        @Override
        public void handle(DragEvent event) {

            Image oldImage = ((CardView) event.getSource()).getImageView().getImage();
            source.getImageView().setImage(null);
            if (oldImage != null) {
                source.getImageView().setImage(oldImage);
            }
            Image newImage = event.getDragboard().getImage();
            ((CardView) event.getSource()).getImageView().setImage(newImage);
            double tempProgressBar = ((CardView) event.getSource()).getProgressBar();
            ((CardView) event.getSource()).setProgressBar(source.getProgressBar());
            source.setProgressBar(tempProgressBar);

//            if (((CardView) event.getSource()).getID().equals(CardsImage.NULL.toString()))
//                ((CardView) event.getSource()).visibleProgressBar(false);
//            else
//                ((CardView) event.getSource()).visibleProgressBar(true);
//
//            if (source.getID().equals(CardsImage.NULL.toString()))
//                source.visibleProgressBar(false);
//            else
//                source.visibleProgressBar(true);
        }
    };

    private EventHandler<DragEvent> dragOver = new EventHandler<DragEvent>() {
        @Override
        public void handle(DragEvent event) {
            event.acceptTransferModes(TransferMode.MOVE);
        }
    };

    public void initialize() {
        initPlayCards();
        initAvailableCards();
    }

    /**
     * put play card pic into scene
     */
    private void initPlayCards() {
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
            CardView cardView = new CardView(new Image(cardsImage.getUrl()), true, cardsImage.toString());
            playCardGridPane.add(cardView, i % 4, i / 4);
            i++;
        }
        ////////////////////////////////////////////////////////////////////////////

//        for (Card card : playCards()) {
//            CardImageView cardImageView = new CardImageView(new Image(card.getImage().getUrl()), true);
//            playCardGridPane.add(cardImageView, i % 4, i / 4);
//            i++;
//        }
    }

    /**
     * put available card pic into scene
     */
    private void initAvailableCards() {

        int i = 0;

        ArrayList<CardsImage> allAvailableCards = new ArrayList<>();
        allAvailableCards.add(CardsImage.MINI_PEKKA);
        allAvailableCards.add(CardsImage.RAGE);
        allAvailableCards.add(CardsImage.VALKYRIE);
        allAvailableCards.add(CardsImage.WIZARD);

        for (CardsImage cardsImage : allAvailableCards) {
//            if(isNotInAvailableCards(cardsImage)){
//              CardImageView cardImageView = new CardImageView(new Image(cardsImage.getUrl()), false);
//          }else{
            CardView cardView = new CardView(new Image(cardsImage.getUrl()), true, cardsImage.toString());
//          }
            choicesCardGridPane.add(cardView, i % 4, i / 4);
            i++;
        }

        while (i < 12) {
            CardView cardView = new CardView(new Image(CardsImage.NULL.getUrl()), true, CardsImage.NULL.toString());
            cardView.setProgressBar(0);
            choicesCardGridPane.add(cardView, i % 4, i / 4);
            i++;
        }
//

    }

//    /**
//     * to check if the card is available for this player or not
//     * @param cardsImage card
//     * @return true if it is NOT available
//     */
//    private boolean IsNotInAvailableCards(CardsImage cardsImage) {
//    }


    class CardView extends VBox {
        private String ID;
        private ImageView imageView = new ImageView();
        private ProgressBar progressBar;

        public CardView(Image image, boolean active, String id) {
            this.ID = id;
            imageView.setImage(image);
            progressBar = new ProgressBar(Math.random());
            imageView.setFitHeight(105);
            imageView.setFitWidth(85);
            progressBar.setPrefWidth(85);
            progressBar.setPrefHeight(15);
            getChildren().add(imageView);
            getChildren().add(progressBar);

            if (active) {
                addEventFilter(MouseEvent.DRAG_DETECTED, pickCard);
                addEventFilter(DragEvent.DRAG_DROPPED, putCard);
                addEventFilter(DragEvent.DRAG_OVER, dragOver);
            } else {
                ColorAdjust colorAdjust = new ColorAdjust(0, -50, 0, 0);
                setEffect(colorAdjust);
            }
        }

        public void visibleProgressBar(boolean visible) {
            progressBar.setVisible(visible);
        }

        public ImageView getImageView() {
            return imageView;
        }

        public void setImageView(ImageView imageView) {
            this.imageView = imageView;
        }

        public double getProgressBar() {
            return progressBar.getProgress();
        }

        public void setProgressBar(double value) {
            this.progressBar.setProgress(value);
        }

        public String getID() {
            return ID;
        }
    }
}
