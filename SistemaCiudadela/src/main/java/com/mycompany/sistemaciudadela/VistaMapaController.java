/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sistemaciudadela;

import com.mycompany.sistemaciudadela.modelo.Administrador;
import com.mycompany.sistemaciudadela.modelo.Residencia;
import com.mycompany.sistemaciudadela.modelo.Residente;
import java.io.IOException;
import java.io.InputStream;
import static java.lang.ProcessBuilder.Redirect.to;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Eliot
 */
public class VistaMapaController implements Initializable {

    @FXML
    private Menu reportes;
    @FXML
    private Menu salir;
    @FXML
    private Pane panelMapa;
    @FXML
    private TextField nombre;
    @FXML
    private TextField cedula;
    @FXML
    private TextField correo;
    @FXML
    private Button boton;
    @FXML
    private Label l1;
    @FXML
    private Label l2;
    @FXML
    private Label l3;
    @FXML
    private Label l4;
    @FXML
    private ComboBox<String> genero;
    private Residencia casaSeleccionada;
    private Residente residente;
    @FXML
    private Label selec;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<String> generos = new ArrayList<>();
        generos.add("Masculino");
        generos.add("Femenino");
        genero.getItems().addAll(generos);
        List<Residencia> residencias = Residencia.cargarResidencias();
        for (Residencia r : residencias) {
            VBox casa = new VBox();
            ImageView imgview = null;
            try {
                //agrego la imagen del aagente
                InputStream input = App.class.getResource("casita.PNG").openStream();
                Image img = new Image(input, 60, 60, true, true);
                imgview = new ImageView(img);
            } catch (NullPointerException | IOException ex) {
                Alert a = new Alert(AlertType.INFORMATION);
                a.setContentText("Estamos teniendo dificultades tecnicas");
                a.show();

                imgview = new ImageView();
            }
            casa.getChildren().add(imgview);
            casa.setLayoutX(r.getX());
            casa.setLayoutY(r.getY());
            panelMapa.getChildren().add(casa);
            VBox infobox = new VBox();
            Label lc = new Label();
            Label lm = new Label();
            Label lr = new Label();
            lc.setText("Residencia: " + r.getVilla());
            lm.setText("Manzana: " + r.getManzana());
            lr.setText("Residente:" + r.getResidente());
            infobox.getChildren().add(lc);
            infobox.getChildren().add(lm);
            infobox.getChildren().add(lr);
            casa.getChildren().add(infobox);
            Administrador.mover(casa);
            infobox.setVisible(false);
            EventHandler eventHandler = (event) -> {
                l1.setDisable(false);
                l2.setDisable(false);
                l3.setDisable(false);
                l4.setDisable(false);
                nombre.setDisable(false);
                cedula.setDisable(false);
                correo.setDisable(false);
                genero.setDisable(false);
                boton.setDisable(false);
                selec.setText("Casa Seleccionada: " + r.getVilla());
                casaSeleccionada = r;
            };
            casa.setOnMouseClicked(eventHandler);
            EventHandler eventHandler1 = (event) -> {
                infobox.setVisible(true);
            };
            //casa.setOnMouseMoved(eventHandler1);
            casa.hoverProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
                if (newValue) {
                    infobox.setVisible(true);
                } else {
                    infobox.setVisible(false);
                }
            });
        }
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

    @FXML
    private void registrar(MouseEvent event) {
        String n = nombre.getText();
        String co = correo.getText();
        String ce = cedula.getText();
        String g = genero.getValue();
        Residente re = new Residente(n, ce, co, g, Administrador.generarPin(), casaSeleccionada.getVilla(), casaSeleccionada.getManzana(), Administrador.generarUsername(n), Administrador.generarContraseña(Administrador.generarUsername(n)));
        if (casaSeleccionada.getResidente().equals("vacio")) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Administrador.agregarResidente(casaSeleccionada, n, re);
                                Thread.sleep(1700);
                                Administrador.enviarCorreo(co, re.getUsername(), re.getContraseña(), re.getPin());
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
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                        }
                    });
                }
            }
            ).start();
        } else {
            Alert a = new Alert(AlertType.ERROR);
            a.setContentText("La casa ya tiene un residente");
            a.show();

            try {
                FXMLLoader loader = new FXMLLoader(App.class
                        .getResource("VistaMapa.fxml"));
                Parent viewMapa = loader.load();

                App.setRoot(viewMapa);
                VistaMapaController mapaController
                        = loader.getController();
            } catch (IOException ex) {
                Alert r = new Alert(AlertType.INFORMATION);
                r.setContentText("Estamos teniendo dificultades tecnicas");
                r.show();

            }
        }
    }

}

class Delta {

    double x, y;
}
