package org.gamedevs.clashroyale.controller.battle.main;

import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import org.gamedevs.clashroyale.model.cards.Card;
import org.gamedevs.clashroyale.model.cards.CardName;
import org.gamedevs.clashroyale.model.container.gamedata.CardImageContainer;
import org.gamedevs.clashroyale.model.container.gamedata.PlayerContainer;
import org.gamedevs.clashroyale.model.container.gamedata.UserAccountContainer;
import org.gamedevs.clashroyale.model.game.battle.tools.CardGenerator;
import org.gamedevs.clashroyale.model.game.battle.tools.Clock;
import org.gamedevs.clashroyale.model.game.battle.tools.Elixir;
import org.gamedevs.clashroyale.model.game.player.Player;
import org.gamedevs.clashroyale.model.utils.console.Console;

import java.util.ArrayList;

public class CardDeckGame {

    @FXML
    private GridPane cardGridPane;
    private static GridPane cardGridPaneUpdatable = new GridPane();

    @FXML
    private Label elixirLabel;
    private static Label elixirLabelUpdatable = new Label();

    @FXML
    private ImageView next;
    private static ImageView nextUpdatable = new ImageView();

    @FXML
    private ProgressBar elixirProgressBar;
    private static ProgressBar elixirProgressBarUpdatable = new ProgressBar();

    private Player player;

    /**
     * an event handler which called to pick a card by dragging it
     */
    private EventHandler<MouseEvent> pickCard = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            CardView source = (CardView) event.getSource();
            Image image = source.getCardImage().getImage();
            Dragboard db = (source.getCardImage()).startDragAndDrop(TransferMode.MOVE);
            ClipboardContent cc = new ClipboardContent();
            cc.putImage(image);
            db.setContent(cc);

//            if(player.drop(,,source.getCard())) {
            //reduce elixir
            player.getElixir().reduceElixir(source.getCard().getCost());

            //remove previous card
            cardGridPaneUpdatable.getChildren().remove(source);

            //get a new card from card generator and add it to deck
            CardView cardImageView = new CardView(player.getCardGenerator().getANewCard(), source.getCol());
            cardGridPaneUpdatable.add(cardImageView, cardImageView.getCol(), 0);

            //change Next card
            nextUpdatable.setImage(CardImageContainer.getCardImageContainer().getCardImage(player.getCardGenerator().getNextCard().getCardName()));

            //add previous card to player possible deck
            player.getCardGenerator().getCompleteDeck().addCard(source.getCard());
//            }

            event.consume();
        }
    };

    public void initialize() {
        setCardGridPaneUpdatable(cardGridPane);
        setElixirLabelUpdatable(elixirLabel);
        setElixirProgressBarUpdatable(elixirProgressBar);
        setNextUpdatable(next);
    }

    /**
     * initialize deck and bind elixir
     */
    public void init() {
        player = PlayerContainer.getPlayerContainer().getPlayer();
        System.out.println(player);
        elixirProgressBarUpdatable.progressProperty().bind(player.getElixir().elixirValueProperty().divide(10));
        elixirLabelUpdatable.textProperty().bind(player.getElixir().elixirValueProperty().asString("%.0f"));
        initPlayCards();

    }

    /**
     * load and put play card pic into top grid
     */
    private void initPlayCards() {
        int i = 0;
        ArrayList<Card> cards = player.getCardGenerator().getInitialCards();
        for (Card card : cards) {
            CardView cardImageView = new CardView(card, i % 4);
            cardGridPaneUpdatable.add(cardImageView, i % 4, 0);
            i++;
        }
        nextUpdatable.setImage(CardImageContainer.getCardImageContainer().getCardImage(player.getCardGenerator().getNextCard().getCardName()));
    }

    public void setCardGridPaneUpdatable(GridPane cardGridPaneUpdatable) {
        this.cardGridPaneUpdatable = cardGridPaneUpdatable;
    }

    public void setElixirLabelUpdatable(Label elixirLabelUpdatable) {
        this.elixirLabelUpdatable = elixirLabelUpdatable;
    }

    public void setElixirProgressBarUpdatable(ProgressBar elixirProgressBarUpdatable) {
        this.elixirProgressBarUpdatable = elixirProgressBarUpdatable;
    }

    public void setNextUpdatable(ImageView nextUpdatable) {
        this.nextUpdatable = nextUpdatable;
    }

    /**
     * A GridPane to show card and progress bar in grid cell
     *
     * @author Hosna Hoseini
     * 9823010 -CE@AUT
     * @version 1.0
     */
    class CardView extends AnchorPane {
        private Card card;
        private ImageView cardImage = new ImageView();
        private int col;


        /**
         * constructor to make a new AnchorPane
         *
         * @param card the card which we want to show
         */
        public CardView(Card card, int col) {
            //init info of fields
            this.card = card;
            cardImage.setImage(CardImageContainer.getCardImageContainer().getCardImage(card.getCardName()));

            this.col = col;

            //set size
            cardImage.setFitHeight(80);
            cardImage.setFitWidth(68);


            //add to AnchorPane
            getChildren().add(cardImage);


            player.getElixir().elixirValueProperty().addListener((observableValue, oldValue, newValue) -> {
                if (newValue.doubleValue() < card.getCost()) {
                    removeEventFilter(MouseEvent.DRAG_DETECTED, pickCard);
                    Effect lockEffect = new ColorAdjust(0, -100, 0, 0);
                    setEffect(lockEffect);
                } else {
                    addEventFilter(MouseEvent.DRAG_DETECTED, pickCard);
                    Effect unlockEffect = new ColorAdjust(0, 0, 0, 0);
                    setEffect(unlockEffect);
                }
            });
        }

        //Getter
        public ImageView getCardImage() {
            return cardImage;
        }

        //Getter
        public Card getCard() {
            return card;
        }

        public int getCol() {
            return col;
        }

        //Setter

        public void setCard(Card card) {
            this.card = card;
        }

        public void setCol(int col) {
            this.col = col;
        }
    }
}
