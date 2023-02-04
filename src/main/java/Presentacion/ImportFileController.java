/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Presentacion;

import Errors.LogicError;
import Logica.Xml_Logica;
import Modelos.Festivos;
import Modelos.Singleton;

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
import java.io.IOException;
import java.util.List;
import javafx.collections.ObservableList;

public class ImportFileController {

    private Festivos festivos;
    private ObservableList<Festivos> festivosLista;
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
            stage = (Stage) negar.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    void negar(ActionEvent event) throws LogicError, IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("primary.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        PrimaryController controller = loader.getController();
        // Pasando la lista al controlador ImportEncryptedController
        stage = (Stage) negar.getScene().getWindow();
        stage.close();
        setFestivos(festivosLista = xmlLogica.cargarFichero(negar.getScene().getWindow()));

    }

    public void setFestivos(List<Festivos> festivos) {
        Singleton singleton = Singleton.getInstance();
        ObservableList<Festivos> listaFestivos = singleton.getListaFestivos();
        listaFestivos.clear();
        listaFestivos.addAll(festivos);
    }
}