/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mikan2;

import BackEnd.UserManager;
import BackEnd.UserModel;
import com.sun.prism.paint.Paint;
import java.awt.Color;
import java.net.URL;
import java.util.*;
import javafx.animation.Interpolator;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.util.Duration;
import system.*;

/**
 * FXML Controller class
 *
 * @author charl
 */
public class MikanFXMLController implements Initializable {
    @FXML
    private ImageView ladderMenu;
    @FXML
    private ImageView imgLogo;
    @FXML
    private Label labelName;
    
    
    private boolean nameMouseEntered;
    @FXML
    private ListView<String> listName;
    @FXML
    private Pane paneName;
    @FXML
    private Label labelOptions;
    @FXML
    private Label labelExit;
    @FXML
    private Pane paneSettings;
    
    @FXML
    private Pane paneNewName;
    @FXML
    private TextField fieldNewName;
    @FXML
    private Label lableError;
    @FXML
    private TextField fieldAddUser;
    @FXML
    private ImageView btnOKNewName;
    @FXML
    private Pane paneError;
    @FXML
    private Label labelError;
    @FXML
    private Label labelNewError;
    @FXML
    private Slider sliderEffects;
    @FXML
    private Slider sliderMusic;
    @FXML
    private ImageView keysASDW;
    @FXML
    private ImageView keysArrow;
    @FXML
    private ChoiceBox<String> choiceKeys;


    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        nameMouseEntered = false;
        
        paneNewName.setVisible(false);
        paneError.setVisible(false);
        //ObservableList<String> items = FXCollections.observableArrayList ("Charles", "Anter", "Dahlia", "Jewel");
        
        //listName.setItems(items);
        //listName.getSelectionModel().select(0);
        
        
        ObservableList<String> items = FXCollections.observableArrayList ("ASDW keys", "ARROW keys");
        choiceKeys.setItems(items);
        choiceKeys.getSelectionModel().select(0);
       
        choiceKeys.getSelectionModel().selectedIndexProperty().addListener(
             new ChangeListener<Number>(){

                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    setKeys(newValue.intValue());
                    GameState.getInstance().getCurrentUser().setKeyControlType(newValue.intValue());
                    UserManager.getInstance().getUser(GameState.getInstance().getCurrentUser().getName()).setKeyControlType(newValue.intValue());
                    UserManager.getInstance().updateFile();
                }
                 
             }
        
        );
        
        sliderMusic.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {

                    AudioPlayer.getInstance().setVolumeBackgroundMusic(newValue.doubleValue());
                    GameState.getInstance().getCurrentUser().setSoundMusic(newValue.intValue());
                    UserManager.getInstance().getUser(GameState.getInstance().getCurrentUser().getName()).setSoundMusic(newValue.intValue());
                    UserManager.getInstance().updateFile();
            }
        });
        
        
        sliderEffects.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {

                    AudioPlayer.getInstance().setVolumeTestEffectsMusic(newValue.doubleValue());
                    AudioPlayer.getInstance().playTestEffectsMusic();
                    GameState.getInstance().getCurrentUser().setSoundEffects(newValue.intValue());
                    UserManager.getInstance().getUser(GameState.getInstance().getCurrentUser().getName()).setSoundEffects(newValue.intValue());
                    UserManager.getInstance().updateFile();
            }
        });

       
        
        
        
        labelName.setVisible(false);
        labelOptions.setVisible(false);
        labelExit.setVisible(false);
        
        List<UserModel> users = UserManager.getInstance().getUserList();
        
        if(users.size() <= 0){
            paneNewName.setVisible(true);
            labelNewError.setVisible(false);
            btnOKNewName.setVisible(false);
        }
        else{
            GameState.getInstance().setCurrentUser(users.get(0));
            setUser(users.get(0));
            initHomeScreen();
        }
        
        AudioPlayer.getInstance().playBackgroundMusic();
        
        
      
    }
    
    public void setKeys(int code){
        code = code%2;
        choiceKeys.getSelectionModel().select(code);
        
        if(code == 0){
            keysASDW.setVisible(true);
            keysArrow.setVisible(false);
        }
        else{
            keysASDW.setVisible(false);
            keysArrow.setVisible(true);
        }
    }
    
    public void changeBackgroundMusicVolume(){

    }
    
    public void initHomeScreen(){
       
        paneNewName.setVisible(false);
        labelName.setText(GameState.getInstance().getCurrentUser().getName());
        
        List<UserModel> users = UserManager.getInstance().getUserList();
        ObservableList<String> items = FXCollections.observableArrayList();
            
        Iterator<UserModel> iterator = users.listIterator();
            
        while(iterator.hasNext()){
      
            
            items.add(iterator.next().getName());
        }
        
        paneError.setVisible(false);
        
        listName.setItems(items);
        listName.getSelectionModel().select(0);
        
        startAnimation();
    }
    
    
    public void startAnimation(){
        TranslateTransition tt = new TranslateTransition(Duration.seconds(1.0), ladderMenu);
     
        tt.setFromY(ladderMenu.getFitHeight());
        tt.setToY(0);
        tt.setCycleCount(1);
        tt.setInterpolator(Interpolator.EASE_BOTH);
        tt.setOnFinished(null);
        
        
        TranslateTransition tt2 = new TranslateTransition(Duration.seconds(1.0), imgLogo);
        
        tt2.setFromY(-(imgLogo.getFitHeight()));
        tt2.setToY(0);
        tt2.setCycleCount(1);
        tt2.setInterpolator(Interpolator.EASE_BOTH);
        tt2.setOnFinished(new Bounce());
       
        
        tt.play();
        tt2.play();
        
    }
    
    public void selectUser(){
       ObservableList<String> list =  listName.getSelectionModel().getSelectedItems();
       String currentUser = list.get(0);
       UserModel user = UserManager.getInstance().getUser(currentUser);
       
       if(user != null){
           setUser(user);
       }
       
       
    }
    
    public void setUser(UserModel user){
        labelName.setText(user.getName());
        GameState.getInstance().setCurrentUser(user);
        sliderMusic.setValue(user.getSoundMusic());
        AudioPlayer.getInstance().setVolumeBackgroundMusic(user.getSoundMusic());
        sliderEffects.setValue(user.getSoundEffects());
        AudioPlayer.getInstance().setVolumeTestEffectsMusic(user.getSoundEffects());
        setKeys(user.getKeyControlTypeAsInt());
        
    }
    
    @FXML
    public void hideError(){
        paneError.setVisible(false);
    }
    
    @FXML
    public void validateNewUser(){
        Validate valid = Validation.isNewUserNameValid(fieldNewName.getText().trim());
        
        if(valid.getStatus()){
            labelNewError.setVisible(false);
            btnOKNewName.setVisible(true);
        }
        else{
            labelNewError.setVisible(true);
            btnOKNewName.setVisible(false);
            labelNewError.setText(valid.getMessage());
        }
    }
    
    @FXML
    public void createUser(){
       
        Validate valid = Validation.isUserNameValid(fieldAddUser.getText().trim());
        
        if(valid.getStatus() == true){
            UserModel newUser = new UserModel(fieldAddUser.getText().trim());
            UserManager.getInstance().AddUser(newUser);
            UserManager.getInstance().updateFile();
            
            updateUserListView();
            fieldAddUser.setText("");
        }
        else{
            paneError.setVisible(true);
            labelError.setText(valid.getMessage());
        }
        
        
    }
    
    public void updateUserListView(){
        List<UserModel> users = UserManager.getInstance().getUserList();
        ObservableList<String> items = FXCollections.observableArrayList();
            
        Iterator<UserModel> iterator = users.listIterator();
            
        while(iterator.hasNext()){
            items.add(iterator.next().getName());
        }
        
 
        listName.setItems(items);
        listName.getSelectionModel().select(0);
    }
    
    @FXML
    public void createNewUser(){
        if(fieldNewName.getText().trim() != ""){
            UserModel newUser = new UserModel(fieldNewName.getText().trim());
            UserManager.getInstance().AddUser(newUser);
            UserManager.getInstance().updateFile();
            GameState.getInstance().setCurrentUser(newUser);
            setUser(newUser);
            
            
            initHomeScreen();
        }
        
    }
    
    @FXML
    public void showSettings(){
        TranslateTransition tt = new TranslateTransition(Duration.seconds(0.5), ladderMenu);

        tt.setFromY(0);
        tt.setToY(ladderMenu.getFitHeight());
        tt.setCycleCount(1);
        tt.setInterpolator(Interpolator.EASE_BOTH);
        
        tt.setOnFinished(new ShowSettingsMenu());
        
        labelName.setVisible(false);
        labelOptions.setVisible(false);
        labelExit.setVisible(false);
        
        tt.play();
    }
    
    @FXML
    public void hideSettings(){
        TranslateTransition tt = new TranslateTransition(Duration.seconds(0.5), paneSettings);
        tt.setFromY(paneSettings.getTranslateY());
        tt.setToY(paneSettings.getTranslateY() + 500);
        tt.setCycleCount(1);
        tt.setInterpolator(Interpolator.EASE_BOTH);
        tt.setOnFinished(new HideNameMenu());
        tt.play();
    }
    
    @FXML
    public void nameMouseEntered(){
        if(this.nameMouseEntered == false){
            this.nameMouseEntered = true;
            Font currentFont = labelName.getFont();
            labelName.setFont(Font.font(currentFont.getFamily(), 55));
        }
    }
    
    @FXML
    public void nameMouseExited(){
        if(this.nameMouseEntered == true){
            this.nameMouseEntered = false;
            Font currentFont = labelName.getFont();
            labelName.setFont(Font.font(currentFont.getFamily(), 50));
        }
    }
    
    @FXML
    public void nameMouseClicked(){
        
        TranslateTransition tt = new TranslateTransition(Duration.seconds(0.5), ladderMenu);

        tt.setFromY(0);
        tt.setToY(ladderMenu.getFitHeight());
        tt.setCycleCount(1);
        tt.setInterpolator(Interpolator.EASE_BOTH);
        
        tt.setOnFinished(new ShowNameMenu());
        
        labelName.setVisible(false);
        labelOptions.setVisible(false);
        labelExit.setVisible(false);
        
        tt.play();
    }
    
    @FXML
    public void okMouseClicked(){
        TranslateTransition tt = new TranslateTransition(Duration.seconds(0.5), paneName);
        tt.setFromY(paneName.getTranslateY());
        tt.setToY(paneName.getTranslateY() + 500);
        tt.setCycleCount(1);
        tt.setInterpolator(Interpolator.EASE_BOTH);
        tt.setOnFinished(new HideNameMenu());
        tt.play();
    }
    
    private class KeyPickerHandler implements EventHandler<KeyEvent>{
        private TextField label;
        
        public KeyPickerHandler(TextField label){
            this.label = label;
        }

        @Override
        public void handle(KeyEvent event) {
            label.setText("");
            if(Character.isLetterOrDigit(event.getCharacter().charAt(0))){
                label.setText(event.getCharacter().substring(1, 1).toUpperCase());
            }
            else if(event.getCode() == KeyCode.UP){
                label.setText("UP");
            }
            else if(event.getCode() == KeyCode.DOWN){
                label.setText("DOWN");
            }
            else if(event.getCode() == KeyCode.LEFT){
                label.setText("LEFT");
            }
            else if(event.getCode() == KeyCode.RIGHT){
                label.setText("RIGHT");
            }
            else if(event.getCode() == KeyCode.SPACE){
                label.setText("SPACE");
            }
            
        }
        
    }
    
    private class ShowSettingsMenu implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.5), paneSettings);
            tt.setFromY(paneSettings.getTranslateY());
            tt.setToY(paneSettings.getTranslateY() - 500);
            tt.setCycleCount(1);
            tt.setInterpolator(Interpolator.EASE_BOTH);
            tt.play();
            
        }
        
    }
    
    private class ShowNameMenu implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.5), paneName);
            tt.setFromY(paneName.getTranslateY());
            tt.setToY(paneName.getTranslateY() - 500);
            tt.setCycleCount(1);
            tt.setInterpolator(Interpolator.EASE_BOTH);
            tt.play();
        }
        
    }
    
    private class HideNameMenu implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.5), ladderMenu);

            tt.setFromY(ladderMenu.getFitHeight());
            tt.setToY(0);
            tt.setCycleCount(1);
            tt.setInterpolator(Interpolator.EASE_BOTH);
            tt.setOnFinished(new ShowMenuItems());
            tt.play();
            
            selectUser();
            
        }
        
    }
    
    private class ShowMenuItems implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
            labelName.setVisible(true);
            labelOptions.setVisible(true);
            labelExit.setVisible(true);
            paneError.setVisible(false);
            
            
        }
        
    }
    
    private class Bounce implements EventHandler<ActionEvent>{
     

        
        @Override
        public void handle(ActionEvent event) {
           TranslateTransition tt = new TranslateTransition(Duration.seconds(0.5), imgLogo);
           
           tt.setFromY(0);
           tt.setToY(-30);
           
           tt.play();
           
           labelName.setVisible(true);
           labelOptions.setVisible(true);
           labelExit.setVisible(true);
        }
        
    }
    
}
