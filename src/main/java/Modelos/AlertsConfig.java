/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

import Interface.AlertsConfigInterface;
import javafx.scene.control.Alert;

/**
 *
 * @author Carlos
 */
public class AlertsConfig implements AlertsConfigInterface{
    
    
    /**
     * Alerta per mostrar els errors
     * @param error 
     */
    @Override
    public void mostrarError(String error) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Error");
        alert.setTitle("Error");
        alert.setContentText(error);
        alert.showAndWait();
        
    }

    /**
     * Alerta per mostrar els Warnings
     * @param alerta 
     */
    @Override
    public void mostrarAlerta(String alerta) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("Alerta");
        alert.setTitle("Alert");
        alert.setContentText(alerta);
        alert.showAndWait();}

    /**
     * Alerta per mostrar les informacions
     * @param info 
     */
    @Override
    public void mostrarInfo(String info) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Info");
        alert.setTitle("Informacion");
        alert.setContentText(info);
        alert.showAndWait();}
    
    
    
}
