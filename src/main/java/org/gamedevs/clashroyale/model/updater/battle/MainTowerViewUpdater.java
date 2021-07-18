package org.gamedevs.clashroyale.model.updater.battle;

import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import org.gamedevs.clashroyale.model.game.droppable.objects.buildings.MainTowers;
import org.gamedevs.clashroyale.model.utils.multithreading.Runnable;

public class MainTowerViewUpdater extends Runnable {

    private final ImageView towerImage;
    private final ProgressBar progressBar;
    private final MainTowers mainTower;

    public MainTowerViewUpdater(ImageView towerImage, ProgressBar progressBar, MainTowers mainTower){
        this.towerImage = towerImage;
        this.progressBar = progressBar;
        this.mainTower = mainTower;
    }

    @Override
    public void run() {

    }

}
