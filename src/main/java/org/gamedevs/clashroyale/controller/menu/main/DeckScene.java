package org.gamedevs.clashroyale.controller.menu.main;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.gamedevs.clashroyale.model.account.Account;
import org.gamedevs.clashroyale.model.cards.Card;
import org.gamedevs.clashroyale.model.cards.CardName;
import org.gamedevs.clashroyale.model.cards.soldier.Soldier;
import org.gamedevs.clashroyale.model.container.gamedata.CardImageContainer;
import org.gamedevs.clashroyale.model.container.gamedata.UserAccountContainer;
import org.gamedevs.clashroyale.model.loader.file.MenuFileLoader;
import org.gamedevs.clashroyale.model.utils.io.AccountIO;


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

    //updatable grid pane
    private static GridPane playCardGridPaneUpdatable = new GridPane();

    //updatable grid pane
    private static GridPane availableCardGridPaneUpdatable = new GridPane();

    //player who uses this scene currently
    private Account account;

    //previous cardView that player chose
    private CardView source;

    //new cardView that player want to put source in it
    private CardView destination;

    //instance of deck scene
    private static DeckScene instance = null;

    /**
     * Constructor
     */
    public DeckScene() {
    }

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

            destination = ((CardView) event.getSource());
            updateAccountDeck(((CardView) event.getSource()).getParent());
            updateGrids(event);

        }
    };

    /**
     * update grid panes info in GUI
     * @param event put card event
     */
    private void updateGrids(DragEvent event) {
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
        Card tempCard = destination.getCard();
        ((CardView) event.getSource()).setCard(source.getCard());
        source.setCard(tempCard);
    }

    /**
     * update deck in account
     * @param sourceGrid sourceGridPane
     */
    private void updateAccountDeck(Parent sourceGrid) {

        if (sourceGrid == playCardGridPane || sourceGrid == playCardGridPaneUpdatable) {
            account.getDeckContainer().removeCard(destination.getCard());
            account.getDeckContainer().addCard(source.getCard());
            account.getDeckAvailable().addCard(destination.getCard());
            account.getDeckAvailable().removeCard(source.getCard());
        } else {
            account.getDeckAvailable().removeCard(destination.getCard());
            account.getDeckAvailable().addCard(source.getCard());
            account.getDeckContainer().addCard(destination.getCard());
            account.getDeckContainer().removeCard(source.getCard());
        }

        writeNewInfoInFile();

    }

    /**
     * update deck in file
     */
    private void writeNewInfoInFile() {
        Thread thread = new Thread() {
            @Override
            public void start() {
                AccountIO.getAccountIO().removeFileInfo(account.getUsername() + ".bin");
                AccountIO.getAccountIO().singleObjectFileWriter(account.getUsername() + ".bin", account);
            }
        };
        thread.start();
    }

    /**
     * An event handler to make an image acceptable for drag
     */
    private EventHandler<DragEvent> dragOver = new EventHandler<DragEvent>() {
        @Override
        public void handle(DragEvent event) {
            event.acceptTransferModes(TransferMode.MOVE);
        }
    };

    public void initialize() {
        setAvailableCardGridPaneUpdatable(availableCardGridPane);
        setPlayCardGridPaneUpdatable(playCardGridPane);
    }

    /**
     * fill the grids by player cards
     */
    public void init() {
        initAccount();
        initPlayCards();
        initAvailableCards();

    }

    /**
     * initialize account
     */
    private void initAccount() {
        account = UserAccountContainer.getUserAccountContainer().getAccount();
    }

    /**
     * load and put play card pic into top grid
     */
    private void initPlayCards() {

        int i = 0;
        for (Card card : account.getDeckContainer().getDeck()) {
            CardView cardImageView = new CardView(card);
            playCardGridPaneUpdatable.add(cardImageView, i % 4, i / 4);
            i++;
        }

    }

    /**
     * put available card image into bottom grid
     */
    private void initAvailableCards() {

        int i = 0;

        for (Card card : account.getDeckAvailable().getDeck()) {
            CardView cardImageView = new CardView(card);
            availableCardGridPaneUpdatable.add(cardImageView, i % 4, i / 4);
            i++;
        }


    }

    /**
     * get instance of deck scene
     * @return DeckScene
     */
    public static DeckScene getInstance() {
        if (instance == null) {
            instance = new DeckScene();
        }
        return instance;

    }

    //setter
    public void setPlayCardGridPaneUpdatable(GridPane playCardGridPaneUpdatable) {
        this.playCardGridPaneUpdatable = playCardGridPaneUpdatable;
    }
    public void setAvailableCardGridPaneUpdatable(GridPane availableCardGridPaneUpdatable) {
        this.availableCardGridPaneUpdatable = availableCardGridPaneUpdatable;
    }

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
            imageView.setImage(CardImageContainer.getCardImageContainer().getCardImage(card.getCardName()));
            progressBar = new ProgressBar(0.5);

            //set size
            imageView.setFitHeight(105);
            imageView.setFitWidth(85);
            progressBar.setPrefWidth(85);
            progressBar.setPrefHeight(15);

            //add to vbox
            getChildren().add(imageView);
            getChildren().add(progressBar);

            addEventFilter(MouseEvent.DRAG_DETECTED, pickCard);
            addEventFilter(DragEvent.DRAG_DROPPED, putCard);
            addEventFilter(DragEvent.DRAG_OVER, dragOver);

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
