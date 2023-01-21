/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.m06uf1practa.m06uf1practa.Datos;

import com.m06uf1practa.m06uf1practa.Modelos.Festivos;
import com.m06uf1practa.m06uf1practa.Interface.XMLDataInterface;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;

/**
 *
 * @author Carlos
 */
public class XMLData implements XMLDataInterface {
    
    private ObservableList<Festivos> festivos;
    private ObservableList<Festivos> resultados;
    @Override
    public void ImportarDocumento() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void ExportarDocumento() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

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
    
    @Override
    public File cargarFichero(Window window){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open XML File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("XML Files", "*.xml")
        );
        File selectedFile = fileChooser.showOpenDialog(window);
        System.out.println(selectedFile);
        if (selectedFile != null) {
            String fileName = selectedFile.getName();
            if (fileName.endsWith(".xml")) {
                System.out.println("El archivo es valido");
                return selectedFile;
            } else {
                System.out.println("El archivo no es valido");
            }
        } else {
            System.out.println("Se ha cancelado la carga de archivo");
        }
        return null;
    }
    
    public ObservableList<Festivos> FiltradoCompleto(String nom,String ambit,String localitat,String municipi){
        
        resultados = FXCollections.observableArrayList();
        for(Festivos valor : festivos){
            if(valor.getNombreIsla().equalsIgnoreCase(nom) || valor.getAmbito().equals(ambit) || valor.getMunicipio().equalsIgnoreCase(municipi)  ){
                resultados.add(valor);
            }
        }
        return resultados;
        
    }
    
    public ObservableList<Festivos> leerFichero(File fichero) {
        festivos = FXCollections.observableArrayList();
        
        try {
            Builder builder = new Builder();
            Document doc = builder.build(fichero);
            Element root = doc.getRootElement();
            Element row = root.getFirstChildElement("row");
            Elements rows = row.getChildElements();

            for (int i = 0; i < rows.size(); i++) {
                Element row1 = rows.get(i);
                Element illa = row1.getFirstChildElement("illa");
                Element mbit = row1.getFirstChildElement("mbit");
                Element municipi = row1.getFirstChildElement("municipi");
                Element localitat = row1.getFirstChildElement("localitat");
                Element data = row1.getFirstChildElement("data");
                Element nom_festa = row1.getFirstChildElement("nom_festa");
                String illaValue = illa.getValue();
                String mbitValue = mbit.getValue();
                String municipiValue = municipi.getValue();
                String localitatValue = localitat.getValue();
                String dataValue = data.getValue();
                String nom_festaValue = nom_festa.getValue();
                
                festivos.add(new Festivos(illaValue,mbitValue,municipiValue,localitatValue,dataValue,nom_festaValue));

            }
            return festivos;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
