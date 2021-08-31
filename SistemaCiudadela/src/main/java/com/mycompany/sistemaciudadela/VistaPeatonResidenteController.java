/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sistemaciudadela;

import com.mycompany.sistemaciudadela.modelo.Administrador;
import com.mycompany.sistemaciudadela.modelo.Residente;
import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Eliot
 */
public class VistaPeatonResidenteController implements Initializable {

    @FXML
    private Menu Peaton;
    @FXML
    private Menu vehiculo;
    @FXML
    private Menu salir;
    @FXML
    private TextField cedula;
    @FXML
    private TextField pin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MenuItem cs = new MenuItem("Cerrar Sesion");
        cs.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                try {
                    FXMLLoader loader = new FXMLLoader(App.class.getResource("VistaPrincipal.fxml"));
                    Parent viewCredito = loader.load();
                    App.setRoot(viewCredito);
                    VistaPrincipalController principalController
                            = loader.getController();
                } catch (IOException ex) {
                    System.out.println("No se ha podido cargar la vista");
                    System.out.println("VistaRegistro.fxml");

                }
            }
        });
        salir.getItems().add(cs);
        MenuItem cv = new MenuItem("Entrar Vehiculo");
        cv.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                try {
                    FXMLLoader loader = new FXMLLoader(App.class.getResource("VistaVehiculoResidente.fxml"));
                    Parent viewCredito = loader.load();
                    App.setRoot(viewCredito);
                    VistaVehiculoResidenteController principalController
                            = loader.getController();
                } catch (IOException ex) {
                    System.out.println("No se ha podido cargar la vista");
                    System.out.println("VistaVehiculoResidente.fxml");

                }
            }

        });
        vehiculo.getItems().add(cv);
    }

    @FXML
    private void ingresar(MouseEvent event) {
        List<Residente> residentes = Residente.cargarResidentes();
        String c = "nada";
        for (Residente r : residentes) {
            if (cedula.getText().equals(r.getCedula()) && pin.getText().equals(r.getPin())) {
                c = r.getCedula();
            }
        }
        System.out.println(c);
        if (cedula.getText().equals(c)) {
            Alert a = new Alert(AlertType.INFORMATION);
            a.setContentText("Acceso Concedido");
            a.show();
            Administrador.registrarEntrada("Residente", LocalTime.now());
        } else {
            Alert a = new Alert(AlertType.ERROR);
            a.setContentText("Acceso Denegado");
            a.show();
        }

    }
}
