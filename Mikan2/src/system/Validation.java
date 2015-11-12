/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system;

import BackEnd.UserManager;
import BackEnd.UserModel;

/**
 *
 * @author charl
 */
public class Validation {
    
    public static Validate isNewUserNameValid(String userName){
        Validate ret = new Validate();
        
        if("".equals(userName.trim())){
            ret.setStatus(false);
            ret.setMessage("Name is required");
        }
        else if(!isAlphaNumeric(userName)){
            ret.setStatus(false);
            ret.setMessage("Should be numbers or letters");
        }
        else if(userName.length() > 8){
            ret.setStatus(false);
            ret.setMessage("Should not exceed 8 \ncharacters");
        }
        else{
            ret.setStatus(true);            
            ret.setMessage("");
        }
        
        return ret;
    }
    
    public static Validate isUserNameValid(String userName){
        Validate ret = new Validate();
        
        if("".equals(userName.trim())){
            ret.setStatus(false);
            ret.setMessage("Name is required");
        }
        else if(!isAlphaNumeric(userName)){
            ret.setStatus(false);
            ret.setMessage("Should be numbers or letters");
        }
        else if(userName.length() > 8){
            ret.setStatus(false);
            ret.setMessage("Should not exceed 8 \ncharacters");
        }
        else if(!isUserNameUnique(userName).getStatus()){
            ret.setStatus(false);
            ret.setMessage("Username already exists");
        }
        else{
            ret.setStatus(true);            
            ret.setMessage("");
        }
        
        return ret;
     
    }
    
    
    private static boolean isAlphaNumeric(String s){
        boolean bool = true;
        char[] charArray = s.toCharArray();
        
        for(char x: charArray){
            if(!Character.isLetterOrDigit(x)){
                bool = false;
                break;
            }
        }

        return bool;
    }
    
    private static Validate isUserNameUnique(String username){
        Validate valid = new Validate(true, "");
        UserModel[] users = UserManager.getInstance().retrieveUsers();
        
       
        for(UserModel user: users){
            if(user.getName().toLowerCase() == null ? username.toLowerCase() == null : user.getName().toLowerCase().equals(username.toLowerCase())){
                valid.setStatus(false);
                valid.setMessage("Username already exists");
                break;
            }
        }
        
        return valid;
    }
}
