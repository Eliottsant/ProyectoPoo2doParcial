/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sistemaciudadela;

import com.mycompany.sistemaciudadela.modelo.Administrador;
import com.mycompany.sistemaciudadela.modelo.Residente;
import com.mycompany.sistemaciudadela.modelo.Visita;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Eliot
 */
public class VistaVisitasController implements Initializable {

    @FXML
    private Menu info;
    @FXML
    private Menu vehiculos;
    @FXML
    private Menu visitas;
    @FXML
    private Menu salir;
    @FXML
    private TextField nombreVisita;
    @FXML
    private TextField cedulaVisita;
    @FXML
    private TextField correoVisita;
    @FXML
    private TextField d;
    @FXML
    private TextField m;
    @FXML
    private TextField a;
    @FXML
    private TableView<Visita> tablaInfo;
    @FXML
    private TextField borrarNom;
    @FXML
    private TextField hora;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Visita> visitas = Residente.verVisitasTodas();
        TableColumn<Visita, String> nombre = new TableColumn<>("Nombre Visita");
        TableColumn<Visita, String> cedula = new TableColumn<>("Cedula Visita");
        TableColumn<Visita, String> email = new TableColumn<>("Codigo acceso");
        TableColumn<Visita, String> fecha = new TableColumn<>("Fecha");
        TableColumn<Visita, String> estado = new TableColumn<>("Estado");
        tablaInfo.getColumns().addAll(nombre, cedula, email, fecha, estado);
        nombre.setCellValueFactory(new Callback<CellDataFeatures<Visita, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(CellDataFeatures<Visita, String> v) {
                return v.getValue().nombre;
            }
        });
        cedula.setCellValueFactory(new Callback<CellDataFeatures<Visita, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(CellDataFeatures<Visita, String> v) {
                return v.getValue().cedula;
            }
        });
        email.setCellValueFactory(new Callback<CellDataFeatures<Visita, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(CellDataFeatures<Visita, String> v) {
                return v.getValue().pin;
            }
        });
        estado.setCellValueFactory(new Callback<CellDataFeatures<Visita, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(CellDataFeatures<Visita, String> v) {
                return v.getValue().estado;
            }
        });
        fecha.setCellValueFactory(new Callback<CellDataFeatures<Visita, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(CellDataFeatures<Visita, String> v) {
                return v.getValue().sfecha();
            }
        });
        for (Visita v : visitas) {
            if (v.getNombreResidente().equals(Residente.verInfo().getNombre())) {
                tablaInfo.getItems().add(v);
            }
        }
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
                    System.out.println("No se ha podido cargar la vista");
                    System.out.println("VistaVehiculos.fxml");

                }
            }

        });
        vehiculos.getItems().add(veh);

    }

    @FXML
    private void registrarVisita(MouseEvent event) {
        String nom = nombreVisita.getText();
        String ced = cedulaVisita.getText();
        String corr = correoVisita.getText();
        String pin = Visita.generarCodigo();
        String estado = "Activo";
        String dia = d.getText();
        String mes = m.getText();
        String anio = a.getText();
        LocalDateTime fecha = LocalDateTime.parse(anio + "-" + mes + "-" + dia + "T" + hora.getText());
        Visita v = new Visita(nom, ced, corr, estado, pin, fecha, Residente.verInfo().getNombre());
        Residente.registroGeneralVisitas(v, Residente.verInfo().getNombre());
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    Visita.enviarcorreo(v.getCorreo(), v.getPin());
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
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
    }

    @FXML
    private void borrar(MouseEvent event) {
        String n = "";
        for (Visita v : Residente.verVisitasTodas()) {
            if (v.getNombre().equals(borrarNom.getText()) && v.getEstado().equals("En proceso")) {
                n = v.getNombre();

            }
        }
        if(n.equals(borrarNom.getText())){
            Visita.borrarVisitaGeneral(n);
        }else{
            Alert a =new Alert(AlertType.ERROR);
            a.setContentText("No se pudo eliminar la visita");
            a.show();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1700);
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
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
    }

}
