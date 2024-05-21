package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    AnchorPane layout;

    @FXML private void cargarsig() throws IOException {

        AnchorPane second = FXMLLoader.load(getClass().getResource("ventas4.fxml"));
        layout.getChildren().setAll(second);
    }


    @FXML private void carsig2() throws IOException {

        AnchorPane secon = FXMLLoader.load(getClass().getResource("inventario2.fxml"));
        layout.getChildren().setAll(secon);
    }

    @FXML private void sig() throws IOException {

        AnchorPane seco = FXMLLoader.load(getClass().getResource("estadisticas2.fxml"));
        layout.getChildren().setAll(seco);
    }

}
