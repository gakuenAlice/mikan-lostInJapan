/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd;

import java.io.FileNotFoundException;
import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

/**
 *
 * @author charl
 */
public class FileManager {
    
    public static void openFile(String filename){
        try {
            accessFile(filename);
        } catch (FileNotFoundException ex) {
           File reader = new File(filename);
        }
    }
    
    public static void writeFile(String filename, String data){
        try {
            openFile(filename);
            FileWriter writer = new FileWriter(filename);
            writer.write(data);
            writer.close();
        } catch (IOException ex) {
            System.out.println("Unable to access file");
        }
    }
    
    public static String readFile(String filename, String data){
        String ret = "";
        
        try {
            FileReader reader = new FileReader(filename);
            Scanner scan = new Scanner(reader);
            
            scan.next();
            
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to access file");
        }
        
        
        return ret;
    }
    
    
    
    public static void accessFile(String filename) throws FileNotFoundException{
        PrintWriter output = new PrintWriter(filename);
        output.close();
    }

    
    
}
