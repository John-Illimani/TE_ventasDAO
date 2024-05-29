/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.emergentes.dao;

import com.emergentes.modelo.Cliente;
import com.emergentes.modelo.Usuario;
import com.emergentes.utiles.ConexionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author illim
 */
public class UsuarioDAOimpl extends ConexionDB implements UsuarioDAO{
    
      @Override
    public void insert(Usuario usr) throws Exception {
        try {
            this.conectar();
        PreparedStatement ps = this.conn.prepareStatement("insert into usuarios (nombres,apellidos,correo,password)values (?,?,?,md5(?))");
        ps.setString(1, usr.getNombres());
        ps.setString(2, usr.getApellidos());
        ps.setString(3, usr.getCorreo());
        ps.setString(4, usr.getPassword());
        ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        }finally{
        this.desconectar();
        }
        
    }

    @Override
    public void update(Usuario usr) throws Exception {
        try {
            this.conectar();
        PreparedStatement ps = this.conn.prepareStatement("update usuarios set nombres = ?, apellidos = ?,correo = ? ,password = md5(?) where id = ?");
        ps.setString(1, usr.getNombres());
        ps.setString(2, usr.getApellidos());
        ps.setString(3, usr.getCorreo());
        ps.setString(4, usr.getPassword());
        ps.setInt(5, usr.getId());
        ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        }finally{
        this.desconectar();
        }
    }

    @Override
    public void delete(int id) throws Exception {
         try {
            this.conectar();
        PreparedStatement ps = this.conn.prepareStatement("delete from usuarios where id = ?");
        
        ps.setInt(1, id);
        ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        }finally{
        this.desconectar();
        }
        
    }

    @Override
    public Usuario getById(int id) throws Exception {
        
        Usuario u = new Usuario();
         try {
            this.conectar();
        PreparedStatement ps = this.conn.prepareStatement("select * from usuarios where id = ?");
        
        ps.setInt(1, id);
        
             ResultSet rs = ps.executeQuery(); //consulta de seleccion
             if (rs.next() ){
                u.setId(rs.getInt("id"));
                u.setNombres(rs.getString("nombres"));
                u.setApellidos(rs.getString("apellidos"));
                u.setCorreo(rs.getString("correo"));
                u.setPassword(rs.getString("password"));
             }
        
        } catch (Exception e) {
            throw e;
        }finally{
        this.desconectar();
        }
         
        return u;
        
    }

    @Override
    public List<Usuario> getAll() throws Exception {
        
        List<Usuario> lista = null;
         try {
            this.conectar();
        PreparedStatement ps = this.conn.prepareStatement("select * from usuarios");
        
        
        
             ResultSet rs = ps.executeQuery(); //consulta de seleccion
             
             lista = new ArrayList<Usuario>();
             
             while (rs.next() ){   //while es para varios registros
                 Usuario u = new Usuario();
                 
                u.setId(rs.getInt("id"));
                u.setNombres(rs.getString("nombres"));
                u.setApellidos(rs.getString("apellidos"));
                
                u.setCorreo(rs.getString("correo"));
                u.setPassword(rs.getString("password"));
                
                
                lista.add (u);
             }
             
             rs.close();
             ps.close();
        
        } catch (Exception e) {
            throw e;
        }finally{
        this.desconectar();
        }
         
         return lista;
        
    }
    
}
