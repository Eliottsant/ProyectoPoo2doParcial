/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sistemaciudadela;

import com.mycompany.sistemaciudadela.modelo.Residente;
import com.mycompany.sistemaciudadela.modelo.Usuario;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Eliot
 */
public class VistaInformacionController implements Initializable {

    @FXML
    private Menu info;
    @FXML
    private Menu vehiculos;
    @FXML
    private Menu visitas;
    @FXML
    private Menu salir;
    @FXML
    private VBox informacion;
    @FXML
    private Pane panelInfo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Residente r = Residente.verInfo();
        Label nom = new Label("Nombre: " + r.getNombre());
        Label cor = new Label("Cedula: " + r.getCedula());
        Label ced = new Label("Correo:" + r.getCorreo());
        Label villa = new Label("Villa:" + r.getVilla());
        Label man = new Label("Manzana:" + r.getManzana());
        Label pin = new Label("Pin:" + r.getPin());
        informacion.getChildren().addAll(nom, ced, cor, villa, man, pin);
        MenuItem cs = new MenuItem("Cerrar Sesion");
        cs.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                r.borrarInfo();
                try {
                    FXMLLoader loader = new FXMLLoader(App.class
                            .getResource("VistaInicioSesion.fxml"));
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
        salir.getItems().add(cs);
        MenuItem veh = new MenuItem("Registrar vehiculo");
        veh.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                try {
                    FXMLLoader loader = new FXMLLoader(App.class
                            .getResource("VistaVehiculos.fxml"));
                    Parent viewVehiculos = loader.load();

                    App.setRoot(viewVehiculos);
                    VistaVehiculosController vehiculosController
                            = loader.getController();
                } catch (IOException ex) {
                    System.out.println("No se ha podido cargar la vista");
                    System.out.println("VistaVehiculos.fxml");

                }
            }

        });
        vehiculos.getItems().add(veh);
        MenuItem visi = new MenuItem("Ver visitas");
        visi.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                try {
                    FXMLLoader loader = new FXMLLoader(App.class
                            .getResource("VistaVisitas.fxml"));
                    Parent viewVisitas = loader.load();

                    App.setRoot(viewVisitas);
                    VistaVisitasController visitasController
                            = loader.getController();
                } catch (IOException ex) {
                    System.out.println("No se ha podido cargar la vista");
                    System.out.println("VistaVisitas.fxml");

                }
            }

        });
        visitas.getItems().add(visi);
    }

    @FXML
    private void cambiar(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(App.class
                    .getResource("VistaCambiarPin.fxml"));
            Parent viewVisitas = loader.load();

            App.setRoot(viewVisitas);
            VistaCambiarPinController cambiarPinController
                    = loader.getController();
        } catch (IOException ex) {
            System.out.println("No se ha podido cargar la vista");
            System.out.println("VistaCambiarPin.fxml");

        }
    }

}
