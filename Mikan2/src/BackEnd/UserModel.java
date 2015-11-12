/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd;

import Misc.*;

/**
 *
 * @author charl
 */
public class UserModel {
    private String name;
    private int soundEffects;
    private int soundMusic;
    private KeyControlType keyControlType;  
    
    public UserModel(){
        this.name = "";
        this.soundEffects = 100;
        this.soundMusic = 50;
        this.keyControlType = KeyControlType.ARROW;
    }
    
    public UserModel(String name){
        this.name = name;
        this.soundEffects = 100;
        this.soundMusic = 50;
        this.keyControlType = KeyControlType.ARROW;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public void setSoundEffects(int soundEffects){
        this.soundEffects = soundEffects;
    }
    
    public void setSoundMusic(int soundMusic){
        this.soundMusic = soundMusic;
    }
    
    public void setKeyControlType(int num){
        this.keyControlType = (num%2 == 0)?KeyControlType.ARROW : KeyControlType.ASDW;
    }
    
    public String getName(){
        return this.name;
    }
    
    public int getSoundEffects(){
        return this.soundEffects;
    }
    
    public int getSoundMusic(){
        return this.soundMusic;
    }
    
    public KeyControlType getKeyControlType(){
        return this.keyControlType;
    }
    
    public int getKeyControlTypeAsInt(){
        return (this.keyControlType == KeyControlType.ARROW)?0:1;
    }
}
