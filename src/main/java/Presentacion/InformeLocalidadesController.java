package Presentacion;

import Modelos.InformeLocalidades;
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

public class InformeLocalidadesController implements Initializable {
    Utils utils = new Utils();
    @FXML
    private ImageView closeButton;
    
    @FXML
    private BarChart<String, Integer> grafica;
    
    /**
     * Inicialitzar finestra
     * @param location
     * @param resources 
     */

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image image = new Image(utils.convertPath("images\\close.png"));
        closeButton.setImage(image);
        grafica.autosize();
        
    }
    
    /**
     * Funcionalitat per tancar finestra
     * @param event 
     */
    
    @FXML
    void close(MouseEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
    
    /**
     * Recollir dades per mostrar els informes de les localitats
     * @param informes 
     */
    public void recogerDatos(ObservableList<InformeLocalidades> informes) {
        
        for (int i=0 ; i<informes.size();i++) {
            System.out.println(informes.get(i));
            XYChart.Series dataSeries1 = new XYChart.Series();
            dataSeries1.setName(informes.get(i).getLocalidad());
            dataSeries1.getData().add(new XYChart.Data(informes.get(i).getLocalidad(),informes.get(i).getCantidad()));

            grafica.getData().add(dataSeries1);
        }

    }

}
