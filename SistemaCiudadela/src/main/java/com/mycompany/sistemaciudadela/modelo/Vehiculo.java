/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sistemaciudadela.modelo;

import com.mycompany.sistemaciudadela.App;
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
public class Vehiculo {

    private String propietario;
    private String matricula;

    public Vehiculo(String propietario, String matricula) {
        this.propietario = propietario;
        this.matricula = matricula;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public static List<String> leerMatriculas() {
        String ruta = "Vehiculos.txt";
        List<String> matriculas = new ArrayList<>();
        try ( InputStream input = App.class.getResource(ruta).openStream();  BufferedReader bf = new BufferedReader(
                new InputStreamReader(input, "UTF-8"))) {
            String linea;
            while ((linea = bf.readLine()) != null) {
                System.out.println(linea);
                matriculas.add(linea);
            }
        } catch (IOException ex) {
            System.out.println("No se pudo cargar la info");
            ex.printStackTrace();
        }
        return matriculas;
    }
}
