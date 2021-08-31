/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sistemaciudadela;

import com.mycompany.sistemaciudadela.modelo.Administrador;
import com.mycompany.sistemaciudadela.modelo.Residente;
import com.mycompany.sistemaciudadela.modelo.Visita;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Eliot
 */
public class VistaSimulacionVisitanteController implements Initializable {

    @FXML
    private Label lblPin;
    @FXML
    private TextField pin;
    @FXML
    private Button ingresar;
    @FXML
    private Label lblNombre;
    @FXML
    private TextField nombre;
    @FXML
    private Label lblCedula;
    @FXML
    private TextField cedula;
    @FXML
    private Label lblNomResi;
    @FXML
    private TextField nombreResidente;
    @FXML
    private Label lblManzana;
    @FXML
    private TextField manzana;
    @FXML
    private Label lblVilla;
    @FXML
    private TextField villa;
    @FXML
    private Button bNotificar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblPin.setVisible(false);
        pin.setVisible(false);
        ingresar.setVisible(false);
        lblNombre.setVisible(false);
        nombre.setVisible(false);
        lblCedula.setVisible(false);
        lblNomResi.setVisible(false);
        cedula.setVisible(false);
        nombreResidente.setVisible(false);
        lblManzana.setVisible(false);
        manzana.setVisible(false);
        lblVilla.setVisible(false);
        villa.setVisible(false);
        bNotificar.setVisible(false);
    }

    @FXML
    private void ingresar(MouseEvent event) {
        try {
            String p = pin.getText();
            Visita v = null;
            for (Visita vi : Residente.verVisitasTodas()) {
                if (vi.getPin().equals(p)) {
                    v = vi;
                }
            }
            if (p.equals(v.getPin())) {
                if (Visita.verHora(v)) {
                    Alert a = new Alert(AlertType.CONFIRMATION);
                    a.setContentText("Acceso concedido");
                    a.show();
                    Visita.registrarEntrada(p);
                    Administrador.registrarEntrada("Visitante", LocalTime.now());

                } else {
                    Alert a = new Alert(AlertType.ERROR);
                    a.setContentText("Codigo expirado");
                    a.show();
                }
            } else if (p.equals("Expirado")) {
                Alert a = new Alert(AlertType.ERROR);
                a.setContentText("El codigo ya fue usado");
                a.show();
            }
        } catch (NullPointerException ex) {
            Alert a = new Alert(AlertType.ERROR);
            a.setContentText("Acceso Denegado");
            a.show();
        }

    }

    @FXML
    private void si(MouseEvent event) {
        lblPin.setVisible(true);
        pin.setVisible(true);
        ingresar.setVisible(true);
        lblNombre.setVisible(false);
        nombre.setVisible(false);
        lblCedula.setVisible(false);
        lblNomResi.setVisible(false);
        cedula.setVisible(false);
        nombreResidente.setVisible(false);
        lblManzana.setVisible(false);
        manzana.setVisible(false);
        lblVilla.setVisible(false);
        villa.setVisible(false);
        bNotificar.setVisible(false);
    }

    @FXML
    private void no(MouseEvent event) {
        lblNombre.setVisible(true);
        nombre.setVisible(true);
        lblCedula.setVisible(true);
        lblNomResi.setVisible(true);
        cedula.setVisible(true);
        nombreResidente.setVisible(true);
        lblManzana.setVisible(true);
        manzana.setVisible(true);
        lblVilla.setVisible(true);
        villa.setVisible(true);
        bNotificar.setVisible(true);
        lblPin.setVisible(false);
        pin.setVisible(false);
        ingresar.setVisible(false);
    }

    @FXML
    private void notificar(MouseEvent event) {
        String n = nombre.getText();
        String ced = cedula.getText();
        String nomresi = nombreResidente.getText();
        String m = manzana.getText();
        String v = villa.getText();
        String c = "nada";
        int p=0;
        String correo = "";
        String pin = Visita.generarCodigo();
        for (Residente r : Residente.cargarResidentes()) {
            if (nomresi.equals(r.getNombre())) {
                c = r.getNombre();
                correo = r.getCorreo();
                System.out.println(r.getVilla());
                if(v.equals(String.valueOf(r.getVilla()))){
                    p=r.getVilla();                   
                }
            }
        }
        System.out.println(c);
        System.out.println(p);
        System.out.println(m);
        System.out.println(v);
        if (nomresi.equals(c)) {
            Alert a = new Alert(AlertType.INFORMATION);
            a.setContentText("Se ha enviado un correo al residente con su pin\n si lo tiene ingreselo");
            a.show();
            Visita.enviarNotificacion(n, correo, pin);
            Visita vis = new Visita(n, ced, correo, "En proceso", pin, LocalDateTime.now(), c);
            Residente.registroGeneralVisitas(vis, c);
        } else {
            Alert a = new Alert(AlertType.ERROR);
            a.setContentText("La informacion que ingreso es incorrecta");
            a.show();
        }
    }

    @FXML
    private void salir(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("VistaPrincipal.fxml"));
            Parent viewPrincipal = loader.load();
            App.setRoot(viewPrincipal);
            VistaPrincipalController principalController
                    = loader.getController();
        } catch (IOException ex) {
            System.out.println("No se ha podido cargar la vista");
            System.out.println("VistaPrincipal.fxml");

        }
    }

}
