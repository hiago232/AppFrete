/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.impl;

import DAO.ClienteDao;
import entidade.Cliente;
import java.util.List;
import java.sql.Connection;

/**
 *
 * @author Usuário
 */
public class ClienteDaoJDBC implements ClienteDao{
    
    private Connection conn;
    
    public ClienteDaoJDBC (Connection conn){
        this.conn = conn;
    }

    @Override
    public void insert(Cliente obj) {
    }

    @Override
    public void update(Cliente obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteByCpf_Cnpj(String cpf_cnpj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Cliente findByCpf_Cnpj(String cpf_cnpj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Cliente> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
