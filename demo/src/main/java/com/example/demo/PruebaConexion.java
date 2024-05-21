package com.example.demo;

public class PruebaConexion {

    public static void main(String[] args){

        if(SQLiteConnection.OpenDB()!=null){
            System.out.println("conectado");
        }
        else {
            System.out.println("error al conectarse");
        }
    }
}

