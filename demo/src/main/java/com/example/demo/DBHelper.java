package com.example.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class DBHelper {

    public static void insertProducto(String nombre, String codigo, String marca, int cantidad, double precioCompra, double precioVenta) {
        String insertQuery = "INSERT INTO Productos (Nombre, Codigo, Marca, Cantidad, PrecioCompra, PrecioVenta) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = SQLiteConnection.OpenDB();
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {


            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, codigo);
            preparedStatement.setString(3, marca);
            preparedStatement.setInt(4, cantidad);
            preparedStatement.setDouble(5, precioCompra);
            preparedStatement.setDouble(6, precioVenta);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }




    public static List<Producto> selectAllProductos() {
        String selectQuery = "SELECT * FROM Productos";
        List<Producto> productos = new ArrayList<>();

        try (Connection connection = SQLiteConnection.OpenDB();
             PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int ID = resultSet.getInt("ID");
                String nombre = resultSet.getString("Nombre");
                String codigo = resultSet.getString("Codigo");
                String marca = resultSet.getString("Marca");
                int cantidad = resultSet.getInt("Cantidad");
                double precioCompra = resultSet.getDouble("PrecioCompra");
                double precioVenta = resultSet.getDouble("PrecioVenta");

                Producto producto = new Producto(ID, nombre, codigo, marca, cantidad, precioCompra, precioVenta);
                productos.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productos;
    }


    public static Producto selectProducto(String texto){
        String selectQuery = "SELECT * FROM Productos WHERE Codigo == '" + texto + "'";
        System.out.println(selectQuery);
        try (Connection connection = SQLiteConnection.OpenDB();
             PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            if(!resultSet.next()){
                //Mensaje de error que no esta ese producto
                return null;
            } else {
                int ID = resultSet.getInt("ID");
                String nombre = resultSet.getString("Nombre");
                String codigo = resultSet.getString("Codigo");
                String marca = resultSet.getString("Marca");
                int cantidad = resultSet.getInt("Cantidad");
                double precioCompra = resultSet.getDouble("PrecioCompra");
                double precioVenta = resultSet.getDouble("PrecioVenta");

                Producto producto = new Producto(ID, nombre, codigo, marca, 1, precioCompra, precioVenta);
                //Agregar el nuevo producto a la tabla
                return producto;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void borrarProducto(String texto) {
        String deleteQuery = "DELETE FROM Productos WHERE Codigo = ?";
        System.out.println(deleteQuery);

        try (Connection connection = SQLiteConnection.OpenDB();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.setString(1, texto);

            int rowsDeleted = preparedStatement.executeUpdate();

            if (rowsDeleted == 0) {
                System.out.println("No product with the specified code found for deletion.");
            } else {
                System.out.println("Product deleted successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void actualizarProducto(String codigo, String nombre, String marca, int cantidad, double precioCompra, double precioVenta) {
        String updateQuery = "UPDATE Productos SET Nombre = ?, Marca = ?, Cantidad = ?, PrecioCompra = ?, PrecioVenta = ? WHERE Codigo = ?";
        System.out.println(updateQuery);

        try (Connection connection = SQLiteConnection.OpenDB();
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, marca);
            preparedStatement.setInt(3, cantidad);
            preparedStatement.setDouble(4, precioCompra);
            preparedStatement.setDouble(5, precioVenta);
            preparedStatement.setString(6, codigo);

            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated == 0) {
                System.out.println("No product with the specified code found for updating.");
            } else {
                System.out.println("Product updated successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
