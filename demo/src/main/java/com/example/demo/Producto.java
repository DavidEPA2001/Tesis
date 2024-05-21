package com.example.demo;

public class Producto {
    private int ID;
    private String Nombre;
    private String Codigo;
    private String Marca;
    private int Cantidad;
    private double PrecioCompra;
    private double PrecioVenta;

    // Constructors, getters, and setters go here

    // You can create constructors and add getters and setters for the fields.
    // Here's an example constructor:

    public Producto(int ID, String Nombre, String Codigo, String Marca, int Cantidad, double PrecioCompra, double PrecioVenta) {
        this.ID = ID;
        this.Nombre = Nombre;
        this.Codigo = Codigo;
        this.Marca = Marca;

        this.Cantidad = Cantidad;
        this.PrecioCompra = PrecioCompra;
        this.PrecioVenta = PrecioVenta;
    }

    // Getters and setters for the fields:

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String Marca) {
        this.Marca = Marca;
    }


    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int Cantidad) {
        this.Cantidad = Cantidad;
    }

    public double getPrecioCompra() {
        return PrecioCompra;
    }

    public void setPrecioCompra(double PrecioCompra) {
        this.PrecioCompra = PrecioCompra;
    }

    public double getPrecioVenta() {
        return PrecioVenta;
    }

    public void setPrecioVenta(double PrecioVenta) {
        this.PrecioVenta = PrecioVenta;
    }
}
