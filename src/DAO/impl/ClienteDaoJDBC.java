/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.impl;

import DAO.ClienteDao;
import DAO.DaoFactory;
import DAO.OrdemDeServicoDao;
import Db.DB;
import Db.DbException;
import entidade.Cliente;
import entidade.OrdemDeServico;
import entidade.Veiculo;
import java.util.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
    public Integer insert(Cliente cliente) {
        Connection conn = null;
        PreparedStatement st = null;
        int rowsAffected = 0;
        try {
            conn = DB.getConnection();
            st = conn.prepareStatement(
                    "INSERT INTO cliente"
                    + "(nome,endereco,data_nasc,cpf_cnpj,cep,cel,email)"
                    + "VALUES"
                    + "(?,?,?,?,?,?,?)");
            st.setString(1, cliente.getNome());
            st.setString(2, cliente.getEndereco());
            st.setString(3, cliente.getNasc());
            st.setString(4, cliente.getCpf_cnpj());

            st.setLong(5, cliente.getCep());
            st.setLong(6, cliente.getCel());
            st.setString(7, cliente.getEmail());

            rowsAffected = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.closeStatement(st);
        }
        return rowsAffected;
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
        
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = conn.prepareStatement(
                    "select * from cliente "
                            + "where cpf_cnpj = ?");
            st.setString(1, cpf_cnpj);
            rs = st.executeQuery();
            if (rs.next()){
                Cliente cliente= instanciaCliente(rs);;
                return cliente;
            }
            return null;
        }catch(SQLException e){
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
        
    }

    @Override
    public List<Cliente> findAll() {
        
        List<Cliente> clienteList = new ArrayList<>();

        Statement st = null;
        ResultSet rs = null;
        try {
            st = conn.createStatement();

            rs = st.executeQuery("select  * \n"
                    + "from  cliente");

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
        String fk_cpf_cnpj="";
        OrdemDeServicoDao osDao = DaoFactory.criaOrdemDeServicoDao();


            Cliente cliente = new Cliente();
            fk_cpf_cnpj = rs.getString("cpf_cnpj");
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
            cliente.setOslist(osDao.findByFkCpfCnpj(fk_cpf_cnpj));
            return cliente;


        }
    }
    

