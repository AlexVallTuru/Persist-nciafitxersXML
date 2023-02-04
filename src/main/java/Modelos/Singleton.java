/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Alex
 */
public class Singleton {

    private static Singleton instance;
    private ObservableList<Festivos> listaFestivos;
    private static String contrasena;

    private Singleton() {
        this.listaFestivos = FXCollections.observableArrayList();
    }

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public ObservableList<Festivos> getListaFestivos() {
        return listaFestivos;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public static String getContrasena() {
        return contrasena;
    }
    
}