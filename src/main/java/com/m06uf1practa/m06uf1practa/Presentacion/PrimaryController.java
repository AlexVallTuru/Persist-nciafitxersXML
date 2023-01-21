package com.m06uf1practa.m06uf1practa.Presentacion;

import com.m06uf1practa.m06uf1practa.Modelos.Festivos;
import com.m06uf1practa.m06uf1practa.Logica.Xml_Logica;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

public class PrimaryController implements Initializable {

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
    private TextField intxtMunicipi;

    @FXML
    private TableColumn<Festivos, String> lblAmbit;

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

    Xml_Logica xmlLogica = new Xml_Logica();

    @FXML
    private Text lvlCercaDades;

    @FXML
    void closeApp(MouseEvent event) {
        Platform.exit();
    }
    
    @FXML
    void cercaDades(ActionEvent event) {
        //xmlLogica.clearTable(tblView);
        System.out.println(chbxAmbit.getValue());
        tblView.setItems(xmlLogica.checkTable(intxtNom.getText(),chbxAmbit.getValue(),intxtMunicipi.getText()));
        if(!intxtNom.getText().isEmpty() && intxtMunicipi.getText().isEmpty()  ){
            tblView.setItems(xmlLogica.cercaDades(intxtNom.getText(),chbxAmbit.getValue(),
                intxtLocalitat.getText(),intxtMunicipi.getText()));
            
        }
        
    }

    @FXML
    void importarFichero(ActionEvent event) {
        
        tblView.setItems(xmlLogica.cargarFichero(btnmenu.getScene().getWindow()));

    }
    
    

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
        btnClose.setImage(image);
        chbxAmbit.getItems().add("Autonòmic");
        chbxAmbit.getItems().add("Local");
        chbxAmbit.getItems().add("Cap");
        chbxAmbit.setValue("Ambit");
        
        lblNom.setCellValueFactory(new PropertyValueFactory<>("nombreIsla"));
        lblAmbit.setCellValueFactory(new PropertyValueFactory<>("ambito"));
        lblMunicipi.setCellValueFactory(new PropertyValueFactory<>("municipio"));
        lblLocalitat.setCellValueFactory(new PropertyValueFactory<>("localidad"));
        lblData.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        lblFesta.setCellValueFactory(new PropertyValueFactory<>("nombreFiesta"));

    }
    
    

}
