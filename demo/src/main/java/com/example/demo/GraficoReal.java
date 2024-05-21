package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tooltip;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class GraficoReal implements Initializable {


    @FXML
    private PieChart gpie;

    @FXML
    private BarChart<String,Number> gbarAr;

    @FXML
    private BarChart<String,Number> gbarAb;

    @FXML
    private LineChart gline;

    @FXML
    private Button elboton;

    @FXML
    private DatePicker fechaInicio;

    @FXML
    private DatePicker fechaFin;


    List<Producto> productos = new ArrayList<>();
    ObservableList<Producto> ProductoObservableList = FXCollections.observableArrayList();



    @Override
    public void initialize(URL url, ResourceBundle resource){

        ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
        data.add(new PieChart.Data("OpenCV", 20));
        data.add(new PieChart.Data("JavaFX", 40));
        data.add(new PieChart.Data("Python", 10));
        data.add(new PieChart.Data("Spring", 15));
        data.add(new PieChart.Data("Qt", 10));
        data.add(new PieChart.Data("SQL", 17));

        PieChart pie = gpie;
        pie.setData(data);
        pie.setTitle("PieChart Tutorial 2017");
        //pie.setLegendSide(Side.LEFT);
        pie.setTitleSide(Side.BOTTOM);
        pie.setLabelLineLength(60);
        pie.setLabelsVisible(true);
        //pie.getData().forEach(this::installTooltip);

        BarChart<String,Number> gba = gbarAr;
        XYChart.Series<String,Number> data2 = new XYChart.Series<>();
        data2.getData().add(new XYChart.Data<>("producto 1", 10));
        data2.getData().add(new XYChart.Data<>("producto 2", 20));
        data2.getData().add(new XYChart.Data<>("producto 3", 40));
        data2.getData().add(new XYChart.Data<>("producto 4", 50));
        data2.getData().add(new XYChart.Data<>("producto 5", 60));
        data2.getData().add(new XYChart.Data<>("producto 6", 70));
        data2.getData().add(new XYChart.Data<>("producto 7", 80));
        data2.getData().add(new XYChart.Data<>("producto 8", 90));
        data2.getData().add(new XYChart.Data<>("producto 9", 110));
        data2.getData().add(new XYChart.Data<>("producto 10", 120));


        gba.getData().add(data2);

        BarChart<String,Number> gba2 = gbarAb;
        XYChart.Series<String,Number> data3 = new XYChart.Series<>();
        data2.getData().add(new XYChart.Data<>("producto 1", 10));
        data2.getData().add(new XYChart.Data<>("producto 2", 20));
        data2.getData().add(new XYChart.Data<>("producto 3", 30));

        gba2.getData().add(data3);


    }


    public void fucnBoton(ActionEvent event){
        DatePicker fechaI = fechaInicio;
        DatePicker fechaF = fechaFin;

        if (fechaF.getValue() == null){
            fechaF.setValue(LocalDate.now());
        }
        if (fechaI.getValue() == null){
            fechaI.setValue(fechaF.getValue().minusMonths(1));
        }

        if (fechaI.getValue().isAfter(fechaF.getValue())){
            Errores.mensajeError("Fechas erroneas");
            return;
        }

        //Codigo de query



    }

    public void installTooltip(PieChart.Data d) {

        String msg = String.format("%s : %s", d.getName(), d.getPieValue());

        Tooltip tt = new Tooltip(msg);
        tt.setStyle("-fx-background-color: gray; -fx-text-fill: whitesmoke;");

        Tooltip.install(d.getNode(), tt);
    }


    }






