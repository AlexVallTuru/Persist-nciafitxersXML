/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import Utils.Utils;
import Interface.XMLLogicInterface;
import Modelos.Festivos;
import Datos.XMLData;
import Errors.DataError;
import Errors.LogicError;
import java.io.File;
import java.time.LocalDate;
import java.util.Set;
import javafx.collections.ObservableList;
import javafx.stage.Window;

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
    public ObservableList<Festivos> cargarFichero(Window window) throws LogicError {
        try {
            file = datos.cargarFichero(window);
            if (file != null) {
                return datos.leerFichero(file);
            }

        } catch (DataError e) {
            throw new LogicError(e.getMessage());
        } catch (NullPointerException e) {
            throw new LogicError("El fichero no es valido");
        }
        return null;

    }

    public ObservableList<Festivos> cercaDades(String nom, 
            String ambit, String localitat, String municipi, String nombreFiesta, LocalDate d1, LocalDate d2) throws LogicError {

        try {
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
                lis = datos.FiltradoCompleto(nom, ambit, localitat, municipi, nombreFiesta, d1, d2);
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
        } catch (DataError e) {
            throw new LogicError(e.getMessage());
        }

    }

    public ObservableList<Festivos> checkTable(String nombre, String ambit, String municipio, String localitat, String nombreFiesta, LocalDate d1, LocalDate d2) throws LogicError {
        try {

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

        } catch (DataError e) {
            throw new LogicError(e.getMessage());
        }

    }

    public ObservableList<Festivos> neteja(String nombre, String ambit, String municipio, String localitat, String nombreFiesta, LocalDate d1, LocalDate d2) throws LogicError {
        try {
            if (nombre.isEmpty()
                    && ambit.equals("Cap")
                    && municipio.isEmpty()
                    && localitat.isEmpty()
                    && nombreFiesta.equals("Festa")
                    && d1 == null
                    && d2 == null) {
                return datos.leerFichero(file);
            }
            return null;

        } catch (DataError e) {
            throw new LogicError(e.getMessage());
        }

    }

    public Set<String> fiestas() {
        return datos.fiestas();
    }

}
