/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.emergentes.dao;

import com.emergentes.modelo.Producto;
import com.emergentes.modelo.Venta;
import com.emergentes.utiles.ConexionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author illim
 */
public class VentaDAOimpl extends ConexionDB implements VentaDAO {

    @Override
    public void insert(Venta venta) throws Exception {
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("insert into ventas (producto_id,cliente_id,fecha)values (?,?,?)");
            ps.setInt(1, venta.getProducto_id());
            ps.setInt(2, venta.getCliente_id());
            ps.setDate(3, venta.getFecha());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }

    }

    @Override
    public void update(Venta venta) throws Exception {
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("update ventas set producto_id = ?, cliente_id = ?,fecha = ? where id = ?");
            ps.setInt(1, venta.getProducto_id());
            ps.setInt(2, venta.getCliente_id());
            ps.setDate(3, venta.getFecha());
            ps.setInt(4, venta.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("delete from ventas where id = ?");

            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }

    }

    @Override
    public Venta getById(int id) throws Exception {

        Venta ven = new Venta();
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("select * from ventas where id = ?");

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery(); //consulta de seleccion
            if (rs.next()) {
                ven.setId(rs.getInt("id"));
                ven.setProducto_id(rs.getInt("producto_id"));
                ven.setCliente_id(rs.getInt("cliente_id"));
                ven.setFecha(rs.getDate("fecha"));
            }

        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }

        return ven;

    }

    @Override
    public List<Venta> getAll() throws Exception {

        List<Venta> lista = null;
        try {
            
            
            this.conectar();
            String sql="select v.*,p.nombre as producto,c.nombre as cliente from ventas v ";
                    sql += "left join productos p on v.producto_id = p.id ";
                    sql += "left join clientes c on v.cliente_id =c.id";
            PreparedStatement ps = this.conn.prepareStatement(sql);
                    

            ResultSet rs = ps.executeQuery(); //consulta de seleccion

            lista = new ArrayList<Venta>();

            while (rs.next()) {   //while es para varios registros
                Venta ven = new Venta();

                ven.setId(rs.getInt("id"));
                ven.setProducto_id(rs.getInt("producto_id"));
                ven.setCliente_id(rs.getInt("cliente_id"));
                ven.setFecha(rs.getDate("fecha"));              
                ven.setCliente(rs.getString("cliente"));
                ven.setProducto(rs.getString("producto"));

                lista.add(ven);
            }

            rs.close();
            ps.close();

        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }

        return lista;

    }

}
