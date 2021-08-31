/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sistemaciudadela;

import com.mycompany.sistemaciudadela.modelo.Residente;
import java.io.IOException;
import java.net.URL;
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
public class VistaCambiarPinController implements Initializable {

    @FXML
    private TextField anterior;
    @FXML
    private TextField nuevo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void regresar(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("VistaInformacion.fxml"));
            Parent viewSalir = loader.load();
            App.setRoot(viewSalir);
            VistaInformacionController principalController
                    = loader.getController();
        } catch (IOException ex) {
            Alert a = new Alert(AlertType.INFORMATION);
            a.setContentText("Estamos teniendo dificultades tecnicas");
            a.show();

        }
    }

    @FXML
    private void cambiar(MouseEvent event) {
        if (anterior.getText().equals(Residente.verInfo().getPin())) {
            Residente.cambiarPin(Residente.verInfo().getNombre(), nuevo.getText());
            Alert a = new Alert(AlertType.CONFIRMATION);
            a.setContentText("El pin se ha cambiado correctamente");
            a.show();
        } else {
            Alert a = new Alert(AlertType.ERROR);
            a.setContentText("El pin anterior no es el mismo");
            a.show();
        }
    }

}
