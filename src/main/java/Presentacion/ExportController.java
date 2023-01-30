package Presentacion;

import Logica.Xml_Logica;
import Modelos.Festivos;
import Modelos.AlertsConfig;
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
    AlertsConfig alert = new AlertsConfig();
    
    final static String USERHOME = System.getProperty("user.home"); // Variable HOME de l'usuari
    ObservableList<Festivos> export; // ObservableList emmagatzemat

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
            alert.mostrarError("El nom del fitxer no pot ser buit.");
        } else if (passwordFile.getText().equals("")) {
            alert.mostrarError("La contrasenya del fitxer no pot ser buida.");
        } else {
                //Exportar les dades a format XML
                Element root = new Element("row"); //Arrel
                export.stream().map(list -> {
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
                    return element;
                    
                }).forEachOrdered(element -> {
                    //Assignar l'element pare a l'element arrel
                    root.appendChild(element);
                });
                
                //Creacio del document
                Document doc = new Document(root);
                
            try { //Generem i guardem el fitxer
                
                File fitxer = new File(USERHOME, filename.getText() + ".xml"); //Instanciem objecte de tipus file
                //Creem el fitxer dins del sistema
                if (fitxer.createNewFile()) { //Si el fitxer s'ha creat...
                    System.out.println("El fitxer " + fitxer + " s'ha creat correctament.");
                } else { //Si el fitxer no s'ha creat perquè ja existeix
                    alert.mostrarError("Ja existeix un fitxer amb el mateix nom.");
                }
                
                FileWriter fitxerW = new FileWriter(fitxer); //Obrim fitxer per escriure.
                fitxerW.write(doc.toXML()); //Escrivim contingut doc (transformat a String) en fitxer
                //Encriptació Àlex
                
                fitxerW.close(); //Tanquem fitxer
                
                alert.mostrarInfo("El fitxer " + filename.getText() 
                        + ".xml s'ha creat correctament.");
                Stage stage = (Stage) btnExportClose.getScene().getWindow();
                stage.close();
                    
            } catch (IOException e) {
                alert.mostrarError("Error creant el fitxer: " + e);
            }
        }
    }

    public void setInfo(ObservableList<Festivos> OLData) {
        export = OLData;
    }
}