/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sistemaciudadela;

import com.mycompany.sistemaciudadela.modelo.Administrador;
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
public class VistaPrincipalController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    

    @FXML
    private void inicioSesion(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("VistaInicioSesion.fxml"));
            Parent viewInicioSesion = loader.load();
            App.setRoot(viewInicioSesion);
            VistaInicioSesionController inicioSesionController
                    = loader.getController();
        } catch (IOException ex) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Estamos teniendo dificultades tecnicas");
            a.show();

        }
    }

    @FXML
    private void simulacion(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("VistaSimulacion.fxml"));
            Parent viewInicioSesion = loader.load();
            App.setRoot(viewInicioSesion);
            VistaSimulacionController SimulacionController
                    = loader.getController();
        } catch (IOException ex) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Estamos teniendo dificultades tecnicas");
            a.show();

        }
    }

}
