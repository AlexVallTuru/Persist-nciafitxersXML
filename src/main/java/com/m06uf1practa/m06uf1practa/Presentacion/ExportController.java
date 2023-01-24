package com.m06uf1practa.m06uf1practa.Presentacion;

import com.m06uf1practa.m06uf1practa.Logica.Xml_Logica;
import com.m06uf1practa.m06uf1practa.Modelos.Festivos;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import nu.xom.Document;
import nu.xom.Element;

public class ExportController {
    
    Xml_Logica xmlLogica = new Xml_Logica();
    PrimaryController primary = new PrimaryController();
    
    final static String USERHOME = System.getProperty("user.home");

    @FXML
    private Button btnExport;

    @FXML
    private ImageView btnExportClose;

    @FXML
    private TextField filename;

    @FXML
    private PasswordField passwordFile;

    @FXML
    void closeExportWindow(MouseEvent event) {
        Stage stage = (Stage) btnExportClose.getScene().getWindow();
        stage.close();
    }

    @FXML
    void exportFile(MouseEvent event) {
        if (filename.getText().equals("")) {
            
        } else if (passwordFile.getText().equals("")) {
            
        } else {
                //Obtenir les dades per exportar
                ObservableList<Festivos> export = primary.exportDadesView();
                
                //Exportar les dades a format XML
                Element root = new Element("row"); //Arrel
                for (Festivos list: export) {
                    //Element pare
                    Element element = new Element("row");
                    
                    //Elements fills
                    Element illa = new Element("illa");
                    Element ambit = new Element("mbit");
                    Element municipi = new Element("municipi");
                    Element localitat = new Element("localitat");
                    Element data = new Element("data");
                    Element nomFesta = new Element("nom_festa");
                    
                    //Afegir valors als elements
                    illa.appendChild(list.getNombreIsla());
                    ambit.appendChild(list.getAmbito());
                    municipi.appendChild(list.getMunicipio());
                    localitat.appendChild(list.getLocalidad());
                    data.appendChild(list.getFecha().toString());
                    nomFesta.appendChild(list.getNombreFiesta());
                    
                    //Posar tots els elements fills sota l'element pare
                    element.appendChild(illa);
                    element.appendChild(ambit);
                    element.appendChild(municipi);
                    element.appendChild(localitat);
                    element.appendChild(data);
                    element.appendChild(nomFesta);
                    
                    //Assignar l'element pare a l'element arrel
                    root.appendChild(element);
                }
                
                //Creacio del document
                Document doc = new Document(root);
                
            try {
                
                String nouXml = USERHOME + File.separator + filename.getText();//Creació del fitxer
                // Encriptació d'Àlex

                FileWriter fitxer = new FileWriter(new File(nouXml),false); //Obrim fitxer per escriure
                

            } catch (IOException e) {
                
            }
        }
    }

}