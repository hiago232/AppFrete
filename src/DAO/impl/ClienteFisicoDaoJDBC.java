/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.impl;

import DAO.DaoFactory;
import DAO.OrdemDeServicoDao;
import Db.DB;
import Db.DbException;

import entidade.ClienteFisico;
import java.util.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import DAO.ClienteFisicoDao;

/**
 *
 * @author Usuário
 */
public class ClienteFisicoDaoJDBC implements ClienteFisicoDao{
    
    private Connection conn;
    
    public ClienteFisicoDaoJDBC (Connection conn){
        this.conn = conn;
    }

    @Override
    public Integer insert(ClienteFisico cliente) {
        Connection conn = null;
        PreparedStatement st = null;
        int rowsAffected = 0;
        try {
            conn = DB.getConnection();
            st = conn.prepareStatement(
                    "INSERT INTO cliente_fisico"
                    + "(cpf,nome,data_nasc,email,cel,cep,rua,numero,bairro,cidade,estado)"
                    + "VALUES"
                    + "(?,?,?,?,?,?,?,?,?,?,?)");
            st.setString(1, cliente.getCpf());
            st.setString(2, cliente.getNome());
            st.setString(3, cliente.getNasc());
            st.setString(4, cliente.getEmail());
            st.setLong(5, cliente.getCel());
            st.setLong(6, cliente.getCep());
            st.setString(7, cliente.getRua());
            st.setInt(8, cliente.getNumero());
            st.setString(9, cliente.getBairro());
            st.setString(10, cliente.getCidade());
            st.setString(11, cliente.getEstado());

            rowsAffected = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.closeStatement(st);
        }
        return rowsAffected;
    }

    @Override
    public void update(ClienteFisico obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteByPK(String cpf) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ClienteFisico findByPK(String cpf) {
        
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = conn.prepareStatement(
                    "select * from cliente_fisico "
                            + "where cpf = ?");
            st.setString(1, cpf);
            rs = st.executeQuery();
            if (rs.next()){
                ClienteFisico cliente= instanciaCliente(rs);;
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
    public List<ClienteFisico> findAll() {
        
        List<ClienteFisico> clienteList = new ArrayList<>();

        Statement st = null;
        ResultSet rs = null;
        try {
            st = conn.createStatement();

            rs = st.executeQuery("select  * \n"
                    + "from  cliente_fisico");

            while (rs.next()) {
                ClienteFisico cliente = instanciaCliente(rs);      
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
    @Override
    public ClienteFisico instanciaCliente(ResultSet rs) throws SQLException{
        String cpf = "";
        String fk_cpf="";
        OrdemDeServicoDao osDao = DaoFactory.criaOrdemDeServicoDao();


            ClienteFisico cliente = new ClienteFisico();
            fk_cpf = rs.getString("cpf");
            if (rs.getString("cpf").length() < 11) {
                cpf = "0" + rs.getString("cpf");
                }else {
                cpf = rs.getString("cpf");}
            cliente.setCpf(cpf);
            cliente.setNome(rs.getString("nome"));
            cliente.setNasc((String) rs.getString("data_nasc"));
            cliente.setEmail(rs.getString("email"));
            cliente.setCel(rs.getLong("cel"));  
            cliente.setCep(rs.getLong("cep"));
            cliente.setRua(rs.getString("rua"));
            cliente.setNumero(rs.getInt("numero"));
            cliente.setBairro(rs.getString("bairro"));
            cliente.setCidade(rs.getString("cidade"));
            cliente.setEstado(rs.getString("estado"));
            cliente.setOslist(osDao.findByFkCpf(fk_cpf));
            return cliente;


        }
    }
    

