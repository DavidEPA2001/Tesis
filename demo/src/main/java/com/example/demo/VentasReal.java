package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class VentasReal implements Initializable {


    List<Producto> productos = new ArrayList<>();


    @FXML
    private TextField textfieldcodigo;

    @FXML
    private Label textfieldtotal;

    @FXML
    private ComboBox comboboxnombre;

    @FXML
    private Spinner spinnercantidad;

    @FXML
    private TableView <Producto> tableviewventas;

    @FXML
    private TableColumn<Producto, String> tablecolumnid;

    @FXML
    private TableColumn<Producto, String> tablecolumnnombre;

    @FXML
    private TableColumn<Producto, String> tablecolumnmarca;

    @FXML
    private TableColumn<Producto, String> tablecolumncantidad;

    @FXML
    private TableColumn<Producto, String> tablecolumnprecio;





    @Override
    public void initialize(URL url, ResourceBundle resource){

        textfieldcodigo.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode() == KeyCode.ENTER){
                leerCodigo(textfieldcodigo.getText());
            }
        });

        mostrarTableViewVentas();


    }

    private void llenarCampos(Producto selectedProduct) {
        if (selectedProduct != null) {
            String id = selectedProduct.getCodigo();
            String nombre = selectedProduct.getNombre();
            int cantidad = selectedProduct.getCantidad();
            SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, cantidad);
            spinnercantidad.setValueFactory(valueFactory);


            textfieldcodigo.setText(id);
            comboboxnombre.setValue(nombre);
            spinnercantidad.getValueFactory().setValue(cantidad);

            System.out.println("ID: " + id);
            System.out.println("Nombre: " + nombre);
            System.out.println("Cantidad: " + cantidad);
        }
    }

    private void mostrarTableViewVentas() {


        ObservableList<Producto> ProductoObservableList = FXCollections.observableArrayList(productos);

        tablecolumnid.setCellValueFactory(new PropertyValueFactory<>("ID"));
        tablecolumnnombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        tablecolumnmarca.setCellValueFactory(new PropertyValueFactory<>("Marca"));
        tablecolumncantidad.setCellValueFactory(new PropertyValueFactory<>("Cantidad"));
        tablecolumnprecio.setCellValueFactory(new PropertyValueFactory<>("PrecioVenta"));


        tableviewventas.setItems(ProductoObservableList);

        tableviewventas.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            llenarCampos(newSelection);
        });
    }

    public void leerCodigo(String texto){
        if(texto.trim().isEmpty()){
            //Cadena vacia, mandar mensaje de error
            System.out.println("Error");
        }
        else{

            int encontrado = -1;
            if(!productos.isEmpty()) {
                double total = 0;
                for (int i = 0; i < productos.size(); i++) {
                    total = productos.get(i).getPrecioVenta() * (productos.get(i).getCantidad() +1 );
                    if (productos.get(i).getCodigo().equals(texto)) {
                        encontrado = i;

                    }
                }
                textfieldtotal.setText("Total: " + String.valueOf(total));
            }
            if(encontrado!=-1){
                //Hacer un ++ a la cantidad del producto encontrado
                int cantidad = productos.get(encontrado).getCantidad() + 1;
                productos.get(encontrado).setCantidad(cantidad);
                System.out.println(productos.get(encontrado).getCantidad());
                //Llamar funcion de mostrar datos en el tableview
                mostrarTableViewVentas();
                tableviewventas.refresh();

            } else{
                //En caso que no este en la tabla, buscarlo en la base de datos
                Producto nuevoProducto = DBHelper.selectProducto(texto);
                System.out.println(texto);
                if(nuevoProducto ==null){
                    //Mensaje de error, no lo encontre
                }else{
                    //Agregar el nuevo producto al arreglo de productos de venta
                    productos.add(nuevoProducto);
                    //Llamar funcion de mostrar datos en el tableview
                    mostrarTableViewVentas();
                }
            }

        }
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 1);
        spinnercantidad.setValueFactory(valueFactory);
        comboboxnombre.setValue("SELECCIONA");
        spinnercantidad.getValueFactory().setValue(1);
        textfieldcodigo.setText("");
    }
}
