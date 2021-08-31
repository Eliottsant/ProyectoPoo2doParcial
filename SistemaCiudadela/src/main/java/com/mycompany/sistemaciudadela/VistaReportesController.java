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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Eliot
 */
public class VistaReportesController implements Initializable {

    @FXML
    private Menu mapa;
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
                    FXMLLoader loader = new FXMLLoader(App.class.getResource("VistaInicioSesion.fxml"));
                    Parent viewCredito = loader.load();
                    App.setRoot(viewCredito);
                    VistaInicioSesionController principalController
                            = loader.getController();
                } catch (IOException ex) {
                    Alert a = new Alert(AlertType.INFORMATION);
                    a.setContentText("Estamos teniendo dificultades tecnicas");
                    a.show();

                }
            }
        });
        salir.getItems().add(cs);
        MenuItem map = new MenuItem("Ver mapa");
        map.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                try {
                    FXMLLoader loader = new FXMLLoader(App.class.getResource("VistaMapa.fxml"));
                    Parent viewMapa = loader.load();
                    App.setRoot(viewMapa);
                    VistaMapaController mapaController
                            = loader.getController();
                } catch (IOException ex) {
                    Alert a = new Alert(AlertType.INFORMATION);
                    a.setContentText("Estamos teniendo dificultades tecnicas");
                    a.show();

                }
            }

        });
        mapa.getItems().add(map);
    }

    @FXML
    private void primerReporte(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("VistaPrimerGrafico.fxml"));
            Parent viewMapa = loader.load();
            App.setRoot(viewMapa);
            VistaPrimerGraficoController PrimerGraficoController
                    = loader.getController();
        } catch (IOException ex) {
            Alert a = new Alert(AlertType.INFORMATION);
            a.setContentText("Estamos teniendo dificultades tecnicas");
            a.show();

        }
    }

    @FXML
    private void segundoReporte(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("VistaSegundoGrafico.fxml"));
            Parent viewMapa = loader.load();
            App.setRoot(viewMapa);
            VistaSegundoGraficoController SegundoGraficoController
                    = loader.getController();
        } catch (IOException ex) {
            Alert a = new Alert(AlertType.INFORMATION);
            a.setContentText("Estamos teniendo dificultades tecnicas");
            a.show();

        }
    }
}
