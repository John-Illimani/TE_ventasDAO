/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.emergentes.dao;

import com.emergentes.modelo.Producto;
import com.emergentes.utiles.ConexionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author illim
 */
public class ProductoDAOimpl extends ConexionDB implements ProductoDAO {

    @Override
    public void insert(Producto producto) throws Exception {
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("insert into productos (nombre,descripcion,precio)values (?,?,?)");
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            ps.setFloat(3, producto.getPrecio());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }

    }

    @Override
    public void update(Producto producto) throws Exception {
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("update productos set nombre = ?, descripcion = ?,precio = ? where id = ?");
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            ps.setFloat(3, producto.getPrecio());
            ps.setInt(4, producto.getId());
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
            PreparedStatement ps = this.conn.prepareStatement("delete from productos where id = ?");

            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }

    }

    @Override
    public Producto getById(int id) throws Exception {

        Producto pro = new Producto();
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("select * from productos where id = ?");

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery(); //consulta de seleccion
            if (rs.next()) {
                pro.setId(rs.getInt("id"));
                pro.setNombre(rs.getString("nombre"));
                pro.setDescripcion(rs.getString("descripcion"));
                pro.setPrecio(rs.getFloat("precio"));
            }

        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }

        return pro;

    }

    @Override
    public List<Producto> getAll() throws Exception {

        List<Producto> lista = null;
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("select * from productos");

            ResultSet rs = ps.executeQuery(); //consulta de seleccion

            lista = new ArrayList<Producto>();

            while (rs.next()) {   //while es para varios registros
                Producto pro = new Producto();

                pro.setId(rs.getInt("id"));
                pro.setNombre(rs.getString("nombre"));
                pro.setDescripcion(rs.getString("descripcion"));
                pro.setPrecio(rs.getFloat("precio"));

                lista.add(pro);
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
