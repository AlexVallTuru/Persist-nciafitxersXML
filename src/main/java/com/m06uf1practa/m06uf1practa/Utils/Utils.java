/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.m06uf1practa.m06uf1practa.Utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Carlos
 */
public class Utils {
    
    public String  convertLocalDate(LocalDate fecha){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return fecha.format(formatter);
        
    }
    
}
