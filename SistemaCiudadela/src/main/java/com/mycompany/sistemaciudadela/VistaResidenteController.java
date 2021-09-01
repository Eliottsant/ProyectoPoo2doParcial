/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sistemaciudadela;

import com.mycompany.sistemaciudadela.modelo.Residente;
import com.mycompany.sistemaciudadela.modelo.Visita;
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
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Eliot
 */
public class VistaResidenteController implements Initializable {

    @FXML
    private MenuBar residenteMenu;
    @FXML
    private Menu info;
    @FXML
    private Menu vehiculos;
    @FXML
    private Menu visitas;
    @FXML
    private Menu salir;

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
                    System.out.println("No se ha podido cargar la vista");
                    System.out.println("VistaRegistro.fxml");

                }
            }

        });
        salir.getItems().add(s);
        MenuItem inf = new MenuItem("Ver informacion");
        inf.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                try {
                    FXMLLoader loader = new FXMLLoader(App.class.getResource("VistaInformacion.fxml"));
                    Parent viewCredito = loader.load();
                    App.setRoot(viewCredito);
                    VistaInformacionController principalController
                            = loader.getController();
                } catch (IOException ex) {
                    System.out.println("No se ha podido cargar la vista");
                    System.out.println("VistaRegistro.fxml");

                }
            }

        });
        info.getItems().add(inf);
        MenuItem visi = new MenuItem("Ver visitas");
        visi.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                try {
                    FXMLLoader loader = new FXMLLoader(App.class.getResource("VistaVisitas.fxml"));
                    Parent viewVisitas = loader.load();
                    App.setRoot(viewVisitas);
                    VistaVisitasController visitasController
                            = loader.getController();
                } catch (IOException ex) {
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setContentText("Estamos teniendo dificultades tecnicas");
                    a.show();

                }
            }

        });
        visitas.getItems().add(visi);
        MenuItem veh = new MenuItem("Registrar vehiculo");
        veh.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                try {
                    FXMLLoader loader = new FXMLLoader(App.class.getResource("VistaVehiculos.fxml"));
                    Parent viewVehiculos = loader.load();
                    App.setRoot(viewVehiculos);
                    VistaVehiculosController vehiculosController
                            = loader.getController();
                } catch (IOException ex) {
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setContentText("Estamos teniendo dificultades tecnicas");
                    a.show();
                }
            }

        });
        vehiculos.getItems().add(veh);

    }
}
