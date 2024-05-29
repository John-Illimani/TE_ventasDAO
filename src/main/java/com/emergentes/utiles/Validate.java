/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.emergentes.utiles;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author illim
 */
public class Validate extends ConexionDB  {
    
    
    Connection con = this.conectar();
    
    PreparedStatement pr;
    
    public boolean checkUSer(String email,String password){
        
        boolean resultado = false;
        try {
            
            String sql="select * from usuarios where email=? and password=sha1(?)";
            pr = con.prepareStatement(sql);
            pr.setString(1,email);
            pr.setString(2,password);
            ResultSet rs = pr.executeQuery();
            resultado = rs.next();
        } catch (SQLException ex) {
            System.out.println("Error al autenticar");
        }
        
        return resultado;
    
    }
    
    
    
    
    
}
