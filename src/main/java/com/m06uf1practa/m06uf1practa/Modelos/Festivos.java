/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.m06uf1practa.m06uf1practa.Modelos;

/**
 *
 * @author Carlos
 */
public class Festivos {
    private String nombreIsla;
    private String ambito;
    private String municipio;
    private String localidad;
    private String fecha;
    private String nombreFiesta;

    public Festivos() {
    }

    public Festivos(String nombreIsla, String ambito, String municipio, String localidad, String fecha, String nombreFiesta) {
        this.nombreIsla = nombreIsla;
        this.ambito = ambito;
        this.municipio = municipio;
        this.localidad = localidad;
        this.fecha = fecha;
        this.nombreFiesta = nombreFiesta;
    }

    public String getNombreIsla() {
        return nombreIsla;
    }

    public void setNombreIsla(String nombreIsla) {
        this.nombreIsla = nombreIsla;
    }

    public String getAmbito() {
        return ambito;
    }

    public void setAmbito(String ambito) {
        this.ambito = ambito;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNombreFiesta() {
        return nombreFiesta;
    }

    public void setNombreFiesta(String nombreFiesta) {
        this.nombreFiesta = nombreFiesta;
    }

    @Override
    public String toString() {
        return "Festivos{" + "nombreIsla=" + nombreIsla + ", ambito=" + ambito + ", municipio=" + municipio + ", localidad=" + localidad + ", fecha=" + fecha + ", nombreFiesta=" + nombreFiesta + '}';
    }
    
    
}
