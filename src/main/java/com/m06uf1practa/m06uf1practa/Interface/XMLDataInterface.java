/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.m06uf1practa.m06uf1practa.Interface;

import com.m06uf1practa.m06uf1practa.Modelos.Festivos;
import java.io.File;
import javafx.collections.ObservableList;
import javafx.stage.Window;

/**
 *
 * @author Carlos
 */
public interface XMLDataInterface {
    
    public void ImportarDocumento();
    
    public void ExportarDocumento();
    
    public void FiltrarPorNombre();
    
    public void FiltrarPorFechas();
    
    public void FiltrarPorAmbito();
    
    public void FiltrarPorFiesta();
    
    public File cargarFichero(Window w);
            
}
