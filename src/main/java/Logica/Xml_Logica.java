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
import Modelos.InformeFiestas;
import Modelos.Singleton;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Set;
import javafx.collections.ObservableList;
import javafx.stage.Window;
import nu.xom.Document;
import nu.xom.Element;

/**
 *
 * @author Carlos
 */
public class Xml_Logica implements XMLLogicInterface {

    Singleton singleton = Singleton.getInstance();
    XMLData datos = new XMLData();
    Utils utils = new Utils();
    private ObservableList<Festivos> lis;
    private File file;

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
            throw new LogicError(e.getMessage());
        }
        return null;

    }

    /**
     * Converteix les dades del ObservableList a un document preparat per
     * exportar a un fitxer XML. També agafa els parametres USERHOME i filename
     * per enviar-los a la creació del fitxer.
     *
     * @param export
     * @param USERHOME
     * @param filename
     * @param window
     * @throws Errors.DataError
     * @author Aitor
     */
    public void exportaDades(ObservableList<Festivos> export, String USERHOME, String filename, Window window) throws DataError {
        Element root = new Element("row"); //Arrel
        Element row = new Element("row"); //Sub-Arrel
        export.stream().map(list -> {
            //Element pare
            Element element = new Element("row");

            //Elements fills
            Element illa = new Element("illa");
            Element ambit = new Element("mbit");
            Element municipi = new Element("municipi");
            Element localitat = new Element("localitat");
            Element data = new Element("data");
            Element nomFesta = new Element("nom_festa");

            //Afegir valors als elements
            illa.appendChild(list.getNombreIsla());
            ambit.appendChild(list.getAmbito());
            municipi.appendChild(list.getMunicipio());
            localitat.appendChild(list.getLocalidad());
            data.appendChild(list.getFecha().toString());
            nomFesta.appendChild(list.getNombreFiesta());

            //Posar tots els elements fills sota l'element pare
            element.appendChild(illa);
            element.appendChild(ambit);
            element.appendChild(municipi);
            element.appendChild(localitat);
            element.appendChild(data);
            element.appendChild(nomFesta);
            return element;

            //Assignar l'element pare a l'element arrel
        }).forEachOrdered(element -> {
            row.appendChild(element);
        });
        root.appendChild(row);

        //Creacio del document
        Document doc = new Document(root);
        String contenidoXml = doc.toXML();
        String key = singleton.getContrasena();
        contenidoXml = XMLData.Encriptacion(contenidoXml, key);
        datos.ExportarDocumento(contenidoXml, USERHOME, filename, window);
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
    
    /**
     * Verifica quins camps contenen dades
     * @param nombre
     * @param ambit
     * @param municipio
     * @param localitat
     * @param nombreFiesta
     * @param d1
     * @param d2
     * @return
     * @throws LogicError 
     */

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
    
    /**
     * Neteja tots els filtres de la taula
     * 
     * @param nombre
     * @param ambit
     * @param municipio
     * @param localitat
     * @param nombreFiesta
     * @param d1
     * @param d2
     * @return
     * @throws LogicError 
     */

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
    
    /**
     * Genera informe general
     * @return 
     */

    public ObservableList<InformeFiestas> generarInforme() {

        try {
            return datos.generaInforme();
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }
    
    /**
     * Genera informe de localitats
     * @return 
     */
    public ObservableList informeLocalitats() {
        try {
            return datos.generaInformeLocalidades();
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;

    }

    /**
     * Cargar fichero encriptado
     * @param window
     * @return
     * @throws LogicError
     * @throws IOException 
     */
    public ObservableList<Festivos> cargarFicheroEncriptado(Window window) throws LogicError, IOException {
        try {
            file = datos.cargarFicheroEncriptado(window);
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

}
