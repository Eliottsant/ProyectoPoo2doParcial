/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sistemaciudadela;

import com.mycompany.sistemaciudadela.modelo.Usuario;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Eliot
 */
public class VistaInicioSesionController implements Initializable {

    @FXML
    private TextField nomUsu;
    @FXML
    private TextField contra;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void inicioSesion(MouseEvent event) {
        List<Usuario> usus = Usuario.cargarUsuarios();
        for (Usuario u : usus) {
            if (u.getUsername().equals("admin") && u.getContraseña().equals(contra.getText())) {
                try {
                    FXMLLoader loader = new FXMLLoader(App.class.getResource("VistaAdmin.fxml"));
                    Parent viewAdmin = loader.load();
                    App.setRoot(viewAdmin);
                    VistaAdminController adminController
                            = loader.getController();
                } catch (IOException ex) {
                    System.out.println("No se ha podido cargar la vista");
                    System.out.println("VistaAdmin.fxml");

                }
            } else if (u.getUsername().equals(nomUsu.getText()) && u.getContraseña().equals(contra.getText())) {
                try {
                    FXMLLoader loader = new FXMLLoader(App.class.getResource("VistaResidente.fxml"));
                    Parent viewAdmin = loader.load();
                    App.setRoot(viewAdmin);
                    VistaResidenteController residenteController
                            = loader.getController();
                } catch (IOException ex) {
                    System.out.println("No se ha podido cargar la vista");
                    System.out.println("VistaResidente.fxml");

                }
            } else {
                
            }
        }
    }

}
