package org.gamedevs.clashroyale.controller.battle.main;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import org.gamedevs.clashroyale.controller.menu.main.DeckScene;
import org.gamedevs.clashroyale.model.cards.Card;
import org.gamedevs.clashroyale.model.container.gamedata.CardImageContainer;
import org.gamedevs.clashroyale.model.container.gamedata.UserAccountContainer;
import org.gamedevs.clashroyale.model.game.battle.tools.CardGenerator;
import org.gamedevs.clashroyale.model.game.battle.tools.Clock;
import org.gamedevs.clashroyale.model.game.battle.tools.Elixir;
import org.gamedevs.clashroyale.model.utils.console.Console;

public class CardDeckGame {

    @FXML
    private GridPane cardGridPane;

    @FXML
    private Label elixirLabel;

    @FXML
    private ImageView nextCardImageView;

    @FXML
    private ProgressBar elixirProgressBar;

    @FXML
    private Label nextLabel;
    private Elixir elixir;
    protected DoubleProperty value = new SimpleDoubleProperty();

    private CardGenerator cardGenerator ;

    /**
     * an event handler which called to pick a card by dragging it
     */
    private EventHandler<MouseEvent> pickCard = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            CardView source = (CardView) event.getSource();
            Image image = source.getCardImage().getImage();
            Dragboard db = (source.getCardImage()).startDragAndDrop(TransferMode.MOVE);
            Console.getConsole().printTracingMessage(source.getCard().getCardName().toString());
            ClipboardContent cc = new ClipboardContent();
            cc.putImage(image);
            db.setContent(cc);
            event.consume();

            elixir.reduceElixir(source.getCard().getCost());
        }
    };

    public void initnext(){
        cardGenerator = new CardGenerator(UserAccountContainer.getUserAccountContainer().getAccount().getDeckContainer(), Elixir.getPlayer1Elixir());
        Clock.getClock().startClock();
        elixir = Elixir.getPlayer1Elixir();
        elixir.startElixir();
        value.bind(elixir.elixirValueProperty());
        initPlayCards();
        elixirProgressBar.progressProperty().bind(value.divide(10));
        elixirLabel.textProperty().bind(value.asString("%.0f"));
    }


    @FXML
    void init(MouseEvent event) {
        initnext();
    }

    /**
     * load and put play card pic into top grid
     */
    private void initPlayCards() {

        int i = 0;
        for (Card card : cardGenerator.getInitCards()) {
            CardView cardImageView = new CardView(card);
            cardGridPane.add(cardImageView, i % 4, i / 4);
            i++;
        }

        nextCardImageView.setImage(CardImageContainer.getCardImageContainer().getCardImage(cardGenerator.getNextCard().getCardName()));

    }

    /**
     * A GridPane to show card and progress bar in grid cell
     *
     * @author Hosna Hoseini
     * 9823010 -CE@AUT
     * @version 1.0
     */
    class CardView extends GridPane {
        private Card card;
        private ImageView cardImage = new ImageView();
        private ImageView ElixirImage = new ImageView();
        private Label cost = new Label();


        /**
         * constructor to make a new VBox
         *
         * @param card the card which we want to show
         */
        public CardView(Card card) {
            //init info of fields
            this.card = card;
            cardImage.setImage(CardImageContainer.getCardImageContainer().getCardImage(card.getCardName()));
//            ElixirImage.setImage(new Image(DeckScene.class.getResourceAsStream("org/gamedevs/clashroyale/view/img/ui/icon/cost.png")));
            this.cost.setText(String.valueOf(card.getCost()));

            //set size
            cardImage.setFitHeight(80);
            cardImage.setFitWidth(68);
            cost.setLayoutX(42.5);
            ElixirImage.setLayoutX(42.5);


            //add to vbox
            getChildren().add(cardImage);
            getChildren().add(cost);
            getChildren().add(ElixirImage);

            addEventFilter(MouseEvent.DRAG_DETECTED, pickCard);


            value.addListener((observableValue, oldValue, newValue) -> {
                if(newValue.doubleValue() < card.getCost()){
                    removeEventFilter(MouseEvent.DRAG_DETECTED, pickCard);
                    Effect lockEffect = new ColorAdjust(0,-100,0,0);
                    setEffect(lockEffect);
                }else {
                    addEventFilter(MouseEvent.DRAG_DETECTED, pickCard);
                    Effect unlockEffect = new ColorAdjust(0,0,0,0);
                    setEffect(unlockEffect);
                }
            });
        }

        private void lockCard(){
            removeEventFilter(MouseEvent.DRAG_DETECTED, pickCard);
        }
        //Getter
        public ImageView getCardImage() {
            return cardImage;
        }

        //Getter
        public Card getCard() {
            return card;
        }

        //Setter
        public void setCard(Card card) {
            this.card = card;
        }

        public Label getCost() {
            return cost;
        }

        public void setCost(Label cost) {
            this.cost = cost;
        }
    }
}
