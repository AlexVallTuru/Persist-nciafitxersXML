/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos;

import Errors.DataError;
import Modelos.Festivos;
import Interface.XMLDataInterface;
import Modelos.InformeFiestas;
import Modelos.AlertsConfig;
import Modelos.InformeLocalidades;
import Modelos.Singleton;
import Utils.Utils;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.Base64;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;
import nu.xom.ParsingException;

/**
 *
 * @author Carlos
 */
public class XMLData implements XMLDataInterface {

    Singleton singleton = Singleton.getInstance();
    private ObservableList<Festivos> festivos;
    private ObservableList<Festivos> resultados;
    private SortedSet<String> nombreFiestas;

    AlertsConfig alert = new AlertsConfig();

    /**
     * Obté el document preparat i l'exporta a un XML amb els parametres rebuts.
     *
     * @param export
     * @param USERHOME
     * @param filename
     * @param window
     * @throws Errors.DataError
     * @author Aitor
     */
    @Override
    public void ExportarDocumento(String export, String USERHOME, String filename, Window window) throws DataError {
        try {
            //Demana el directori per guardar el fitxer
            DirectoryChooser saveDirectory = new DirectoryChooser();
            saveDirectory.setInitialDirectory(new File(USERHOME));
            File selectedDirectory = saveDirectory.showDialog(window);

            if (selectedDirectory == null) {
                throw new DataError("Cap directori seleccionat");
            } else {
                File fitxer = new File(selectedDirectory.toString(), filename + ".xml"); //Instanciem objecte de tipus file

                //Creem el fitxer dins del sistema
                if (fitxer.createNewFile()) { //Si el fitxer s'ha creat...
                    System.out.println("El fitxer " + fitxer + " s'ha creat correctament.");
                    FileWriter fitxerW = new FileWriter(fitxer, StandardCharsets.UTF_8); //Obrim fitxer per escriure.
                    fitxerW.write(export); //Escrivim contingut doc (transformat a String) en fitxer
                    fitxerW.close(); //Tanquem fitxer

                    alert.mostrarInfo("El fitxer " + filename
                            + ".xml s'ha creat correctament.");
                } else { //Si el fitxer no s'ha creat perquè ja existeix
                    throw new DataError("Ja existeix un fitxer amb el mateix nom.");
                }
            }
        } catch (IOException e) {
            alert.mostrarError("Error creant el fitxer: " + e);
        }
    }

    /**
     * Filtrar dades amb un sol parametre
     *
     * @param filterCriteria
     * @return
     * @throws DataError
     */
    public ObservableList<Festivos> filtrar(Predicate<Festivos> filterCriteria) throws DataError {
        resultados = FXCollections.observableArrayList();
        for (Festivos valor : festivos) {
            if (filterCriteria.test(valor)) {
                resultados.add(valor);
            }
        }
        if (resultados.isEmpty()) {
            throw new DataError(String.format("Valor no trovat a la taula"));
        } else {
            return resultados;
        }
    }

    /**
     * Filtrar datos por nombre de isla
     *
     * @param nombre
     * @return
     * @throws DataError
     */
    @Override
    public ObservableList<Festivos> FiltrarPorNombre(String nombre) throws DataError {
        resultados = FXCollections.observableArrayList();
        return filtrar(n -> n.getNombreIsla().equalsIgnoreCase(nombre));

    }

    /**
     * Filtra datos por municipio
     *
     * @param municipio
     * @return
     * @throws DataError
     */
    @Override
    public ObservableList<Festivos> FiltrarPorMunicipio(String municipio) throws DataError {
        resultados = FXCollections.observableArrayList();
        return filtrar(n -> n.getMunicipio().equalsIgnoreCase(municipio));

    }

    /**
     * Filtrar datos entre 2 fechas
     *
     * @param d1
     * @param d2
     * @return
     * @throws DataError
     */
    @Override
    public ObservableList<Festivos> FiltrarPorFechas(LocalDate d1, LocalDate d2) throws DataError {
        resultados = FXCollections.observableArrayList();
        for (Festivos valor : festivos) {

            if (valor.getFecha().isAfter(d1) && valor.getFecha().isBefore(d2)) {
                resultados.add(valor);

            }
        }
        if (resultados.isEmpty()) {
            throw new DataError(String.format("No s'han trovat dades entre %s y %s", d1.toString(), d2.toString()));
        } else {
            return resultados;
        }
    }

    /**
     * Filtrar datos por Ambito
     *
     * @param ambit
     * @return
     * @throws DataError
     */
    @Override
    public ObservableList<Festivos> FiltrarPorAmbito(String ambit) throws DataError {
        resultados = FXCollections.observableArrayList();
        return filtrar(n -> n.getAmbito().equalsIgnoreCase(ambit));

    }

    /**
     * Filtrar datos por nombre de fiesta
     *
     * @param fiesta
     * @return
     * @throws DataError
     */
    @Override
    public ObservableList<Festivos> FiltrarPorFiesta(String fiesta) throws DataError {
        resultados = FXCollections.observableArrayList();
        return filtrar(n -> n.getNombreFiesta().equalsIgnoreCase(fiesta));

    }

    /**
     * Filtrar datos por localidad
     *
     * @param localidad
     * @return
     * @throws DataError
     */
    @Override
    public ObservableList<Festivos> FiltrarPorLocalidad(String localidad) throws DataError {
        resultados = FXCollections.observableArrayList();
        return filtrar(n -> n.getLocalidad().equalsIgnoreCase(localidad));

    }

    /**
     * Cargar fichero
     *
     * @param window
     * @return
     * @throws DataError
     */
    @Override
    public File cargarFichero(Window window) throws DataError {

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

                return selectedFile;
            } else {
                throw new DataError(String.format("El fitxer %s no es valid", fileName));

            }
        }
        throw new DataError("No s'ha seleccionat un fitxer");
    }

    /**
     * Filtrar datos de manera completa
     *
     * @param nom
     * @param ambit
     * @param localitat
     * @param municipi
     * @param nombreFiesta
     * @param d1
     * @param d2
     * @return
     * @throws DataError
     */
    public ObservableList<Festivos> FiltradoCompleto(String nom, String ambit, String localitat, String municipi, String nombreFiesta, LocalDate d1, LocalDate d2) throws DataError {

        resultados = FXCollections.observableArrayList();
        for (Festivos valor : festivos) {

            if ((valor.getNombreIsla().equalsIgnoreCase(nom) || nom.isEmpty())
                    && (valor.getAmbito().equalsIgnoreCase(ambit) || ambit.equals("Cap"))
                    && (valor.getMunicipio().equalsIgnoreCase(municipi) || municipi.isEmpty())
                    && (valor.getLocalidad().equalsIgnoreCase(localitat) || localitat.isEmpty())
                    && (valor.getNombreFiesta().equals(nombreFiesta) || nombreFiesta.equals("Fiesta"))
                    && (valor.getFecha().isAfter(d1) && valor.getFecha().isBefore(d2))) {
                resultados.add(valor);

            }
        }

        if (resultados.isEmpty()) {
            throw new DataError("No s'han trovat resultats");
        } else {
            return resultados;
        }

    }

    /**
     * Leer fichero y almacenar sus valores
     *
     * @param fichero
     * @return
     * @throws DataError
     */
    public ObservableList<Festivos> leerFichero(File fichero) throws DataError {
        festivos = FXCollections.observableArrayList();
        festivos.clear();
        nombreFiestas = new TreeSet<>();
        Utils utils = new Utils();
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

                nombreFiestas.add(utils.capitalizeName(nom_festaValue));
                festivos.add(new Festivos(illaValue, mbitValue, municipiValue, localitatValue, utils.convertLocalDate(dataValue), utils.capitalizeName(nom_festaValue)));

            }
            return festivos;

        } catch (ParsingException e) {
            throw new DataError(e.getMessage());
        } catch (IOException e) {
            throw new DataError("Error al llegir el fitxer");
        }

    }

    /**
     * Ordenar fiestas alfabeticamente
     *
     * @return
     */
    public SortedSet<String> fiestas() {
        return this.nombreFiestas;
    }

    public ObservableList<Festivos> tableView() {
        return festivos;
    }

    /**
     * Generar informe almacenando nombre de isla y contador
     *
     * @return
     */
    public ObservableList<InformeFiestas> generaInforme() {
        ObservableList<InformeFiestas> informe = FXCollections.observableArrayList();
        Set<String> nombres = new HashSet<String>();

        for (Festivos festivo : festivos) {
            nombres.add(festivo.getNombreIsla());
        }

        for (String nombre : nombres) {
            int count = 0;
            for (int i = 0; i < festivos.size(); i++) {
                if (festivos.get(i).getNombreIsla().equals(nombre)) {
                    count++;
                }

            }
            informe.add(new InformeFiestas(nombre, count));
        }

        return informe;

    }

    /**
     * Generar informe de les localitats
     *
     * @return
     */
    public ObservableList<InformeLocalidades> generaInformeLocalidades() {
        ObservableList<InformeLocalidades> informe = FXCollections.observableArrayList();
        Set<String> nombres = new HashSet<String>();

        for (Festivos festivo : festivos) {
            nombres.add(festivo.getLocalidad());
        }

        for (String nombre : nombres) {
            int count = 0;
            for (int i = 0; i < festivos.size(); i++) {
                if (festivos.get(i).getLocalidad().equals(nombre)) {
                    count++;
                }

            }
            informe.add(new InformeLocalidades(nombre, count));
        }

        return informe;

    }

    /**
     * Encripta el mensaje dado utilizando la encriptación XOR con la clave
     * dada.
     *
     * @param message el mensaje a encriptar
     * @param key la clave a utilizar para la encriptación
     * @return el mensaje encriptado codificado en base64
     */
    public static String Encriptacion(String message, String key) {
        byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);
        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
        byte[] resultado = new byte[messageBytes.length];

        for (int i = 0; i < messageBytes.length; i++) {
            resultado[i] = (byte) (messageBytes[i] ^ keyBytes[i % keyBytes.length]);
        }
        return Base64.getEncoder().encodeToString(resultado);
    }

    /**
     * Desencripta el mensaje codificado en base64 dado utilizando la
     * encriptación XOR con la clave dada.
     *
     * @param mensaje el mensaje encriptado codificado en base64
     * @param key la clave utilizada para la encriptación
     * @return el mensaje desencriptado
     */
    public static String Desencriptacion(String mensaje, String key) {
        byte[] encodedMessageBytes = Base64.getDecoder().decode(mensaje);
        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
        byte[] resultado = new byte[encodedMessageBytes.length];
        for (int i = 0; i < encodedMessageBytes.length; i++) {
            resultado[i] = (byte) (encodedMessageBytes[i] ^ keyBytes[i % keyBytes.length]);
        }
        return new String(resultado, StandardCharsets.UTF_8);
    }

    public File cargarFicheroEncriptado(Window window) throws DataError {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open XML File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("XML Files", "*.xml")
        );
        File selectedFile = fileChooser.showOpenDialog(window);
        String encriptedString = fileToString(selectedFile);
        String datoslimpios = Desencriptacion(encriptedString, Singleton.getContrasena());
        try ( FileWriter fileWriter = new FileWriter(selectedFile)) {
            fileWriter.write(datoslimpios);
            System.out.println("El fitxer s'ha escrit correctament");
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error: " + e.getMessage());
        }
        //System.out.println(selectedFile);
        if (selectedFile != null) {
            String fileName = selectedFile.getName();
            if (fileName.endsWith(".xml")) {

                return selectedFile;
            } else {
                throw new DataError(String.format("El fitxer %s no es valid", fileName));

            }
        }
        throw new DataError("No s'ha seleccionat un fitxer");
    }
    /**
     * Se le pasa un file y se transforma a String
     * @param selectedFile
     * @return 
     */
    public static String fileToString(File selectedFile) {
        try {
            byte[] encoded = Files.readAllBytes(selectedFile.toPath());
            return new String(encoded, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
