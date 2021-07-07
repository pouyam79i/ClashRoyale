package org.gamedevs.clashroyale.model.media;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.net.URISyntaxException;
import java.util.ArrayList;

public class MusicPlayer {

    private static MusicPlayer musicPlayerInstance = null;

    private final ArrayList<Media> menuMusics;
    private final ArrayList<Media> battleMusics;

    private MediaPlayer mediaPlayer = null;

    private MusicPlayer() throws URISyntaxException {
        menuMusics = new ArrayList<Media>();
        battleMusics = new ArrayList<Media>();
        initial();
    }

    public void playMenuMusic(){
        start(menuMusics.get(0));
        turnOnRepeat();
    }

    public void turnOnRepeat(){
        mediaPlayer.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                mediaPlayer.seek(Duration.ZERO);
            }
        });
    }

    public void start(Media media){
        stop();
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
    }

    public void stop(){
        if(mediaPlayer != null){
            mediaPlayer.stop();
            mediaPlayer = null;
        }
    }

    private void initial(){
        // Adding menu music
        menuMusics.add(new Media(getClass().getResource("./../../audio/main/CR_MENU_01.mp3").toExternalForm()));
        menuMusics.add(new Media(getClass().getResource("./../../audio/main/CR_MENU_02.mp3").toExternalForm()));
        menuMusics.add(new Media(getClass().getResource("./../../audio/main/CR_MENU_03.mp3").toExternalForm()));
        // Adding battle music
        battleMusics.add(new Media(getClass().getResource("./../../audio/battle/CR_Tutorial_battle_01.mp3").toExternalForm()));
        battleMusics.add(new Media(getClass().getResource("./../../audio/battle/CR_battle_01.mp3").toExternalForm()));
    }

    public static MusicPlayer getMusicPlayer() throws URISyntaxException {
        if(musicPlayerInstance == null)
            musicPlayerInstance = new MusicPlayer();
        return musicPlayerInstance;
    }

}
