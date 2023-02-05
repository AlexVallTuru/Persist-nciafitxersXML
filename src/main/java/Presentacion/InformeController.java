package Presentacion;

import Logica.Xml_Logica;
import Modelos.InformeFiestas;
import Utils.Utils;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class InformeController implements Initializable {
    Utils utils = new Utils();
    Xml_Logica logica = new Xml_Logica();
    @FXML
    private TableColumn<InformeFiestas, Integer> cantidad;

    @FXML
    private TableColumn<InformeFiestas, String> isla;
    
    @FXML
    private ImageView closeButton;

    @FXML
    private TableView<InformeFiestas> tabla;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tabla.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tabla.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        cantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        isla.setCellValueFactory(new PropertyValueFactory<>("nombreIsla"));
        Image image = new Image(utils.convertPath("images\\close.png"));
        closeButton.setImage(image);
    }

    public void setTable(ObservableList<InformeFiestas> lista) {
        tabla.setItems(lista);

    }
    
    @FXML
    void close(MouseEvent event) {
        Stage stage =(Stage) closeButton.getScene().getWindow();
        stage.close();
    }
    

}
