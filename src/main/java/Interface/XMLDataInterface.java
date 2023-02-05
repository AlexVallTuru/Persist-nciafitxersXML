/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interface;

import Errors.DataError;
import Modelos.Festivos;
import java.io.File;
import java.time.LocalDate;
import javafx.collections.ObservableList;
import javafx.stage.Window;

/**
 *  Interface per a les funcions de capa dades
 * @author Carlos
 */
public interface XMLDataInterface {

    public void ExportarDocumento(String doc, String USERHOME, String filename, Window window) throws DataError;

    public ObservableList<Festivos> FiltrarPorNombre(String nombre) throws DataError;

    public ObservableList<Festivos> FiltrarPorFechas(LocalDate d1, LocalDate d2) throws DataError;

    public ObservableList<Festivos> FiltrarPorAmbito(String ambit) throws DataError;

    public ObservableList<Festivos> FiltrarPorFiesta(String fiesta) throws DataError;

    public ObservableList<Festivos> FiltrarPorMunicipio(String municipio) throws DataError;

    public ObservableList<Festivos> FiltrarPorLocalidad(String localidad) throws DataError;

    public File cargarFichero(Window w) throws DataError;

}
