/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

/**
 *  Clase per emmagatzemar les dades per als informes de les localitats
 * @author carlo
 */
public class InformeLocalidades {
    private String localidad;
    private Integer cantidad;

    public InformeLocalidades(String localidad, Integer cantidad) {
        this.localidad = localidad;
        this.cantidad = cantidad;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "InformeLocalidades{" + "localidad=" + localidad + ", cantidad=" + cantidad + '}';
    }
    
}
