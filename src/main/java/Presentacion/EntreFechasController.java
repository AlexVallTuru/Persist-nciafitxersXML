package Presentacion;

import Modelos.InformeFiestas;
import Utils.Utils;
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

public class EntreFechasController implements Initializable {
    Utils utils = new Utils();
            
    @FXML
    private ImageView closeButton;

    @FXML
    private BarChart<String, Integer> grafica;
    
    /**
     * Inicialitzar la finestra de la grafica
     * @param location
     * @param resources 
     */

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image image = new Image(utils.convertPath("images\\close.png"));
        closeButton.setImage(image);
    }
    
    /**
     * Rebre dades de la table view per mostrar
     * @param informes 
     */
    public void recogerDatos(ObservableList<InformeFiestas> informes) {
        for (InformeFiestas informe : informes) {

            XYChart.Series dataSeries1 = new XYChart.Series();
            dataSeries1.setName(informe.getNombreIsla());
            dataSeries1.getData().add(new XYChart.Data(informe.getNombreIsla(), informe.getCantidad()));

            grafica.getData().add(dataSeries1);
        }

    }

    /**
     * Funció per tancar la finestra
     * @param event 
     */
    @FXML
    void close(MouseEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

}
