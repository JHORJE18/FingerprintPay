package com.jhorje18.fingerprintpay.Objetos;

/**
 * Created by aeolservice on 13/3/18.
 */

public class Producto {

    //Variables
    private String titulo;
    private String descripcion;
    private int precio;
    private String urlPicture;

    public Producto() {
    }

    public Producto(String titulo, String descripcion, int precio, String urlPicture) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.precio = precio;
        this.urlPicture = urlPicture;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getUrlPicture() {
        return urlPicture;
    }

    public void setUrlPicture(String urlPicture) {
        this.urlPicture = urlPicture;
    }
}
