/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sistemaciudadela;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

/**
 * FXML Controller class
 *
 * @author Eliot
 */
public class VistaSimulacionResidenteController implements Initializable {

    @FXML
    private MenuBar ResiMenu;
    @FXML
    private Menu peaton;
    @FXML
    private Menu vehiculo;
    @FXML
    private Menu salir;

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
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setContentText("Estamos teniendo dificultades tecnicas");
                    a.show();

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
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setContentText("Estamos teniendo dificultades tecnicas");
                    a.show();

                }
            }

        });
        peaton.getItems().add(cp);
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
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setContentText("Estamos teniendo dificultades tecnicas");
                    a.show();
                }
            }

        });
        vehiculo.getItems().add(cv);
    }
}
