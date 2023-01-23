/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import Utils.Utils;
import Interface.XMLLogicInterface;
import Modelos.Festivos;
import Datos.XMLData;
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
        
        /**
         * Filtrar con todos los datos
         */
        if ((!nom.isEmpty() 
                && !ambit.equals("Cap") 
                && !localitat.isEmpty() 
                && !municipi.isEmpty() 
                && !nombreFiesta.equals("Festa") 
                && (d1 != null) 
                && (d2 != null))) {
            lis = datos.FiltradoCompleto(nom, ambit, localitat, municipi, nombreFiesta,d1,d2);
            return lis;

        }
        
        /**
         * Filtrar por Ambito
         */
        if (nom.isEmpty() 
                && localitat.isEmpty() 
                && !ambit.equals("Cap") 
                && municipi.isEmpty() 
                && nombreFiesta.equals("Festa") 
                && (d1 == null) 
                && (d2 == null)) {

            lis = datos.FiltrarPorAmbito(ambit);
            return lis;

        }
        
        /**
         * Filtrar por nombre
         */
        if (!nom.isEmpty() 
                && localitat.isEmpty() 
                && ambit.equals("Cap") 
                && municipi.isEmpty() 
                && nombreFiesta.equals("Festa") 
                && (d1 == null) && (d2 == null)) {

            lis = datos.FiltrarPorNombre(nom);
            return lis;

        }
        
        /**
         * Filtrar por municipio
         */
        
        if (nom.isEmpty() 
                && localitat.isEmpty() 
                && ambit.equals("Cap") 
                && !municipi.isEmpty() 
                && nombreFiesta.equals("Festa") 
                && (d1 == null) && (d2 == null)) {

            lis = datos.FiltrarPorMunicipio(municipi);
            return lis;

        }

        /**
         * Filtrar por localidad
         */
        if (nom.isEmpty() 
                && !localitat.isEmpty()
                && ambit.equals("Cap") 
                && municipi.isEmpty() 
                && nombreFiesta.equals("Festa") 
                && (d1 == null) && (d2 == null)) {

            lis = datos.FiltrarPorLocalidad(localitat);
            return lis;

        }

        /**
         * Filtrar entre dos fechas
         */
        if (nom.isEmpty() && localitat.isEmpty()
                && ambit.equals("Cap")
                && municipi.isEmpty()
                && nombreFiesta.equals("Festa")
                && (d1 != null) 
                && (d2 != null)) {

            lis = datos.FiltrarPorFechas(d1, d2);
            return lis;

        }

        /**
         * Filtrar por Fiesta
         */
        if (nom.isEmpty() 
                && localitat.isEmpty() 
                && ambit.equals("Cap") 
                && municipi.isEmpty() 
                && !nombreFiesta.equalsIgnoreCase("Festa")
                && (d1 == null) 
                && (d2 == null)) {

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
                && d1 == null
                && d2 == null) {
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
                && d1 == null 
                && d2==null) {
            return datos.leerFichero(file);
        }
        return null;
    }

    public Set<String> fiestas() {
        return  datos.fiestas();
    }

}
