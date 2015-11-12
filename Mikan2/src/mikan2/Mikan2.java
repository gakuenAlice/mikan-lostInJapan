/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mikan2;

import com.almasb.fxgl.GameApplication;
import com.almasb.fxgl.settings.GameSettings;
import com.almasb.fxgl.ui.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import system.Default;





/**
 *
 * @author charl
 */
public class Mikan2 extends GameApplication{

    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    protected void initSettings(GameSettings settings) {
       settings.setHeight(Default.DISPALY_HEIGHT.getValue());
       settings.setWidth(Default.DISPLAY_WIDTH.getValue());
       settings.setIntroEnabled(true);
       settings.setTitle("Mikan: Lost in Japan");
       settings.setMenuEnabled(true);
    }

    @Override
    protected void initInput() {
        
    }

    @Override
    protected void initAssets() throws Exception {
         
    }

    @Override
    protected void initGame() {
       
    }

    @Override
    protected void initPhysics() {
        
    }

    @Override
    protected void initUI() {
      
    }

    @Override
    protected void onUpdate() {
         
    }
    
    @Override
    protected FXGLMenuFactory initMenuFactory(){
        return new FXGLMenuFactory() {

            @Override
            public FXGLMenu newMainMenu(GameApplication app) {
                return new MikanMenu(app);
            }

            @Override
            public FXGLMenu newGameMenu(GameApplication app) {
                return new MikanMenu(app);
            }
            
        };
        
    }


    

    
}
