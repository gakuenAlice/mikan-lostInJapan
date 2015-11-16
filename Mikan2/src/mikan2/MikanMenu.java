/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mikan2;

import com.almasb.fxgl.GameApplication;
import com.almasb.fxgl.event.MenuEvent;
import com.almasb.fxgl.ui.FXGLMenu;
import com.almasb.fxgl.ui.UIFactory;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;


/**
 *
 * @author charl
 */
public class MikanMenu extends FXGLMenu{

    public MikanMenu(GameApplication app) {
        super(app);
    }

    @Override
    protected MenuBox createMenuBody() {
        
        MenuItem itemResume = new MenuItem("RESUME");
        itemResume.setOnAction(e -> itemResume.fireEvent(new MenuEvent(MenuEvent.RESUME)));

        MenuItem itemSave = new MenuItem("SAVE");
        itemSave.setOnAction(e -> {
            UIFactory.getDialogBox().showInputBox("Enter save file name", name -> {
                itemSave.fireEvent(new MenuEvent(e.getSource(), e.getTarget(), MenuEvent.SAVE, name));
            });
        });

        MenuItem itemLoad = new MenuItem("LOAD");
        itemLoad.setMenuContent(createContentLoad());

        MenuItem itemOptions = new MenuItem("OPTIONS");
        itemOptions.setChild(createOptionsMenu());

        MenuItem itemExtra = new MenuItem("EXTRA");
        itemExtra.setChild(createExtraMenu());

        MenuItem itemExit = new MenuItem("MAIN MENU");
        itemExit.setOnAction(e -> {
            UIFactory.getDialogBox().showConfirmationBox("Exit to Main Menu?\nAll unsaved progress will be lost!", yes -> {
                if (yes)
                    itemExit.fireEvent(new MenuEvent(MenuEvent.EXIT));
            });
        });
        
        return new MikanBox(800, itemResume, itemSave, itemLoad, itemOptions, itemExtra, itemExit);
    }
    
    protected static class MikanBox extends MenuBox{

        public MikanBox(int width, MenuItem... items) {
            super(width, items);
            this.getChildren().removeAll();
            
            for(MenuItem item : items){
                this.getChildren().remove(item);
            }
            try {
                
                
                this.getChildren().add(FXMLLoader.load(getClass().getResource("MikanFXML.fxml")));
                
            } catch (IOException ex) {
                Logger.getLogger(MikanMenu.class.getName()).log(Level.SEVERE, null, ex);
            }

            
        }
        
       
        
        
    }
    
}
