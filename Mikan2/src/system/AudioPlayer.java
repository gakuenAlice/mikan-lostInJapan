/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system;

import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

/**
 *
 * @author charl
 */
public class AudioPlayer {
    public static final String FilenameBGMusic = "kung.mp3";
    public static final String FilenameTextEffectsMusic = "gong.mp3";
    private static AudioPlayer instance;
    
    private MediaPlayer backgroundMusic;
    private MediaPlayer testEffectsMusic;
    
    private AudioPlayer(){
        backgroundMusic = new MediaPlayer(new Media(new File(FilenameBGMusic).toURI().toString()));   
        testEffectsMusic = new MediaPlayer(new Media(new File(FilenameTextEffectsMusic).toURI().toString())); 
        
    }    
    public static AudioPlayer getInstance(){
        if(instance == null){
            instance = new AudioPlayer();
        }
        
        return instance;
    }
    
    public void playBackgroundMusic(){
        backgroundMusic.setCycleCount(MediaPlayer.INDEFINITE);
        backgroundMusic.play();
    }
    
    public void playTestEffectsMusic(){
        testEffectsMusic.seek(Duration.ZERO);
        testEffectsMusic.setCycleCount(1);
        testEffectsMusic.play();
    }
    
    public void setVolumeBackgroundMusic(double num){
        backgroundMusic.setVolume(num/100.0);
        
    }
    
    public void setVolumeTestEffectsMusic(double num){
        testEffectsMusic.setVolume(num/100.0);
    }
}
