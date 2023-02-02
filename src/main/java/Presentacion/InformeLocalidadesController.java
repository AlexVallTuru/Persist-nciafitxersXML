package Presentacion;

import Modelos.InformeFiestas;
import Modelos.InformeLocalidades;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ScrollBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class InformeLocalidadesController implements Initializable {

    @FXML
    private ImageView closeButton;
    
    @FXML
    private BarChart<String, Integer> grafica;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image image = new Image("images\\close.png");
        closeButton.setImage(image);
        //grafica.autosize();
        
    }
    
    @FXML
    void close(MouseEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public void recogerDatos(ObservableList<InformeLocalidades> informes) {
        
        for (int i=0 ; i<25;i++) {
            System.out.println(informes.get(i));
            XYChart.Series dataSeries1 = new XYChart.Series();
            dataSeries1.setName(informes.get(i).getLocalidad());
            dataSeries1.getData().add(new XYChart.Data(informes.get(i).getLocalidad(),informes.get(i).getCantidad()));

            grafica.getData().add(dataSeries1);
        }

    }

}
