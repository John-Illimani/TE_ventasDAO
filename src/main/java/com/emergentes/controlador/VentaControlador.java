/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.emergentes.controlador;

import com.emergentes.dao.ClienteDAO;
import com.emergentes.dao.ClienteDAOimpl;
import com.emergentes.dao.ProductoDAO;
import com.emergentes.dao.ProductoDAOimpl;
import com.emergentes.dao.VentaDAO;
import com.emergentes.dao.VentaDAOimpl;
import com.emergentes.modelo.Cliente;
import com.emergentes.modelo.Producto;
import com.emergentes.modelo.Venta;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author illim
 */
@WebServlet(name = "VentaControlador", urlPatterns = {"/VentaControlador"})
public class VentaControlador extends HttpServlet {

   
  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        try {
             
            int id;
            VentaDAO dao = new VentaDAOimpl();  //se obtiene el objeto dao para hacer las implementaciones
            ProductoDAO daoProducto = new ProductoDAOimpl();
            ClienteDAO daoCliente = new ClienteDAOimpl();
            
            List<Producto> lista_productos = null;
            List<Cliente> lista_clientes = null;
             Venta v = new Venta();
            
            
            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";

            switch (action) {

                case "add":
                    
                    lista_productos = daoProducto.getAll();
                    lista_clientes = daoCliente.getAll();
                    
                    
                    request.setAttribute("lista_productos", lista_productos);
                    request.setAttribute("lista_clientes", lista_clientes);
                    
                    
                    request.setAttribute("venta", v);
                    request.getRequestDispatcher("frmventa.jsp").forward(request, response);
                    break;
                case "edit":
                    
                    lista_productos = daoProducto.getAll();
                    lista_clientes = daoCliente.getAll();
                    
                    
                    request.setAttribute("lista_productos", lista_productos);
                    request.setAttribute("lista_clientes", lista_clientes);
                    
                    id = Integer.parseInt(request.getParameter("id"));
                    v = dao.getById(id);
                    request.setAttribute("venta", v);
                    request.getRequestDispatcher("frmventa.jsp").forward(request, response);
                    
                    break;
                case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.delete(id);
                    response.sendRedirect("VentaControlador");
                    break;
                case "view":
                    //obtener la lista de registros 
                    
                    List<Venta> lista = dao.getAll();
                    
                    request.setAttribute("venta",lista);  //le llamamos venta y le mandamos la lista
                    request.getRequestDispatcher("ventas.jsp").forward(request, response);
                    
                    break;
            }

        } catch (Exception e) {
        }
       
        
        
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
         int id= Integer.parseInt((request.getParameter("id"))); //el post ya es para registrar
         int cliente_id= Integer.parseInt((request.getParameter("cliente_id"))); //el post ya es para registrar
         int producto_id= Integer.parseInt((request.getParameter("producto_id"))); //el post ya es para registrar
        
        String fecha = request.getParameter("fecha");
        
        
        Venta v = new Venta();
        
        v.setId(id);
        v.setCliente_id(cliente_id);
        v.setProducto_id(producto_id);
        v.setFecha(convierteFecha(fecha));
        
       
       
        
        VentaDAO dao = new VentaDAOimpl();
        
        if(id == 0){
            try {
                //nuevo registro
                
                
                dao.insert(v);
            } catch (Exception ex) {
                System.out.println("Error al insertar "+ ex.getMessage());
            }
        }else{
            try {
                //edicion de registro

                dao.update(v);
            } catch (Exception ex) {
                System.out.println("Error al editar "+ex.getMessage());
            }
        
        
        }
        response.sendRedirect("VentaControlador"); //se transfiere el contol al servlet de Cliente controlador
       
    }
    
    
    
    
    
    
    
     public Date convierteFecha(String fecha){
            Date fechaBD = null;
            
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            
            java.util.Date fechaTMP;
        try {
            fechaTMP = formato.parse(fecha);
            fechaBD = new Date(fechaTMP.getTime());
        } catch (ParseException ex) {
            Logger.getLogger(VentaControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
            return fechaBD;
        }

    
   
}
