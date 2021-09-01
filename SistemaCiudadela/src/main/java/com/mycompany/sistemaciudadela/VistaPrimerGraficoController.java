/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sistemaciudadela;

import com.mycompany.sistemaciudadela.modelo.Administrador;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Eliot
 */
public class VistaPrimerGraficoController implements Initializable {

    @FXML
    private BarChart<String, Integer> tabla;
    @FXML
    private CategoryAxis x;
    private ObservableList<String> horas = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        List<String> h = new ArrayList<>();
        for (int i = 0; i <= 23; i++) {
            String hora = i + ":00";
            h.add(hora);
        }
        horas.addAll(h);
        x.setCategories(horas);
        agregarbarras(Administrador.leerEntradas());
    }

    public void agregarbarras(List<Integer> hs) {
        int[] contadorHoras = new int[24];
        for (int i : hs) {
            contadorHoras[i]++;
        }
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        for (int i = 0; i < contadorHoras.length; i++) {
            series.getData().add(new XYChart.Data<>(horas.get(i), contadorHoras[i]));

        }
        tabla.getData().add(series);
    }

    @FXML
    private void volver(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("VistaReportes.fxml"));
            Parent viewInicioSesion = loader.load();
            App.setRoot(viewInicioSesion);
            VistaReportesController ReportesController
                    = loader.getController();
        } catch (IOException ex) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Estamos teniendo dificultades tecnicas");
            a.show();
        }
    }
}
