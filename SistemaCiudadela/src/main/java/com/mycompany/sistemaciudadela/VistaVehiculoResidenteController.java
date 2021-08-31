/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sistemaciudadela;

import com.mycompany.sistemaciudadela.modelo.Administrador;
import com.mycompany.sistemaciudadela.modelo.Vehiculo;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
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
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Eliot
 */
public class VistaVehiculoResidenteController implements Initializable {

    @FXML
    private Menu peaton;
    @FXML
    private Menu vehiculo;
    @FXML
    private Menu salir;
    @FXML
    private TextField matricula;

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
        MenuItem cp = new MenuItem("Entrar peaton");
        cp.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                try {
                    FXMLLoader loader = new FXMLLoader(App.class.getResource("VistaPeatonResidente.fxml"));
                    Parent viewCredito = loader.load();
                    App.setRoot(viewCredito);
                    VistaPeatonResidenteController principalController
                            = loader.getController();
                } catch (IOException ex) {
                    System.out.println("No se ha podido cargar la vista");
                    System.out.println("VistaRegistro.fxml");

                }
            }

        });
        peaton.getItems().add(cp);
    }

    @FXML
    private void ingresarVehiculo(MouseEvent event) {
        List<String> matriculas = Vehiculo.leerMatriculas();
        String c = "nada";
        for (String m : matriculas) {
            if (matricula.getText().equals(m)) {
                c = m;
            }
        }
        System.out.println(c);
        if (matricula.getText().equals(c)) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Acceso Concedido");
            a.show();
            Administrador.registrarEntrada("Residente", LocalTime.now());
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Acceso Denegado");
            a.show();
        }

    }

}
