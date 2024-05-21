package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class InventarioReal implements Initializable {

    @FXML
    private TableView <Producto> tableviewinventario;

    @FXML
    private TableColumn<Producto, String> tablecolumnid;

    @FXML
    private TableColumn<Producto, String> tablecolumnnombre;

    @FXML
    private TableColumn<Producto, String> tablecolumnmarca;

    @FXML
    private TableColumn<Producto, String> tablecolumncantidad;

    @FXML
    private TableColumn<Producto, String> tablecolumnpreciocompra;

    @FXML
    private TableColumn<Producto, String> tablecolumnprecioventa;
    @FXML
    private TextField textidproducto;
    @FXML
    private TextField textnombreproducto;
    @FXML
    private TextField textmarcaproducto;

    @FXML
    private TextField busquedatextfield;

    @FXML
    private TextField textcantidadproducto;

    @FXML
    private TextField textprecioproducto;
    @FXML
    private TextField textprecioproductoventa;

    @FXML
    private Button botonagregar;

    @FXML
    private Button botonactualizar;

    @FXML
    private Button botoneliminar;


    List<Producto> productos = new ArrayList<>();
    ObservableList<Producto> ProductoObservableList = FXCollections.observableArrayList();



    @Override
    public void initialize(URL url, ResourceBundle resource){
        commonInitialization();



    }

    public void handleAgregarButtonAction(ActionEvent event)  {
        // Get the values from the TextFields
        String idProducto = textidproducto.getText();
        String nombreProducto = textnombreproducto.getText();
        String marcaProducto = textmarcaproducto.getText();
        int cantidadProducto = Integer.parseInt(textcantidadproducto.getText());
        double precioProducto = Double.parseDouble(textprecioproducto.getText());
        double precioProductoVenta = Double.parseDouble(textprecioproductoventa.getText());

        // Call the insertData method in the DBHelper class
        DBHelper.insertProducto( nombreProducto, idProducto, marcaProducto, cantidadProducto, precioProducto, precioProductoVenta);

        textidproducto.clear();
        textnombreproducto.clear();
        textmarcaproducto.clear();
        textcantidadproducto.clear();
        textprecioproducto.clear();
        textprecioproductoventa.clear();

        commonInitialization();
    }

    public void handleBorrarButtonAction(ActionEvent event)  {
        // Get the values from the TextFields
        String idProducto = textidproducto.getText();



        // Call the insertData method in the DBHelper class
        DBHelper.borrarProducto(idProducto);

        textidproducto.clear();
        textnombreproducto.clear();
        textmarcaproducto.clear();
        textcantidadproducto.clear();
        textprecioproducto.clear();
        textprecioproductoventa.clear();

        commonInitialization();
    }

    public void handleActualizarButtonAction(ActionEvent event)  {
        // Get the values from the TextFields
        String idProducto = textidproducto.getText();
        String nombreProducto = textnombreproducto.getText();
        String marcaProducto = textmarcaproducto.getText();
        int cantidadProducto = Integer.parseInt(textcantidadproducto.getText());
        double precioProducto = Double.parseDouble(textprecioproducto.getText());
        double precioProductoVenta = Double.parseDouble(textprecioproductoventa.getText());




        // Call the insertData method in the DBHelper class
        DBHelper.actualizarProducto(idProducto,nombreProducto,marcaProducto,cantidadProducto,precioProducto,precioProductoVenta);

        textidproducto.clear();
        textnombreproducto.clear();
        textmarcaproducto.clear();
        textcantidadproducto.clear();
        textprecioproducto.clear();
        textprecioproductoventa.clear();

        commonInitialization();
    }

    private void commonInitialization() {

        List<Producto> productos = DBHelper.selectAllProductos();
        ObservableList<Producto> ProductoObservableList = FXCollections.observableArrayList(productos);

        tablecolumnid.setCellValueFactory(new PropertyValueFactory<>("ID"));
        tablecolumnnombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        tablecolumnmarca.setCellValueFactory(new PropertyValueFactory<>("Marca"));
        tablecolumncantidad.setCellValueFactory(new PropertyValueFactory<>("Cantidad"));
        tablecolumnpreciocompra.setCellValueFactory(new PropertyValueFactory<>("PrecioCompra"));
        tablecolumnprecioventa.setCellValueFactory(new PropertyValueFactory<>("PrecioVenta"));

        tableviewinventario.setItems(ProductoObservableList);

        tableviewinventario.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            llenarCampos(newSelection);
        });

        FilteredList<Producto> filteredData = new FilteredList<>(ProductoObservableList, b -> true);
        busquedatextfield.textProperty().addListener((observable, oldValue, newValue) -> {

            filteredData.setPredicate(Producto -> {

                if(newValue.isEmpty() || newValue.isBlank() || newValue == null ){
                    return true;
                }

                String searchKeyword = newValue.toLowerCase();

                if(Producto.getNombre().toLowerCase().indexOf(searchKeyword) > -1){
                    return true;
                }
                else if(Producto.getMarca().toLowerCase().indexOf(searchKeyword) > -1){
                    return true;
                }
                else
                    return false;
            });
        });

        SortedList<Producto> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(tableviewinventario.comparatorProperty());

        tableviewinventario.setItems(sortedData);

    }

    private void llenarCampos(Producto selectedProduct) {
        if (selectedProduct != null) {

            String id = selectedProduct.getCodigo();
            String nombre = selectedProduct.getNombre();
            String marca = selectedProduct.getMarca();
            int cantidad = selectedProduct.getCantidad();
            double preciocompra = selectedProduct.getPrecioCompra();
            double precioventa = selectedProduct.getPrecioVenta();

            textidproducto.setText(id);
            textnombreproducto.setText(nombre);
            textmarcaproducto.setText(marca);
            textcantidadproducto.setText(String.valueOf(cantidad));
            textprecioproducto.setText(String.valueOf(preciocompra));
            textprecioproductoventa.setText(String.valueOf(precioventa));



        }
    }




}
