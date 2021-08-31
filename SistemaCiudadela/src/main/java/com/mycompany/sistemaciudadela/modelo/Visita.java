/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sistemaciudadela.modelo;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
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
public class Visita {

    public final SimpleStringProperty nombre;
    public final SimpleStringProperty cedula;
    public final SimpleStringProperty correo;
    public final SimpleStringProperty estado;
    public final SimpleStringProperty pin;
    public LocalDateTime fecha;
    private String nombreResidente;

    public Visita(String nombre, String cedula, String correo, String estado, String pin, LocalDateTime fecha, String nombreResidente) {
        this.nombre = new SimpleStringProperty(nombre);
        this.cedula = new SimpleStringProperty(cedula);
        this.correo = new SimpleStringProperty(correo);
        this.estado = new SimpleStringProperty(estado);
        this.pin = new SimpleStringProperty(pin);
        this.fecha = fecha;
        this.nombreResidente = nombreResidente;
    }

    public String getNombreResidente() {
        return nombreResidente;
    }

    public String getPin() {
        return pin.get();
    }

    public String getNombre() {
        return nombre.get();
    }

    public String getCedula() {
        return cedula.get();
    }

    public String getCorreo() {
        return correo.get();
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public String getEstado() {
        return estado.get();
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public final void setNombre(String value) {
        nombre.set(value);
    }

    public final void setCedula(String value) {
        cedula.set(value);
    }

    public final void setCorreo(String value) {
        correo.set(value);
    }

    public final void setPin(String value) {
        pin.set(value);
    }

    public final void setEstado(String value) {
        estado.set(value);
    }
    public SimpleStringProperty sfecha(){
        SimpleStringProperty f= new SimpleStringProperty(fecha+"");
        return f;
    }

    public static void enviarcorreo(String correo, String pin) {
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
            message.setSubject("Registro de Visita");
            message.setText("Su codigo tendra una validacion de 5 minutos despues de la fecha indicada\n" + "Su codigo es: " + pin);
            Transport.send(message);
            // System.out.println ("¡Enviado con éxito!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static String generarCodigo() {
        int n1 = (int) Math.floor(Math.random() * (9 - 0 + 1) + 0);
        int n2 = (int) Math.floor(Math.random() * (9 - 0 + 1) + 0);
        int n3 = (int) Math.floor(Math.random() * (9 - 0 + 1) + 0);
        int n4 = (int) Math.floor(Math.random() * (9 - 0 + 1) + 0);
        int n5 = (int) Math.floor(Math.random() * (9 - 0 + 1) + 0);
        int n6 = (int) Math.floor(Math.random() * (9 - 0 + 1) + 0);
        int n7 = (int) Math.floor(Math.random() * (9 - 0 + 1) + 0);
        int n8 = (int) Math.floor(Math.random() * (9 - 0 + 1) + 0);
        String code = Integer.toString(n1) + Integer.toString(n2) + Integer.toString(n3) + Integer.toString(n4) + Integer.toString(n5) + Integer.toString(n6) + Integer.toString(n7) + Integer.toString(n8);
        return code;
    }

    public static void enviarNotificacion(String nombre, String correo, String pin) {
        String from = "eliottsant@outlook.com";
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", "smtp.office365.com");
        properties.setProperty("mail.smtp.port", "587");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.auth", "true");
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("eliottsant@outlook.com", "eliott2000");
            }
        });
        try {
            MimeMessage message = new MimeMessage(session);
            message.addHeader("X-Mailer", "Microsoft Outlook Express 6.00.2900.2869");
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(correo));
            message.setSubject("Notificacion de ingreso");
            message.setText("La persona con nombre " + nombre + " esta intentando ingresar se le envia un pin para el accseso, si no conoce a esta persona notifique en garita\n Pin: " + pin);
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static void borrarVisitaGeneral(String visita) {
        try {

            File archivo = new File("src/main/resources/com/mycompany/sistemaciudadela/visitas.txt");
            boolean estatus = archivo.delete();
            if (!estatus) {
                System.out.println("Error no se ha podido eliminar el  archivo");
            } else {
                System.out.println("Se ha eliminado el archivo exitosamente");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        List<Visita> visitas = Residente.verVisitasTodas();
        List<Visita> visis = new ArrayList<>();
        for (Visita v : visitas) {
            if (v.getNombre().equals(visita)) {
            } else {
                visis.add(v);
            }
        }
        try {
            String ruta = "src/main/resources/com/mycompany/sistemaciudadela/visitas.txt";
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
            fichero = new FileWriter("src/main/resources/com/mycompany/sistemaciudadela/visitas.txt");
            pw = new PrintWriter(fichero);

            for (Visita vs : visis) {
                pw.println(vs.getNombre() + "/" + vs.getCedula() + "/" + vs.getCorreo() + "/" + vs.getEstado() + "/" + vs.getPin() + "/" + vs.getFecha()+"/"+vs.getNombreResidente());
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

    public static boolean verHora(Visita v) {
        LocalDateTime act = LocalDateTime.now();
        LocalDateTime despues = act.plusMinutes(5);
        LocalDateTime antes = act.minusMinutes(5);
        if (v.getFecha().isEqual(act) || (v.getFecha().isBefore(despues) && v.getFecha().isAfter(antes)) || v.getFecha().isEqual(antes) || v.getFecha().isEqual(despues) || !v.getFecha().isBefore(antes)) {
            return true;
        } else {
            return false;
        }

    }

    public static void registrarEntrada(String pin) {
        try {

            File archivo = new File("src/main/resources/com/mycompany/sistemaciudadela/visitas.txt");
            boolean estatus = archivo.delete();
            if (!estatus) {
                System.out.println("Error no se ha podido eliminar el  archivo");
            } else {
                System.out.println("Se ha eliminado el archivo exitosamente");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        List<Visita> visitas = Residente.verVisitasTodas();
        List<Visita> visis = new ArrayList<>();
        for (Visita v : visitas) {
            if (v.getPin().equals(pin)) {
                v.setPin("Expirado");
                v.setEstado("Ingreso");
                visis.add(v);
            } else {
                visis.add(v);
            }
        }
        try {
            String ruta = "src/main/resources/com/mycompany/sistemaciudadela/visitas.txt";
            File file = new File(ruta);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter("src/main/resources/com/mycompany/sistemaciudadela/visitas.txt");
            pw = new PrintWriter(fichero);

            for (Visita vs : visis) {
                pw.println(vs.getNombre() + "/" + vs.getCedula() + "/" + vs.getCorreo() + "/" + vs.getEstado() + "/" + vs.getPin() + "/" + vs.getFecha()+"/"+vs.getNombreResidente());
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
