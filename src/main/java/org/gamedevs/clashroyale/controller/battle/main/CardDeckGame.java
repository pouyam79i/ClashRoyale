package org.gamedevs.clashroyale.controller.battle.main;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Effect;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import org.gamedevs.clashroyale.model.cards.Card;
import org.gamedevs.clashroyale.model.container.gamedata.CardImageContainer;
import org.gamedevs.clashroyale.model.container.gamedata.PlayerContainer;
import org.gamedevs.clashroyale.model.container.gamedata.SelectedCardContainer;
import org.gamedevs.clashroyale.model.game.player.Player;

import java.util.ArrayList;
/**
 * controller for card deck in game
 *
 * @author Hosna Hoseini 9823010 -CE@AUT
 * @version 1.0
 */
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

    private CardView selected;
    private Player player;

    /**
     * an event handler which called to pick a card by dragging it
     */
    private EventHandler<MouseEvent> pickCard = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {

            CardView source = (CardView) event.getSource();
            if (source.equals(selected)) {
                selected = null;
                deselectCard(source);

            } else {
                if (selected != null) {
                    deselectCard(selected);
                }
                selectCard(source);
                putCard(source);
            }
        }
    };

    /**
     * check if its possible to put card remove it from deck and produce new one
     *
     * @param source card to put
     */
    private void putCard(CardView source) {

        /**
         * listener which calls when a card put successfully in game
         * so it remove card from deck and reduce elixir
         */
        ChangeListener put = new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean newValue) {
                if (newValue) {
                    //reduce Elixir
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

                }

            }
        };


        /**
         * listener to cancel previous (put) listener
         * after handling one card the previous (put) listener  should canceled
         * because if it stays it still reduce elixir each time a new card put!
         */
        ChangeListener skip = new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean newValue) {
                if (!newValue) {
                    deselectCard(source);
                    SelectedCardContainer.getSelectedCardContainer().selectedCardIsDroppedProperty().removeListener(put);
                }
            }
        };


        SelectedCardContainer.getSelectedCardContainer().selectedCardIsDroppedProperty().addListener(put);
        SelectedCardContainer.getSelectedCardContainer().selectedCardExistProperty().addListener(skip);

    }

    /**
     * select card
     *
     * @param source card to be selected
     */
    private void selectCard(CardView source) {
        selected = source;
        source.getCardImage().setFitHeight(88);
        source.getCardImage().setFitWidth(75);
        SelectedCardContainer.getSelectedCardContainer().put(source.getCard());

    }

    /**
     * deselect card
     *
     * @param source card to be deselected
     */
    private boolean deselectCard(CardView source) {
        selected = null;
        source.getCardImage().setFitHeight(80);
        source.getCardImage().setFitWidth(68);
        SelectedCardContainer.getSelectedCardContainer().takeOut();
        return true;
    }

    /**
     * initialize FXML objects
     */
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

    //Setters
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
                    removeEventFilter(MouseEvent.MOUSE_CLICKED, pickCard);
                    Effect lockEffect = new ColorAdjust(0, -100, 0, 0);
                    setEffect(lockEffect);
                } else {
                    addEventFilter(MouseEvent.MOUSE_CLICKED, pickCard);
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
