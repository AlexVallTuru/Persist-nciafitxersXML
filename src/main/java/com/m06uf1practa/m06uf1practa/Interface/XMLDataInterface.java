/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.m06uf1practa.m06uf1practa.Interface;

import com.m06uf1practa.m06uf1practa.Modelos.Festivos;
import java.io.File;
import java.time.LocalDate;
import javafx.collections.ObservableList;
import javafx.stage.Window;

/**
 *
 * @author Carlos
 */
public interface XMLDataInterface {
    
    public void ImportarDocumento();
    
    public void ExportarDocumento();
    
    public ObservableList<Festivos> FiltrarPorNombre(String nombre);
    
    public ObservableList<Festivos> FiltrarPorFechas(LocalDate d1,LocalDate d2);
    
    public ObservableList<Festivos> FiltrarPorAmbito(String ambit);
    
    public ObservableList<Festivos> FiltrarPorFiesta(String fiesta);
    
    public File cargarFichero(Window w);
            
}
