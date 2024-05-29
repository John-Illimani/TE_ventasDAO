/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.emergentes.controlador;

import com.emergentes.dao.ClienteDAO;
import com.emergentes.dao.ClienteDAOimpl;
import com.emergentes.dao.ProductoDAO;
import com.emergentes.dao.ProductoDAOimpl;
import com.emergentes.modelo.Cliente;
import com.emergentes.modelo.Producto;
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
@WebServlet(name = "ProductoControlador", urlPatterns = {"/ProductoControlador"})
public class ProductoControlador extends HttpServlet {

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        try {
            Producto cli = new Producto();
            int id;
            ProductoDAO dao = new ProductoDAOimpl();  //se obtiene el objeto dao para hacer las implementaciones

            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";

            switch (action) {

                case "add":
                    request.setAttribute("producto", cli);
                    request.getRequestDispatcher("frmproducto.jsp").forward(request, response);
                    break;
                case "edit":
                    id = Integer.parseInt(request.getParameter("id"));
                    cli = dao.getById(id);
                    request.setAttribute("producto", cli);
                    request.getRequestDispatcher("frmproducto.jsp").forward(request, response);
                    
                    break;
                case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.delete(id);
                    response.sendRedirect("ProductoControlador");
                    break;
                case "view":
                    //obtener la lista de registros 
                    
                    List<Producto> lista = dao.getAll();
                    
                    request.setAttribute("productos",lista);  //le llamamos lista y le mandamos la lista
                    request.getRequestDispatcher("productos.jsp").forward(request, response);
                    
                    break;
            }

        } catch (Exception e) {
        }
        
        
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        
        int id= Integer.parseInt((request.getParameter("id"))); //el post ya es para registrar
        
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        float precio = Float.parseFloat(request.getParameter("precio"));  
        
        Producto pro = new Producto();
        
        pro.setId(id);
        pro.setNombre(nombre);
        pro.setDescripcion(descripcion);
        pro.setPrecio(precio);
        
        
        ProductoDAO dao = new ProductoDAOimpl();
        
        if(id == 0){
            try {
                //nuevo registro
                
                
                dao.insert(pro);
            } catch (Exception ex) {
                System.out.println("Error al insertar "+ ex.getMessage());
            }
        }else{
            try {
                //edicion de registro

                dao.update(pro);
            } catch (Exception ex) {
                System.out.println("Error al editar "+ex.getMessage());
            }
        
        
        }
        response.sendRedirect("ProductoControlador"); //se transfiere el contol al servlet de Cliente controlador

    }

    }

   


