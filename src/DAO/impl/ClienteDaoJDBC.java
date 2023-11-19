/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.impl;

import DAO.ClienteDao;
import Db.DB;
import Db.DbException;
import entidade.Cliente;
import entidade.Veiculo;
import java.util.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
        List<Cliente> clienteList = new ArrayList<>();
        Statement st = null;
        ResultSet rs = null;
        try {
            st = conn.createStatement();

            rs = st.executeQuery("select * from cliente");

            while (rs.next()) {
                Cliente cliente = instanciaCliente(rs);
                clienteList.add(cliente);
            }
            return clienteList;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }

    private Cliente instanciaCliente(ResultSet rs) throws SQLException{
        String cpf_cnpj = "";
        
        while (rs.next()) {
            Cliente cliente = new Cliente();
            if (rs.getString("cpf_cnpj").length() < 11) {
                cpf_cnpj = "0" + rs.getString("cpf_cnpj");
                }else {
                cpf_cnpj = rs.getString("cpf_cnpj");}
            
            cliente.setNome(rs.getString("nome"));
            cliente.setCpf_cnpj(cpf_cnpj);
            cliente.setEmail(rs.getString("email"));
            cliente.setCep(rs.getLong("cep"));
            cliente.setNasc((String) rs.getString("data_nasc"));
            cliente.setEndereco(rs.getString("endereco"));
            cliente.setCel(rs.getLong("cel"));    
            return cliente;
            }
        return null;

        }
    }
    

