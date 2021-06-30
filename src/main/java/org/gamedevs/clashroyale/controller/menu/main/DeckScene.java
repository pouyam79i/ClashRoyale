package org.gamedevs.clashroyale.controller.menu.main;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.awt.*;
import java.io.File;

public class DeckScene {
//    GameManager gameManager = new GameManager();


    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    private GridPane playCardGridPane;

//    @FXML
//    private GridPane choicesCardGridPane;

    public void initialize() {
        int i = 0;
//        for (Card card : gameManager.getPlayer.getCards()) {
//            ObservableList<Node> imageViews = playCardGridPane.getChildren();
//            ((ImageView) imageViews.get(1)).setImage(new Image(new File("src/main/resources/org/gamedevs/clashroyale/view/img/cards/archers.png").toURI().toString()));
//            i++;
//        }

//        ImageView[] imageViews = new ImageView[12];
//        imageViews[0] = availableCard0ImageView;
//        imageViews[0].setImage(new Image("./archers.png"));
//        choicesCardGridPane.getChildren().add(new ImageView(new Image("./archers.png")));
//        choicesCardGridPane.add(imageViews[0],0,0);
    }

    @FXML
    void pickPlayCard(MouseEvent event) {
        Image image = ((ImageView) event.getSource()).getImage();
        Dragboard db = ((ImageView) event.getSource()).startDragAndDrop(TransferMode.COPY);
        ClipboardContent cc = new ClipboardContent();
        cc.putImage(image);
        db.setContent(cc);
//        ((ImageView)event.getSource()).setImage(null);
        event.consume();
    }

    @FXML
    void pickAvailableCard(MouseEvent event) {
        Image image = ((ImageView) event.getSource()).getImage();
        Dragboard db = ((ImageView) event.getSource()).startDragAndDrop(TransferMode.COPY);
        ClipboardContent cc = new ClipboardContent();
        cc.putImage(image);
        db.setContent(cc);
        ((ImageView) event.getSource()).setImage(null);
        event.consume();
    }

    @FXML
    void dragOver(DragEvent event) {
        event.acceptTransferModes(TransferMode.COPY);
    }

    @FXML
    void putImage(DragEvent event) {
        Image image = event.getDragboard().getImage();
        ((ImageView) event.getSource()).setImage(image);
    }
}
