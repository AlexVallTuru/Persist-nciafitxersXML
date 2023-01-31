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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
    }

    @FXML
    void close(MouseEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public void recogerDatos(ObservableList<InformeLocalidades> informes) {
        for (InformeLocalidades informe : informes) {

            XYChart.Series dataSeries1 = new XYChart.Series();
            dataSeries1.setName(informe.getLocalidad());
            dataSeries1.getData().add(new XYChart.Data(informe.getCantidad(),informe.getLocalidad()));

            grafica.getData().add(dataSeries1);
        }

    }

}
