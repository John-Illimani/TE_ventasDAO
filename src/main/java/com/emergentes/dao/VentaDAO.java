/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.emergentes.dao;

import com.emergentes.modelo.Producto;
import com.emergentes.modelo.Venta;
import java.util.List;

/**
 *
 * @author illim
 */
public interface VentaDAO {
    
    public void insert(Venta venta) throws Exception;
    public void update(Venta venta) throws Exception;
    public void delete(int id) throws Exception;
    
    public Venta getById(int id)throws Exception;
    public List<Venta> getAll() throws Exception;
    
    
}
