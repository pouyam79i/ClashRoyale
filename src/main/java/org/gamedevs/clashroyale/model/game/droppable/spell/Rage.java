package org.gamedevs.clashroyale.model.game.droppable.spell;

import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import org.gamedevs.clashroyale.MainConfig;
import org.gamedevs.clashroyale.controller.battle.main.MainBattleField;
import org.gamedevs.clashroyale.model.cards.CardName;
import org.gamedevs.clashroyale.model.container.gamedata.MouseTilePosition;
import org.gamedevs.clashroyale.model.game.droppable.objects.GameObject;
import org.gamedevs.clashroyale.model.game.player.Side;
import org.gamedevs.clashroyale.model.utils.console.Console;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Rage extends Spell {

    private double duration;
    private Circle circle = new Circle();

    public Rage(int level, Side side) {
        super(side);
        radius = 5;
        nameOfDroppable = CardName.RAGE;
        switch (level) {
            case 1:
                duration = 6;
                break;
            case 2:
                duration = 6.5;
                break;
            case 3:
                duration = 7;
                break;
            case 4:
                duration = 7.5;
                break;
            case 5:
                duration = 8;
                break;
        }
    }

    @Override
    protected void effect() {
        drawCircle();
        poison();
        Platform.runLater(() -> {
            MainBattleField.getMainBattleField().getBattleFieldPaneUpdatable().getChildren().remove(circle);
        });
    }


    /**
     * draw circle on range of range
     */
    private void drawCircle() {
        circle.setCenterX(MouseTilePosition.TranslateTileToPixelX(headTile.getX()));
        circle.setCenterY(MouseTilePosition.TranslateTileToPixelY(headTile.getY()));
        circle.setRadius(MainConfig.STD_BATTLE_FIELD_X_TILE_RATIO * radius);
        circle.setFill(new Color(0.93, 0.51, 0.93, 0.3));
        circle.setVisible(true);
        Platform.runLater(() -> {
            MainBattleField.getMainBattleField().getBattleFieldPaneUpdatable().getChildren().add(circle);
        });
    }

    /**
     * boost targets in range for specific duration
     */
    private void poison() {
        ArrayList<GameObject> targets = findTargetsInRange();
        final boolean[] running = {true};
        Timer timer = new Timer();
        ArrayList<GameObject> finalTargets = targets;
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                running[0] = false;
                for (GameObject target : finalTargets)
                    target.unboost();
                timer.cancel();
            }
        };

        timer.schedule(task, (long) duration * 1000);
        while (running[0]) {
            targets = findTargetsInRange();
            for (GameObject target : targets)
                if (target.getTeamSide() == teamSide)
                    target.boost();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            for (GameObject target : targets)
                if (target.getTeamSide() == teamSide)
                    target.unboost();
        }
    }

}



