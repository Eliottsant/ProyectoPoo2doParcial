/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sistemaciudadela;

import java.io.IOException;
import java.io.InputStream;
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
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Eliot
 */
public class VistaAdminController implements Initializable {

    @FXML
    private Pane panelAdmin;
    //@FXML
    //private MenuBar menuAdmin;
    @FXML
    private Menu mapa;
    @FXML
    private Menu cerrarSesion;
    @FXML
    private Menu reportes;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MenuItem s = new MenuItem("Cerrar Sesion");
        s.setOnAction(new EventHandler<ActionEvent>() {
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
        cerrarSesion.getItems().add(s);
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
        MenuItem rep = new MenuItem("Ver reportes");
        rep.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                try {
                    FXMLLoader loader = new FXMLLoader(App.class.getResource("VistaReportes.fxml"));
                    Parent viewMapa = loader.load();
                    App.setRoot(viewMapa);
                    VistaReportesController reporteController
                            = loader.getController();
                } catch (IOException ex) {
                    Alert a = new Alert(AlertType.INFORMATION);
                    a.setContentText("Estamos teniendo dificultades tecnicas");
                    a.show();

                }

            }
        });
        reportes.getItems().add(rep);
    }

}
