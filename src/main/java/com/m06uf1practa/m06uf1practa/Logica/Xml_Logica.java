/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.m06uf1practa.m06uf1practa.Logica;

import com.m06uf1practa.m06uf1practa.Utils.Utils;
import com.m06uf1practa.m06uf1practa.Interface.XMLLogicInterface;
import com.m06uf1practa.m06uf1practa.Modelos.Festivos;
import com.m06uf1practa.m06uf1practa.Datos.XMLData;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import javafx.stage.Window;
/*import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;*/

/**
 *
 * @author Carlos
 */
public class Xml_Logica implements XMLLogicInterface {

    XMLData datos = new XMLData();
    Utils utils = new Utils();
    private File file;
    @Override
    public void FiltrarPorNombre() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void FiltrarPorFechas() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void FiltrarPorAmbito() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void FiltrarPorFiesta() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public void FiltradoCompleto(){
        
    }

    @Override
    public ObservableList<Festivos> cargarFichero(Window window) {
        try {
            file = datos.cargarFichero(window);
            if(file!=null){
                return datos.leerFichero(file);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
    
    public ObservableList<Festivos> cercaDades(String nom,String ambit,String localitat,String municipi){
 
        if(!nom.isEmpty() || !ambit.isEmpty() || !localitat.isEmpty() || !municipi.isEmpty()){
            return datos.FiltradoCompleto(nom,ambit,localitat,municipi);
            
        }
        return null;
    }
    
    public void clearTable(TableView table){
        for (int i = 0; i <table.getItems().size(); i++) {
            table.getItems().clear();
        }
    }
    
    public ObservableList<Festivos> checkTable(String nombre,String ambit,String municipio){
        if(nombre.isEmpty() && municipio.isEmpty()){
            return datos.leerFichero(file);
        }
        return null;
    }

    

}
