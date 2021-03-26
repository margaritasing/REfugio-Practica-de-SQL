package com.company.myapp;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Refugio  {

    private String dbName = "refugio";
    private String dbUser = "root";
    private String dbPwd = "ange09lina08";

    public void buscarYMostrar(String sql){
        ConexionDB conexionDB = new ConexionDB(dbName,dbUser,dbPwd);
        ResultSet resultados = conexionDB.consultar(sql);
        try{
            mostrarResultadosPOO(resultados);
        }catch(SQLException e){
            System.out.println("No se encontraron resultados.");
        }finally {
            conexionDB.cerrar();
        }
    }

    private void mostrarResultadosPOO(ResultSet resultados) throws SQLException{
        if(resultados != null)
            while(resultados.next())
                System.out.println(
                        new Animal(
                                resultados.getInt("id"),
                                resultados.getString("nombre"),
                                resultados.getString("especie"),
                                resultados.getInt("edad")
                        )
                );
    }
    public void mostrarAnimalesPorEspecie(String especie){
        buscarYMostrar("SELECT * FROM animales WHERE especie LIKE '%"+especie+"%'");
    }
    public void mostrarAnimalesPorID(int id){
        buscarYMostrar("SELECT * FROM animales WHERE id = "+id);
    }
    public void mostrarAnimalesPorNombre(String nombre){
        buscarYMostrar("SELECT * FROM animales WHERE nombre LIKE '%"+nombre+"%'");
    }
    public void listarAnimales() {
        buscarYMostrar("SELECT * FROM animales;");
    }

    public void agregarAnimal(Animal animal) {
        ConexionDB conexionDB = new ConexionDB(dbName,dbUser,dbPwd);
        String nombre = animal.getNombre();
        String especie = animal.getEspecie();
        int edad = animal.getEdad();
        String sql = "INSERT INTO animales (nombre, especie, edad) VALUES "+
                "('"+nombre+"','"+especie+"',"+edad+");";
        try {
            conexionDB.insertar(sql);
        }catch(SQLException e){
            System.out.println("No se pudo agregar el registro.");
        }finally {
            conexionDB.cerrar();
        }
    }

    public void actualizarEdad(int idACambiar, int edad) {
        ConexionDB conexionDB = new ConexionDB(dbName,dbUser,dbPwd);
        String sql = "UPDATE animales SET edad="+edad+" WHERE (id="+idACambiar+");";
        try {
            conexionDB.insertar(sql);
        }catch(SQLException e){
            System.out.println("No se pudo modificar el registro.");
        }finally {
            conexionDB.cerrar();
        }
    }


}




