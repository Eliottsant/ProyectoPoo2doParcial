/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sistemaciudadela;

import com.mycompany.sistemaciudadela.modelo.Residente;
import com.mycompany.sistemaciudadela.modelo.Vehiculo;
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
public class VistaVehiculosController implements Initializable {

    @FXML
    private Menu info;
    @FXML
    private Menu vehiculos;
    @FXML
    private Menu visitas;
    @FXML
    private Menu salir;
    @FXML
    private TextField propietario;
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
                Residente r = Residente.verInfo();
                r.borrarInfo();
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
        salir.getItems().add(cs);
        MenuItem inf = new MenuItem("Ver información");
        inf.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                try {
                    FXMLLoader loader = new FXMLLoader(App.class.getResource("VistaInformacion.fxml"));
                    Parent viewSalir = loader.load();
                    App.setRoot(viewSalir);
                    VistaInformacionController principalController
                            = loader.getController();
                } catch (IOException ex) {
                    System.out.println("No se ha podido cargar la vista");
                    System.out.println("VistaInformacion.fxml");

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
                    System.out.println("No se ha podido cargar la vista");
                    System.out.println("VistaVisitas.fxml");

                }
            }

        });
        visitas.getItems().add(visi);

    }

    @FXML
    private void registrar(MouseEvent event) {
        Vehiculo v = new Vehiculo(propietario.getText(), matricula.getText());
        List<Residente> residentes = Residente.cargarResidentes();
        try {
            for (Residente r : residentes) {
                if (Residente.verInfo().getNombre().equals(r.getNombre())) {
                    Residente.registrarVehiculo(v);
                }
            }
            Alert a = new Alert(AlertType.INFORMATION);
            a.setContentText("Se ha registrado el vehiculo correctamente");
            a.show();
        } catch (NullPointerException ex) {
            Alert a = new Alert(AlertType.ERROR);
            a.setContentText("No se ha registrado el vehiculo correctamente");
            a.show();
            System.out.println(ex.getMessage());
        }
    }
}
