/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sistemaciudadela.modelo;

import com.mycompany.sistemaciudadela.App;
import com.mycompany.sistemaciudadela.modelo.Residente;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Eliot
 */
public class Residencia {

    private double x;
    private double y;
    private int manzana;
    private int villa;
    private String residente;

    public Residencia(double x, double y, int manzana, int villa, String residente) {
        this.x = x;
        this.y = y;
        this.manzana = manzana;
        this.villa = villa;
        this.residente = residente;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getManzana() {
        return manzana;
    }

    public int getVilla() {
        return villa;
    }

    public String getResidente() {
        return residente;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setManzana(int manzana) {
        this.manzana = manzana;
    }

    public void setVilla(int villa) {
        this.villa = villa;
    }

    public void setResidente(String residente) {
        this.residente = residente;
    }
    

    public static List<Residencia> cargarResidencias() {
        String ruta = "residencias.txt";
        List<Residencia> residencias = new ArrayList<>();
        try ( InputStream input = App.class.getResource(ruta).openStream();  BufferedReader bf = new BufferedReader(
                new InputStreamReader(input, "UTF-8"))) {
            String linea;
            while ((linea = bf.readLine()) != null) {
                System.out.println(linea);
                String[] u = linea.split("-");
                Residencia r = new Residencia(Double.valueOf(u[0]), Double.valueOf(u[1]), Integer.parseInt(u[2]), Integer.parseInt(u[3]),u[4]);
                residencias.add(r);
            }
        } catch (IOException ex) {
            System.out.println("No se pudo cargar la info");
            ex.printStackTrace();
        }
        return residencias;
    }
}
