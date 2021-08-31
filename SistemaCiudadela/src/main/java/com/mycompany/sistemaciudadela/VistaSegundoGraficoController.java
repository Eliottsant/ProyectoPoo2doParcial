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
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Eliot
 */
public class VistaSegundoGraficoController implements Initializable {

    @FXML
    private BarChart<String, Integer> grafico;
    @FXML
    private CategoryAxis x;
    private ObservableList<String> tipos = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<String> t = new ArrayList<>();
        t.add("Residente");
        t.add("Visitante");
        tipos.addAll(t);
        x.setCategories(tipos);
        agregarbarras(Administrador.leerEntradasVR());
    }

    public void agregarbarras(List<Integer> hs) {
        int[] contadorTipos = new int[2];
        contadorTipos[0] += hs.get(0);
        contadorTipos[1] += hs.get(1);
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        for (int i = 0; i < contadorTipos.length; i++) {
            series.getData().add(new XYChart.Data<>(tipos.get(i), contadorTipos[i]));
        }
        grafico.getData().add(series);
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
            System.out.println("No se ha podido cargar la vista");
            System.out.println("VistaRegistro.fxml");

        }
    }

}
