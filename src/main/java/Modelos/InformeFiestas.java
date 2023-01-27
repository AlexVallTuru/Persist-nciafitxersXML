/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

/**
 *
 * @author carlo
 */
public class InformeFiestas {
    private String nombreIsla;
    private Integer cantidad;

    public InformeFiestas() {
    }

    public InformeFiestas(String nombreIsla, Integer cantidad) {
        this.nombreIsla = nombreIsla;
        this.cantidad = cantidad;
    }

    public String getNombreIsla() {
        return nombreIsla;
    }

    public void setNombreIsla(String nombreIsla) {
        this.nombreIsla = nombreIsla;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "InformeFiestas{" + "nombreIsla=" + nombreIsla + ", cantidad=" + cantidad + '}';
    }

    
    
}
