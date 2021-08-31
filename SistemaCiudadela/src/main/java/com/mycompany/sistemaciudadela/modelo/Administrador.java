/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sistemaciudadela.modelo;

import com.mycompany.sistemaciudadela.App;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Eliot
 */
public class Administrador {

    public static void agregarResidente(Residencia residencia, String nombre, Residente resi) {
        try {

            File archivo = new File("src/main/resources/com/mycompany/sistemaciudadela/residencias.txt");
            boolean estatus = archivo.delete();
            if (!estatus) {
                System.out.println("Error no se ha podido eliminar el  archivo");
            } else {
                System.out.println("Se ha eliminado el archivo exitosamente");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        List<Residencia> residencias = Residencia.cargarResidencias();
        List<Residencia> resis = new ArrayList<>();
        for (Residencia r : residencias) {
            if (r.getVilla() == residencia.getVilla()) {
                r.setResidente(nombre);
                resis.add(r);
            } else {
                resis.add(r);
            }
        }
        try {
            String ruta = "src/main/resources/com/mycompany/sistemaciudadela/residencias.txt";
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
            fichero = new FileWriter("src/main/resources/com/mycompany/sistemaciudadela/residencias.txt");
            pw = new PrintWriter(fichero);

            for (Residencia rs : resis) {
                pw.println(rs.getX() + "-" + rs.getY() + "-" + rs.getManzana() + "-" + rs.getVilla() + "-" + rs.getResidente());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Nuevamente aprovechamos el finally para 
                // asegurarnos que se cierra el fichero.
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        FileWriter f = null;
        PrintWriter p = null;
        try {
            f = new FileWriter("src/main/resources/com/mycompany/sistemaciudadela/residentes.txt", true);
            p = new PrintWriter(f);
            p.println(resi.getNombre() + "-" + resi.getCedula() + "-" + resi.getCorreo() + "-" + resi.getGenero() + "-" + resi.getPin() + "-" + resi.getUsername() + "-" + resi.getContraseña() + "-" + resi.getManzana() + "-" + resi.getVilla());
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

    public static void mover(VBox v) {
        final Delta dragDelta = new Delta();
        v.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                // record a delta distance for the drag and drop operation.
                dragDelta.x = v.getLayoutX() - mouseEvent.getSceneX();
                dragDelta.y = v.getLayoutY() - mouseEvent.getSceneY();
                v.setCursor(Cursor.MOVE);
            }
        });
        v.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                v.setCursor(Cursor.HAND);
            }
        });
        v.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                v.setLayoutX(mouseEvent.getSceneX() + dragDelta.x);
                v.setLayoutY(mouseEvent.getSceneY() + dragDelta.y);
            }
        });
        v.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                v.setCursor(Cursor.HAND);
            }
        });

    }

    public static String generarUsername(String nombre) {
        String[] n = nombre.split("");
        int ind = n.length;
        String user = n[0] + n[1] + n[ind - 2] + n[ind - 1];
        return user;
    }

    public static String generarPin() {
        int n1 = (int) Math.floor(Math.random() * (9 - 0 + 1) + 0);
        int n2 = (int) Math.floor(Math.random() * (9 - 0 + 1) + 0);
        int n3 = (int) Math.floor(Math.random() * (9 - 0 + 1) + 0);
        int n4 = (int) Math.floor(Math.random() * (9 - 0 + 1) + 0);
        String pin = Integer.toString(n1) + Integer.toString(n2) + Integer.toString(n3) + Integer.toString(n4);
        return pin;

    }

    public static String generarContraseña(String username) {
        int n1 = (int) Math.floor(Math.random() * (9 - 0 + 1) + 0);
        int n2 = (int) Math.floor(Math.random() * (9 - 0 + 1) + 0);
        int n3 = (int) Math.floor(Math.random() * (9 - 0 + 1) + 0);
        int n4 = (int) Math.floor(Math.random() * (9 - 0 + 1) + 0);
        String contraseña = username + Integer.toString(n1) + Integer.toString(n2) + Integer.toString(n3);
        return contraseña;
    }

    public static void enviarCorreo(String correo, String user, String contra, String pin) {
        String from = "eliottsant@outlook.com";
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", "smtp.office365.com");
        properties.setProperty("mail.smtp.port", "587");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.auth", "true");
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("eliottsant@outlook.com", "eliott2000"); // Contraseña de la cuenta
            }
        });
        try {
            MimeMessage message = new MimeMessage(session);
            message.addHeader("X-Mailer", "Microsoft Outlook Express 6.00.2900.2869");
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(correo));
            message.setSubject("Registro de su residencia");
            message.setText("Username: " + user + "\n" + "Contraseña: " + contra + "\n" + "Pin: " + pin);
            Transport.send(message);
            // System.out.println ("¡Enviado con éxito!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static void registrarEntrada(String tipo, LocalTime hora) {
        FileWriter f = null;
        PrintWriter p = null;
        try {
            f = new FileWriter("src/main/resources/com/mycompany/sistemaciudadela/entradas.txt", true);
            p = new PrintWriter(f);
            p.println(tipo + "-" + hora);
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

    public static List<Integer> leerEntradas() {
        String ruta = "entradas.txt";
        List<Integer> horas = new ArrayList<>();
        try ( InputStream input = App.class.getResource(ruta).openStream();  BufferedReader bf = new BufferedReader(
                new InputStreamReader(input, "UTF-8"))) {
            String linea;
            while ((linea = bf.readLine()) != null) {
                String[] u = linea.split("-");
                String[] h = u[1].split(":");
                horas.add(Integer.parseInt(h[0]));

            }
        } catch (IOException ex) {
            System.out.println("No se pudo cargar la info");
            ex.printStackTrace();
        }
        return horas;
    }

    public static List<Integer> leerEntradasVR() {
        String ruta = "entradas.txt";
        List<Integer> tipos = new ArrayList<>();
        try ( InputStream input = App.class.getResource(ruta).openStream();  BufferedReader bf = new BufferedReader(
                new InputStreamReader(input, "UTF-8"))) {
            String linea;
            int r = 0;
            int v = 0;
            while ((linea = bf.readLine()) != null) {
                String[] u = linea.split("-");
                if (u[0].equals("Residente")) {
                    r++;
                } else {
                    v++;
                }
            }
            tipos.add(r);
            tipos.add(v);
        } catch (IOException ex) {
            System.out.println("No se pudo cargar la info");
            ex.printStackTrace();
        }
        return tipos;

    }
}

class Delta {
    double x, y;
}
