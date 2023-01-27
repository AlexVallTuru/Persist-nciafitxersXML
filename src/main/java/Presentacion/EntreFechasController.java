package Presentacion;

import Modelos.InformeFiestas;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class EntreFechasController implements Initializable {

    @FXML
    private CategoryAxis categoria;

    @FXML
    private NumberAxis numeros;
    
    @FXML
    private ImageView closeButton;
        
    @FXML
    private BarChart<String, Integer> grafica;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

    public void recogerDatos(ObservableList<InformeFiestas> informes) {
        for (InformeFiestas informe : informes) {
            Image image = new Image("images\\close.png");
            closeButton.setImage(image);
            XYChart.Series dataSeries1 = new XYChart.Series();
            dataSeries1.setName(informe.getNombreIsla());
            dataSeries1.getData().add(new XYChart.Data(informe.getNombreIsla(), informe.getCantidad()));

            grafica.getData().add(dataSeries1);
        }

    }
    
        @FXML
    void close(MouseEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

}
