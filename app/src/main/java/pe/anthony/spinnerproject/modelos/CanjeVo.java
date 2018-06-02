package pe.anthony.spinnerproject.modelos;

import java.io.Serializable;

public class CanjeVo implements Serializable {
    private String categoria;
    private String marca;
    private String presentacion;
    private int cantidad;
    private double precio;
    private PremioVo premioVo;

    public CanjeVo() {
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public PremioVo getPremioVo() {
        return premioVo;
    }

    public void setPremioVo(PremioVo premioVo) {
        this.premioVo = premioVo;
    }
}
