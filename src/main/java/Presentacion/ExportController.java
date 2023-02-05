package Presentacion;

import Errors.DataError;
import Logica.Xml_Logica;
import Modelos.Festivos;
import Modelos.AlertsConfig;
import Modelos.Singleton;
import java.util.regex.Pattern;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * Controla la pantalla de exports
 * 
 * @author mole6
 */
public class ExportController {
    
    Singleton singleton = Singleton.getInstance();
    Xml_Logica xmlLogica = new Xml_Logica();
    PrimaryController primary = new PrimaryController();
    AlertsConfig alert = new AlertsConfig();
    
    private final static Pattern INVALID_FILENAME = Pattern.compile("[\\\\/:*?\"<>|]+"); //Patro per trobar noms de fitxer invalids
    final static String USERHOME = System.getProperty("user.home"); // Variable HOME de l'usuari
    ObservableList<Festivos> export; // ObservableList emmagatzemat

    @FXML
    private Button btnExport;

    @FXML
    private ImageView btnExportClose;

    @FXML
    private TextField filename;

    @FXML
    private PasswordField passwordFile;

    /**
     * Tanca la finextra
     * 
     * @param event 
     * @author Aitor
     */
    @FXML
    void closeExportWindow(MouseEvent event) {
        Stage stage = (Stage) btnExportClose.getScene().getWindow();
        stage.close();
    }

    /**
     * Comproba que els camps de nom i contrasenya siguin correctes i comença
     * la exportació.
     * 
     * @param event 
     * @throws Errors.DataError
     * @author Aitor
     */
    @FXML
    void exportFile(MouseEvent event) throws DataError{
        if (filename.getText().equals("")) {
            alert.mostrarError("El nom del fitxer no pot ser buit.");
        } else if (INVALID_FILENAME.matcher(filename.getText()).find()) {
            alert.mostrarError("El nom de l'arxiu es invalid.");
        } else if (passwordFile.getText().equals("")) {
            alert.mostrarError("La contrasenya del fitxer no pot ser buida.");
        } else {
            try {
                //Crida a metodes d'exportació
                singleton.setContrasena(passwordFile.getText());
                xmlLogica.exportaDades(export, USERHOME, filename.getText(), btnExport.getScene().getWindow());
                //Tanca la finestra
                Stage stage = (Stage) btnExport.getScene().getWindow();
                stage.close();
            } catch (DataError e) {
                alert.mostrarError(e.getMessage());
            }
        }
    }

    /**
     * Agafa l'ObservableList del primary controller i l'asigna a una variable
     * del controller local.
     * 
     * @param OLData 
     * @author Aitor
     */
    public void setInfo(ObservableList<Festivos> OLData) {
        export = OLData;
    }
}