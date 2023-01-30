package Presentacion;

import Logica.Xml_Logica;
import Modelos.Festivos;
import Modelos.AlertsConfig;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ExportController {
    
    Xml_Logica xmlLogica = new Xml_Logica();
    PrimaryController primary = new PrimaryController();
    AlertsConfig alert = new AlertsConfig();
    
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
     * @author Aitor
     */
    @FXML
    void exportFile(MouseEvent event) {
        if (filename.getText().equals("")) {
            alert.mostrarError("El nom del fitxer no pot ser buit.");
        } else if (passwordFile.getText().equals("")) {
            alert.mostrarError("La contrasenya del fitxer no pot ser buida.");
        } else {
            //Crida a metodes d'exportació
            xmlLogica.exportaDades(export, USERHOME, filename.getText());
            //Tanca la finestra
            Stage stage = (Stage) btnExport.getScene().getWindow();
            stage.close();
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