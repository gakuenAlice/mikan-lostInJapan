/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mikan2;


import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 *
 * @author charl
 */
public class MikanBox extends Pane{
    
    @FXML private ImageView ladderMenu;
    @FXML private Label label;
    
    public MikanBox(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MikanFXML.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
    
    public void hello(){
        label.setText("Hello World");
    }
}
