/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system;

/**
 *
 * @author charl
 */
public class Validate {
    private boolean status;
    private String message;
    
    public Validate(){
        this.status = false;
        this.message = "Unknown Error";
    }
    
    public Validate(boolean status, String message){
        this.status = status;
        this.message = message;
    }
    
    public void setStatus(boolean bool){
        status = bool;
    }
    
    public void setMessage(String message){
        this.message = message;
    }
    
    public boolean getStatus(){
        return this.status;
    }
    
    public String getMessage(){
        return this.message;
    }
}
