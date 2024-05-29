/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.emergentes.controlador;

import com.emergentes.utiles.ConexionDB;
import com.emergentes.utiles.Validate;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author illim
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    
  
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        action = (request.getParameter("action") == null)? "view" : request.getParameter("action");  //pregunta
        
        if (action.equals("logout")){
        HttpSession ses = request.getSession();
        ses.invalidate();
        }
        
        
        response.sendRedirect("login.jsp");
        
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            String correo =  request.getParameter("correo");
            String password =  request.getParameter("password");
            
            
            System.out.println("correo: "+correo+ "password: "+password);
            
            String sql = "select * from usuarios where correo = ? and password = md5(?)";
            
            ResultSet rs ;
            ConexionDB canal = new ConexionDB();
            Connection cn = canal.conectar();
            
            PreparedStatement ps = cn.prepareStatement(sql);
            
            ps.setString(1, correo);
            ps.setString(2, password);
            
            rs=ps.executeQuery();
            
            
            if (rs.next()){
                //crear las variables de session
                
                HttpSession ses = request.getSession();
                
                //las marcas
                
                ses.setAttribute("logueado", "OK");
                
                ses.setAttribute("usuario", rs.getString("nombres")+" " +rs.getString("apellidos"));
                response.sendRedirect("ClienteControlador");
            }else{
             response.sendRedirect("login.jsp");
            }
            
            
            
        } catch (SQLException ex) {
            System.out.println("Error al conectar a la base de datos "+ ex.getMessage());
        }
       
    }

   
}
