package org.gamedevs.clashroyale.controller.loaders;

import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class PreloaderScreen extends Preloader {

    private Stage preloaderStage;
    private Scene scene;

    public PreloaderScreen(){

    }

    @Override
    public void init() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(
                "./../../../../../../resources/view/fxml/loading.fxml"
        ));
        scene = new Scene(root);
    }

    @Override
    public void start(Stage stage) throws Exception {

        this.preloaderStage = stage;

        preloaderStage.setScene(scene);
        preloaderStage.initStyle(StageStyle.UNDECORATED);
        preloaderStage.show();

    }

    @Override
    public void handleProgressNotification(ProgressNotification pn){

    }

    @Override
    public void handleStateChangeNotification(StateChangeNotification evt){

    }

}
