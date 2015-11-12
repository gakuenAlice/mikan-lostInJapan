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
public enum Default {
    DISPALY_HEIGHT(600),
    DISPLAY_WIDTH(800);
    
    int num;
    
    private Default(int num){
        this.num = num;
    }
    
    public int getValue(){
        return this.num;
    }
}
