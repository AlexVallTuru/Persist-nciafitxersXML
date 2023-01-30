package Presentacion;

import Errors.DataError;
import Errors.LogicError;
import Modelos.Festivos;
import Logica.Xml_Logica;
import Modelos.AlertsConfig;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PrimaryController implements Initializable {

    Xml_Logica xmlLogica = new Xml_Logica();

    @FXML
    private Button btnCerca;

    @FXML
    private ImageView btnClose;

    @FXML
    private MenuBar btnmenu;

    @FXML
    private ChoiceBox<String> chbxAmbit;

    @FXML
    private DatePicker dpFirst;

    @FXML
    private DatePicker dpSecond;

    @FXML
    private TextField intxtCercaDades;

    @FXML
    private TextField intxtLocalitat;

    @FXML
    private TextField intxtNom;

    @FXML
    private TableView tblView;

    @FXML
    private Button neteja;

    @FXML
    private TextField intxtMunicipi;

    @FXML
    private TableColumn<Festivos, String> lblAmbit;

    @FXML
    private MenuItem exportFile;

    @FXML
    private TableColumn<Festivos, String> lblData;

    @FXML
    private TableColumn<Festivos, String> lblFesta;

    @FXML
    private TableColumn<Festivos, String> lblLocalitat;

    @FXML
    private MenuItem importFile;

    @FXML
    private TableColumn<Festivos, String> lblMunicipi;

    @FXML
    private TableColumn<Festivos, String> lblNom;

    @FXML
    private Text lvlCercaDades;

    @FXML
    private ChoiceBox<String> nombreFiesta;

    @FXML
    void closeApp(MouseEvent event) {
        Platform.exit();
    }

    @FXML
    void limpiarTabla(ActionEvent event) {
        try {

            intxtNom.clear();
            chbxAmbit.setValue("Cap");
            intxtMunicipi.clear();
            intxtLocalitat.clear();
            nombreFiesta.setValue("Festa");
            dpFirst.setValue(null);
            dpSecond.setValue(null);

            tblView.setItems(xmlLogica.neteja(intxtNom.getText(), chbxAmbit.getValue(), intxtMunicipi.getText(), intxtLocalitat.getText(), nombreFiesta.getValue(), dpFirst.getValue(), dpSecond.getValue()));

        } catch (LogicError e) {
            e.printStackTrace();

        }
    }

    @FXML
    void exportarArchivo(ActionEvent event) {
        try {
            //Llamar al controller para exportar la información
            FXMLLoader loader = new FXMLLoader(getClass().getResource("export.fxml"));
            Parent root = loader.load();
            ExportController controller = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            controller.setInfo(tblView.getItems());
            stage.show();
            
        } catch (IOException e) {

        }
    }

    @FXML
    void cercaDades(ActionEvent event) {
        try {
            tblView.setItems(
                    xmlLogica.checkTable(
                            intxtNom.getText(),
                            chbxAmbit.getValue(),
                            intxtMunicipi.getText(),
                            intxtLocalitat.getText(),
                            nombreFiesta.getValue(),
                            dpFirst.getValue(),
                            dpSecond.getValue()));

        } catch (LogicError e) {
            alert.mostrarAlerta(e.getMessage());

        }

        tblView.refresh();

    }

    @FXML
    void importarFichero(ActionEvent event) {
        try {
            tblView.setItems(
                    xmlLogica.cargarFichero(btnmenu.getScene().getWindow()));
            nombreFiesta.getItems().addAll(xmlLogica.fiestas());
        } catch (LogicError e) {
            
            alert.mostrarError(e.getMessage());

        }

    }

    private AlertsConfig alert = new AlertsConfig();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {

            initValues();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initValues() {
        Image image = new Image("images\\close.png");
        tblView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tblView.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        btnClose.setImage(image);
        chbxAmbit.getItems().add("Autonòmic");
        chbxAmbit.getItems().add("Local");
        chbxAmbit.getItems().add("Cap");
        nombreFiesta.setValue("Festa");
        chbxAmbit.setValue("Cap");
        lblNom.setCellValueFactory(new PropertyValueFactory<>("nombreIsla"));
        lblAmbit.setCellValueFactory(new PropertyValueFactory<>("ambito"));
        lblMunicipi.setCellValueFactory(new PropertyValueFactory<>("municipio"));
        lblLocalitat.setCellValueFactory(new PropertyValueFactory<>("localidad"));
        lblData.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        lblFesta.setCellValueFactory(new PropertyValueFactory<>("nombreFiesta"));

    }

}
