package com.example.simulacroexamen03.modelos;

import java.io.Serializable;

public class Partido implements Serializable {
    private String equipo1;
    private String equipo2;
    private int goles1;
    private int goles2;
    private String resumen;

    public Partido(String equipo1, String equipo2, int goles1, int goles2, String resumen) {
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
        this.goles1 = goles1;
        this.goles2 = goles2;
        this.resumen = resumen;
    }

    public String getEquipo1() {return equipo1;}
    public void setEquipo1(String equipo1) {this.equipo1 = equipo1;}

    public String getEquipo2() {return equipo2;}
    public void setEquipo2(String equipo2) {this.equipo2 = equipo2;}

    public int getGoles1() {return goles1;}
    public void setGoles1(int goles1) {this.goles1 = goles1;}

    public int getGoles2() {return goles2;}
    public void setGoles2(int goles2) {this.goles2 = goles2;}

    public String getResumen() {return resumen;}
    public void setResumen(String resumen) {this.resumen = resumen;}
}
