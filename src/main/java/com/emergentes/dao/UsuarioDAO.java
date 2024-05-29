/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.emergentes.dao;

import com.emergentes.modelo.Cliente;
import com.emergentes.modelo.Usuario;
import java.util.List;

/**
 *
 * @author illim
 */
public interface UsuarioDAO {
     public void insert(Usuario usr) throws Exception;
    public void update(Usuario usr) throws Exception;
    public void delete(int id) throws Exception;
    
    public Usuario getById(int id)throws Exception;
    public List<Usuario> getAll() throws Exception;
}
