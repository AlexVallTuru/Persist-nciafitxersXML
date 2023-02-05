package Presentacion;

import Errors.LogicError;
import Logica.Xml_Logica;
import Modelos.Festivos;
import Modelos.Singleton;
import java.io.IOException;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

public class ImportEncryptedController {

    Singleton singleton = Singleton.getInstance();

    private Festivos festivos;
    private ObservableList<Festivos> festivosLista;
    Xml_Logica xmlLogica = new Xml_Logica();
    @FXML
    private Button btnAccedir;

    @FXML
    private PasswordField txtPassword;

    @FXML
    void onAction_Accedir(ActionEvent event) throws LogicError, IOException {
        if (txtPassword.toString() == null) {
            System.err.println("Saltar aviso de poner contraseña");
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("primary.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            PrimaryController controller = loader.getController();
            stage = (Stage) btnAccedir.getScene().getWindow();
            stage.close();
            singleton.setContrasena(txtPassword.getText());
            //setFestivos(festivosLista = xmlLogica.cargarFicheroEncriptado(btnAccedir.getScene().getWindow()));
        }

    }

    public void setFestivos(List<Festivos> festivos) {
        ObservableList<Festivos> listaFestivos = singleton.getListaFestivos();
        listaFestivos.clear();
        listaFestivos.addAll(festivos);
    }
}
