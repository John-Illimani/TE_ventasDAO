/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.emergentes.utiles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ConexionDB {
    
    static String driver ="com.mysql.jdbc.Driver";
    static String url = "jdbc:mysql://localhost:3308/bd_ventas";
    static String usuario = "root";
    static String password = "Promocion2012@@//$$";
    
    protected Connection conn = null;

    public ConexionDB() {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url,usuario,password);
            
            if(conn != null){
                System.out.println("Conexion OK "+conn);
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Error en especificacion de driver "+ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Error al conectar" + ex.getMessage());
        }
    }
    
    
    public Connection conectar (){
    return conn;
    }

    public void desconectar (){
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println("Error al cerrar" +ex.getMessage());
        }
    }
   
    
    
}
