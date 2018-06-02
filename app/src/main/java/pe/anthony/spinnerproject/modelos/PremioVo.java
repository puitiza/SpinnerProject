package pe.anthony.spinnerproject.modelos;

import java.io.Serializable;

public class PremioVo implements Serializable {
    private String premio1;
    private int cantidadP1;

    private String premio2;
    private int cantidadP2;

    private String premio3;
    private int cantidadP3;

    private String premio4;
    private int cantidadP4;

    public PremioVo() {
    }

    public String getPremio1() {
        return premio1;
    }

    public void setPremio1(String premio1) {
        this.premio1 = premio1;
    }

    public int getCantidadP1() {
        return cantidadP1;
    }

    public void setCantidadP1(int cantidadP1) {
        this.cantidadP1 = cantidadP1;
    }

    public String getPremio2() {
        return premio2;
    }

    public void setPremio2(String premio2) {
        this.premio2 = premio2;
    }

    public int getCantidadP2() {
        return cantidadP2;
    }

    public void setCantidadP2(int cantidadP2) {
        this.cantidadP2 = cantidadP2;
    }

    public String getPremio3() {
        return premio3;
    }

    public void setPremio3(String premio3) {
        this.premio3 = premio3;
    }

    public int getCantidadP3() {
        return cantidadP3;
    }

    public void setCantidadP3(int cantidadP3) {
        this.cantidadP3 = cantidadP3;
    }

    public String getPremio4() {
        return premio4;
    }

    public void setPremio4(String premio4) {
        this.premio4 = premio4;
    }

    public int getCantidadP4() {
        return cantidadP4;
    }

    public void setCantidadP4(int cantidadP4) {
        this.cantidadP4 = cantidadP4;
    }
}
