/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interface;

import Errors.LogicError;
import Modelos.Festivos;
import java.io.File;
import javafx.collections.ObservableList;
import javafx.stage.Window;

/**
 *  Interfaces per a la capa logica
 * @author Carlos
 */
public interface XMLLogicInterface {

    public ObservableList<Festivos> cargarFichero(Window w) throws LogicError;

}
