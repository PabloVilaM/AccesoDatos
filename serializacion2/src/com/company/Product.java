package com.company;

import java.io.Serializable;

public class Product implements Serializable     {
    private String codigo;
    private String descripcion;
    private double precio;

    public Product (){
        codigo = "";
        descripcion = "";
        precio = 0;
    }

    public Product (String code, String descr, double prec){
        codigo = code;
        descripcion = descr;
        precio = prec;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Product{" +
                "codigo='" + codigo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                '}';
    }
}
