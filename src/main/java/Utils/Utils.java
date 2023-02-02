/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 *
 * @author Carlos
 */
public class Utils {
    public enum OS{
        WINDOWS,LINUX,MAC
    }
    public LocalDate convertLocalDate(String fecha) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(fecha, formatter);

    }

    public String capitalizeName(String name) {
        String nueva_cadena;
        char primeraLetra = name.charAt(0);
        if (Character.isAlphabetic(primeraLetra)) {
            nueva_cadena = name.substring(0, 1).toUpperCase() + name.substring(1, name.length());
            return nueva_cadena;
        }
        return name;
        

    }
    
    public String convertPath(String path){
        String os = System.getProperty("os.name").toLowerCase();
        
        if(os.contains("win")){
            return path.replace("/","\\" );
        }
        
        if(os.contains("nix") || os.contains("nux") || os.contains("aix")){
            return path.replace("\\","/" );
        }
        
        return path;
        
    }
        
    

}
