/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Presentacion;

import Errors.LogicError;
import Logica.Xml_Logica;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Carlos
 */
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import Presentacion.PrimaryController;

public class ImportFileController {

    Xml_Logica xmlLogica = new Xml_Logica();
    @FXML
    private Button confirmar;

    @FXML
    private Button negar;

    @FXML
    void confirmar(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ImportEncrypted.fxml"));
            Parent root = loader.load();
            ImportEncryptedController controller = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.showAndWait();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @FXML
    void negar(ActionEvent event) {
        try {

            xmlLogica.cargarFichero(negar.getScene().getWindow());

            // nombreFiesta.getItems().addAll(xmlLogica.fiestas());
        } catch (LogicError e) {
            e.printStackTrace();
            //alert.mostrarError(e.getMessage());

        }

    }

}
