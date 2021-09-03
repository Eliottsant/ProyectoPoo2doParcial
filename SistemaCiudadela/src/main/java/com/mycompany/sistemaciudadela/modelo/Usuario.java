/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sistemaciudadela.modelo;

import com.mycompany.sistemaciudadela.App;
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
public class Usuario {

    String username;
    String contraseña;

    public Usuario(String username, String contraseña) {
        this.username = username;
        this.contraseña = contraseña;

    }

    public String getUsername() {
        return username;
    }

    public String getContraseña() {
        return contraseña;
    }

    public static List<Usuario> cargarUsuarios() {
        String ruta = "archivos/usuarios.txt";
        List<Usuario> usuarios = new ArrayList<>();
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
                Usuario usu = new Usuario(u[0], u[1]);
                usuarios.add(usu);
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

        return usuarios;
    }
}
