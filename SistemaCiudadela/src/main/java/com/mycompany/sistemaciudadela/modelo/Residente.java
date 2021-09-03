/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sistemaciudadela.modelo;

import com.mycompany.sistemaciudadela.App;
import com.mycompany.sistemaciudadela.modelo.Vehiculo;
import com.mycompany.sistemaciudadela.modelo.Visita;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Eliot
 */
public class Residente extends Usuario {

    private String nombre;
    private String cedula;
    private String correo;
    private String genero;
    private String pin;
    private int villa;
    private int manzana;
    private ArrayList<Vehiculo> vehiculos;
    private List<Visita> visitas;

    public Residente(String nombre, String cedula, String correo, String genero, String pin, int villa, int manzana, String username, String contraseña) {
        super(username, contraseña);
        this.nombre = nombre;
        this.cedula = cedula;
        this.correo = correo;
        this.genero = genero;
        this.pin = pin;
        this.villa = villa;
        this.manzana = manzana;
    }

    public String getNombre() {
        return nombre;
    }

    public void setVilla(int villa) {
        this.villa = villa;
    }

    public void setManzana(int manzana) {
        this.manzana = manzana;
    }

    public int getVilla() {
        return villa;
    }

    public int getManzana() {
        return manzana;
    }

    public String getCedula() {
        return cedula;
    }

    public String getCorreo() {
        return correo;
    }

    public String getGenero() {
        return genero;
    }

    public String getPin() {
        return pin;
    }

    public String getUsername() {
        return username;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public List<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public List<Visita> getVisitas() {
        return visitas;
    }

    public static List<Residente> cargarResidentes() {
        List<Residente> residentes = new ArrayList<>();
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        try {
            archivo = new File("archivos/residentes.txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] u = linea.split("-");
                Residente r = new Residente(u[0], u[1], u[2], u[3], u[4], Integer.parseInt(u[7]), Integer.parseInt(u[8]), u[5], u[6]);
                residentes.add(r);
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

        return residentes;
    }

    public void registrarInfo(String user) {
        try {
            String ruta = "archivos/user.txt";
            String contenido = user;
            File file = new File(ruta);
            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();

            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(contenido);
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Residente verInfo() {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        String linea = null;
        try {
            archivo = new File("archivos/user.txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            linea = br.readLine();

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
        for (Residente r : cargarResidentes()) {
            if (r.getNombre().equals(linea)) {
                return r;
            }
        }
        return null;
    }

    public void borrarInfo() {
        try {

            File archivo = new File("archivo/user.txt");
            boolean estatus = archivo.delete();
            if (!estatus) {
                System.out.println("Error no se ha podido eliminar el  archivo");
            } else {
                System.out.println("Se ha eliminado el archivo exitosamente");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void registrarVehiculo(Vehiculo v) {
        FileWriter f = null;
        PrintWriter p = null;
        try {
            f = new FileWriter("archivos/Vehiculos.txt", true);
            p = new PrintWriter(f);
            p.println(v.getMatricula());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Nuevamente aprovechamos el finally para 
                // asegurarnos que se cierra el fichero.
                if (null != f) {
                    f.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static void registroGeneralVisitas(Visita v, String nombre) {
        FileWriter f = null;
        PrintWriter p = null;
        try {
            f = new FileWriter("archivos/visitas.txt", true);
            p = new PrintWriter(f);
            p.println(v.getNombre() + "/" + v.getCedula() + "/" + v.getCorreo() + "/" + "En proceso" + "/" + v.getPin() + "/" + v.getFecha() + "/" + nombre);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != f) {
                    f.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static List<Visita> verVisitasTodas() {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        List<Visita> visitas = new ArrayList<>();
        try {
            archivo = new File("archivos/visitas.txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
                String[] u = linea.split("/");
                LocalDateTime fecha = LocalDateTime.parse(u[5]);
                Visita v = new Visita(u[0], u[1], u[2], u[3], u[4], fecha, u[6]);
                visitas.add(v);
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

        return visitas;

    }

    public static void cambiarPin(String nombre, String pin) {
        List<Residente> residentes = Residente.cargarResidentes();
        try {
            File archivo = new File("archivos/residentes.txt");
            boolean estatus = archivo.delete();
            if (!estatus) {
                System.out.println("Error no se ha podido eliminar el  archivo");
            } else {
                System.out.println("Se ha eliminado el archivo exitosamente");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        List<Residente> resis = new ArrayList<>();
        for (Residente r : residentes) {
            if (r.getNombre().equals(nombre)) {
                r.setPin(pin);
                resis.add(r);
            } else {
                resis.add(r);
            }
        }
        try {
            String ruta = "archivos/residentes.txt";
            File file = new File(ruta);
            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter("archivos/residentes.txt");
            pw = new PrintWriter(fichero);

            for (Residente rs : resis) {
                pw.println(rs.getNombre() + "-" + rs.getCedula() + "-" + rs.getCorreo() + "-" + rs.getGenero() + "-" + rs.getPin() + "-" + rs.getUsername() + "-" + rs.getContraseña() + "-" + rs.getManzana() + "-" + rs.getVilla());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
