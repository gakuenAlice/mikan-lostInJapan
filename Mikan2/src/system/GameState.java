/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system;

import BackEnd.*;

/**
 *
 * @author charl
 */
public class GameState {
    private UserModel currentUser;
    private static GameState instance;
            
    private GameState(){
        
    }
    
    public static GameState getInstance(){
        if(instance == null){
            instance = new GameState();
        }
        
        return instance;
    }
    
    public void setCurrentUser(UserModel user){
        this.currentUser = user;
    }
    
    public UserModel getCurrentUser(){
        return this.currentUser;
    }
}
