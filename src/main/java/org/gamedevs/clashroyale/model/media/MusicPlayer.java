package org.gamedevs.clashroyale.model.media;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import org.gamedevs.clashroyale.model.utils.console.Console;

import java.util.ArrayList;

/**
 * This class plays main music of application!
 * @author Pouya Mohammadi - CE@AUT - Uni ID: 9829039
 * @version 1.0
 */
public class MusicPlayer {

    /**
     * Only instance of music player
     */
    private static MusicPlayer musicPlayerInstance = null;

    /**
     * Music container for main menu
     */
    private final ArrayList<Media> musics;

    /**
     * Media player -> used to main app music!
     */
    private MediaPlayer mediaPlayer = null;

    /**
     * Constructor of MusicPlayer
     * Builds requirements!
     */
    private MusicPlayer(){
        musics = new ArrayList<Media>();
    }

    /**
     * players the music
     * @param musicName name of music
     */
    public void play(Musics musicName){
        if(musicName != null){
            if(musics.size() > musicName.getIndex() && musicName.getIndex() >= 0){
                startMediaPlayer(musics.get(musicName.getIndex()));
                return;
            }
        }
        Console.getConsole().printTracingMessage("Failed to play music");
    }

    /**
     * stops the music
     */
    public void stop(){
        if(mediaPlayer != null){
            mediaPlayer.stop();
            mediaPlayer = null;
        }
    }

    /**
     * Loads music in used in main media player
     */
    public void load(){
        Console.getConsole().printTracingMessage("Loading main musics...");
        // Adding menu music
        musics.add(new Media(getClass().getResource("./../../audio/main/CR_signup.mp3").toExternalForm()));    // Number 0
        musics.add(new Media(getClass().getResource("./../../audio/main/CR_main_menu.mp3").toExternalForm()));    // Number 1
        // Adding battle music
        musics.add(new Media(getClass().getResource("./../../audio/battle/CR_battle_p1.mp3").toExternalForm()));   // Number 2
        musics.add(new Media(getClass().getResource("./../../audio/battle/CR_battle_p2.mp3").toExternalForm()));    // NUmber 3
        musics.add(null); // Number 4
        Console.getConsole().printTracingMessage("Loading main musics finished!");
    }

    /**
     * Starts the music
     * @param media is file of loaded music
     */
    private void startMediaPlayer(Media media){
        if(media == null)
            return;
        stop();
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                mediaPlayer.seek(Duration.ZERO);
            }
        });
        mediaPlayer.setAutoPlay(true);
    }

    /**
     * @return only instance of this class
     */
    public static MusicPlayer getMusicPlayer() {
        if(musicPlayerInstance == null)
            musicPlayerInstance = new MusicPlayer();
        return musicPlayerInstance;
    }

}
