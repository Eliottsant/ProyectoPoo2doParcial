/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sistemaciudadela;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Eliot
 */
public class VistaSimulacionController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void residente(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("VistaSimulacionResidente.fxml"));
            Parent viewInicioSesion = loader.load();
            App.setRoot(viewInicioSesion);
            VistaSimulacionResidenteController SimulacionResidenteController
                    = loader.getController();
        } catch (IOException ex) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Estamos teniendo dificultades tecnicas");
            a.show();

        }
    }

    @FXML
    private void visita(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("VistaSimulacionVisitante.fxml"));
            Parent viewInicioSesion = loader.load();
            App.setRoot(viewInicioSesion);
            VistaSimulacionVisitanteController SimulacionVisitanteController
                    = loader.getController();
        } catch (IOException ex) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Estamos teniendo dificultades tecnicas");
            a.show();

        }
    }

    @FXML
    private void volver(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("VistaPrincipal.fxml"));
            Parent viewInicioSesion = loader.load();
            App.setRoot(viewInicioSesion);
            VistaPrincipalController PrincipalController
                    = loader.getController();
        } catch (IOException ex) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Estamos teniendo dificultades tecnicas");
            a.show();

        }
    }

}
