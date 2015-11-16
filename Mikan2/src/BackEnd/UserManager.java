/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd;


import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.*;
import javax.json.stream.JsonParsingException;

/**
 *
 * @author charl
 */
public class UserManager {
    private LinkedList<UserModel> users;
    private static final String filename = "mikanUsers.txt";
    private static UserManager instance;
    
    private UserManager(){
        this.users = new LinkedList<UserModel>();
        UserModel[] userModels = retrieveUsers();
        
        try{
            for(UserModel userModel: userModels){
                this.users.add(userModel);
            }
        }catch(NullPointerException ex){
           
        }catch(Exception ex){
            
        }
        
    }
    
    
    public static UserManager getInstance(){
        if(instance == null){
            instance = new UserManager();
        }
        
        return instance;
    }
    
    public List<UserModel> getUserList(){
        return this.users;
    }
    
    public UserModel getUser(String s){
        UserModel ret = null;
        
        Iterator<UserModel> iterator = users.iterator();
        
        while(iterator.hasNext()){
            UserModel x = iterator.next();
            
            if(x.getName().toLowerCase().trim() == null ? s.toLowerCase().trim() == null : x.getName().toLowerCase().trim().equals(s.toLowerCase().trim())){
                ret = x;
                break;
            }
        }
        
        return ret;
    }
    
    public boolean AddUser(UserModel user){
        boolean ret = true;
        
        if(isUserExist(user) != true){
            users.push(user);
            
        }
        else{
            ret = false;
        }
        
        return ret;
    }
    
    private boolean isUserExist(UserModel user){
        boolean ret = false;
     
        
        ListIterator<UserModel> iterator = users.listIterator();
        
        while(iterator.hasNext() == true && ret == false){
            UserModel x = iterator.next();
            
            if(x.getName() == user.getName()){
                ret = true;
            }
        }
       
                
        return ret;
    }
    
    
    private UserModel[] getUsersAsArray(){
       
        int size = users.size();
        UserModel[] arr = new UserModel[size];
        
        ListIterator<UserModel> iterator = users.listIterator();
        
        for(int i= 0; i < size && iterator.hasNext(); i++){
            arr[i] = iterator.next();
        }
         
        return arr;
    }
    
    public void printUsers(){
        ListIterator<UserModel> iterator = users.listIterator();
        
        while(iterator.hasNext()){
            UserModel user = iterator.next();
            System.out.println(user.getName() + " -- " + user.getSoundEffects() + " -- " + user.getSoundMusic());
        }
    }
    
    public UserModel[] retrieveUsers(){
        UserModel[] ret = null;
        
        try {
            InputStream fis = new FileInputStream(filename);
            JsonReader jsonReader = Json.createReader(fis);
            JsonObject jsonObject = jsonReader.readObject();
            jsonReader.close();
            fis.close();
            
            JsonArray jsonArray = jsonObject.getJsonArray("users");
        
            
            
            ret = new UserModel[jsonArray.size()];
           
                for(int i = 0; i < jsonArray.size(); i++){
                    JsonObject jObject = jsonArray.getJsonObject(i);
                    UserModel user = new UserModel();
                    user.setName(jObject.getString("name"));
                    user.setSoundEffects(jObject.getInt("soundEffects"));
                    user.setSoundMusic(jObject.getInt("soundMusic"));
                    user.setKeyControlType(jObject.getInt("keyControlType"));

                    ret[i] = user;
                }
          
          
        } catch (JsonParsingException ex){
            System.out.println("File has been corrupted");
            ret = null;
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to access file");
            ret = null;
        } catch (IOException ex) {
            System.out.println("Unable to close file");
            ret = null;
        } catch (NullPointerException ex){
            System.out.println("Data has been corrupt");
            ret = null;
        }catch (Exception ex){
            System.out.println("Data has been corrupt");
            ret = null;
        }
        
        
        return ret;
    }
    
    public void updateFile(){
        JsonArrayBuilder usersBuilder = Json.createArrayBuilder();
        JsonObjectBuilder mainBuilder = Json.createObjectBuilder();
        
        
        ListIterator<UserModel> iterator = users.listIterator();
        
        while(iterator.hasNext()){
            UserModel user = iterator.next();
            JsonObjectBuilder userBuilder = Json.createObjectBuilder();
            
            userBuilder.add("name", user.getName())
                       .add("soundEffects", user.getSoundEffects())
                       .add("soundMusic", user.getSoundMusic())
                       .add("keyControlType",user.getKeyControlTypeAsInt());
            
            usersBuilder.add(userBuilder);
        }
        
        mainBuilder.add("users", usersBuilder);
        JsonObject empJsonObject = mainBuilder.build();
        FileManager.openFile(filename);
        
        try {
            OutputStream os = new FileOutputStream(filename);
            JsonWriter jsonWriter = Json.createWriter(os);
            jsonWriter.writeObject(empJsonObject);
            jsonWriter.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Cannot Access File");
        }
        
    }
    
    
    
    
    
}
