/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sistemaciudadela.modelo;

import com.mycompany.sistemaciudadela.App;
import com.mycompany.sistemaciudadela.modelo.Residente;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
        String ruta = "archivos/residencias.txt";
        List<Residencia> residencias = new ArrayList<>();
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
            archivo = new File(ruta);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
                String[] u = linea.split("-");
                Residencia r = new Residencia(Double.valueOf(u[0]), Double.valueOf(u[1]), Integer.parseInt(u[2]), Integer.parseInt(u[3]), u[4]);
                residencias.add(r);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        return residencias;
    }
}
