/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.emergentes.controlador;

import com.emergentes.dao.ClienteDAO;
import com.emergentes.dao.ClienteDAOimpl;
import com.emergentes.dao.UsuarioDAO;
import com.emergentes.dao.UsuarioDAOimpl;
import com.emergentes.modelo.Cliente;
import com.emergentes.modelo.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author illim
 */
@WebServlet(name = "UsuarioCotrolador", urlPatterns = {"/UsuarioCotrolador"})
public class UsuarioCotrolador extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Usuario u = new Usuario();
            int id;
            UsuarioDAO dao = new UsuarioDAOimpl();  //se obtiene el objeto dao para hacer las implementaciones

            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";

            switch (action) {

                case "add":
                    request.setAttribute("usuario", u);
                    request.getRequestDispatcher("frmusuario.jsp").forward(request, response);
                    break;
                case "edit":
                    id = Integer.parseInt(request.getParameter("id"));
                    u = dao.getById(id);
                    request.setAttribute("usuario", u);
                    request.getRequestDispatcher("frmusuario.jsp").forward(request, response);
                    
                    break;
                case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.delete(id);
                    response.sendRedirect("UsuarioCotrolador");
                    break;
                case "view":
                    //obtener la lista de registros 
                    
                    List<Usuario> lista = dao.getAll();
                    
                    request.setAttribute("usuarios",lista);  //le llamamos lista y le mandamos la lista
                    request.getRequestDispatcher("usuario.jsp").forward(request, response);
                    
                    break;
            }

        } catch (Exception e) {
        }
        
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        int id= Integer.parseInt((request.getParameter("id"))); //el post ya es para registrar
        
        String nombres = request.getParameter("nombres");
        String apellidos = request.getParameter("apellidos");
        String correo = request.getParameter("correo");
        String password = request.getParameter("password");
        
        Usuario u = new Usuario();
        
        u.setId(id);
        u.setNombres(nombres);
        u.setApellidos(apellidos);
        u.setCorreo(correo);
        u.setPassword(password);
        
        UsuarioDAO dao = new UsuarioDAOimpl();
        
        if(id == 0){
            try {
                //nuevo registro
                
                
                dao.insert(u);
            } catch (Exception ex) {
                System.out.println("Error al insertar "+ ex.getMessage());
            }
        }else{
            try {
                //edicion de registro

                dao.update(u);
            } catch (Exception ex) {
                System.out.println("Error al editar "+ex.getMessage());
            }
        
        
        }
        response.sendRedirect("UsuarioCotrolador"); //se transfiere el contol al servlet de Cliente controlador

        
    }

    
}
