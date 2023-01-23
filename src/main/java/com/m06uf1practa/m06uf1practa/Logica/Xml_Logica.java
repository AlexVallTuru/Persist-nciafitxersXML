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
import java.util.Set;
import javafx.collections.FXCollections;
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
    private ObservableList<Festivos> lis;
    private File file;

    @Override
    public ObservableList<Festivos> FiltrarPorNombre() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ObservableList<Festivos> FiltrarPorFechas() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ObservableList<Festivos> FiltrarPorAmbito() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ObservableList<Festivos> FiltrarPorFiesta() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void FiltradoCompleto() {

    }

    @Override
    public ObservableList<Festivos> cargarFichero(Window window) {
        try {
            file = datos.cargarFichero(window);
            if (file != null) {
                return datos.leerFichero(file);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public ObservableList<Festivos> cercaDades(String nom, String ambit, String localitat, String municipi, String nombreFiesta, LocalDate d1, LocalDate d2) {

        System.out.println(nom.isEmpty());
        System.out.println(!ambit.equals("Cap"));
        System.out.println(localitat.isEmpty());
        System.out.println(municipi.isEmpty());
        System.out.println(nombreFiesta);
        if ((!nom.isEmpty() && !ambit.equals("Cap") && !localitat.isEmpty() && !municipi.isEmpty() && !nombreFiesta.equals("Festa") && (d1.toString().isEmpty() || d1 == null) && (d2.toString().isEmpty() ||d2 == null))) {
            lis = datos.FiltradoCompleto(nom, ambit, localitat, municipi, nombreFiesta);
            return lis;

        }

        if (nom.isEmpty() && localitat.isEmpty() && !ambit.equals("Cap") && municipi.isEmpty() && nombreFiesta.equals("Festa") && (d1.toString().isEmpty() || d1 == null) && (d2.toString().isEmpty() ||d2 == null)) {

            lis = datos.FiltrarPorAmbito(ambit);
            return lis;

        }

        if (!nom.isEmpty() && localitat.isEmpty() && ambit.equals("Cap") && municipi.isEmpty() && nombreFiesta.equals("Festa") && (d1 == null) && (d2 == null)) {

            lis = datos.FiltrarPorNombre(nom);
            return lis;

        }

        if (nom.isEmpty() && localitat.isEmpty() && !ambit.equals("Cap") && municipi.isEmpty() && nombreFiesta.equals("Festa") && (d1.toString().isEmpty() || d1 == null) && (d2.toString().isEmpty() ||d2 == null)) {

            lis = datos.FiltrarPorAmbito(ambit);
            return lis;

        }

        if (nom.isEmpty() && localitat.isEmpty()
                && ambit.equals("Cap")
                && municipi.isEmpty()
                && nombreFiesta.equals("Festa")
                && (d1 != null) 
                && (d2 != null)) {

            lis = datos.FiltrarPorFechas(d1, d2);
            return lis;

        }

        if (nom.isEmpty() && localitat.isEmpty() && ambit.equals("Cap") && municipi.isEmpty() && !nombreFiesta.equalsIgnoreCase("Festa")) {

            lis = datos.FiltrarPorFiesta(nombreFiesta);
            return lis;

        }
        return null;
    }

    public ObservableList<Festivos> checkTable(String nombre, String ambit, String municipio, String localitat, String nombreFiesta, LocalDate d1, LocalDate d2) {
        if (nombre.isEmpty() 
                && ambit.equals("Cap") 
                && municipio.isEmpty() 
                && localitat.isEmpty() 
                && nombreFiesta.equals("Festa") 
                && !d1.toString().isEmpty()
                && !d2.toString().isEmpty()) {
            return datos.leerFichero(file);
        } else {
            return cercaDades(nombre, ambit, localitat, municipio, nombreFiesta, d1, d2);
        }

    }

    public ObservableList<Festivos> neteja(String nombre, String ambit, String municipio, String localitat, String nombreFiesta, LocalDate d1, LocalDate d2) {
        if (nombre.isEmpty() 
                && ambit.equals("Cap") 
                && municipio.isEmpty() 
                && localitat.isEmpty() 
                && nombreFiesta.equals("Festa") 
                && !d1.toString().isEmpty() 
                && !d2.toString().isEmpty()) {
            return datos.leerFichero(file);
        }
        return null;
    }

    public Set<String> fiestas() {
        return  datos.fiestas();
    }

}
