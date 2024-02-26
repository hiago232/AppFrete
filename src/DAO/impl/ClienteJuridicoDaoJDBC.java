/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.impl;

import DAO.DaoFactory;
import DAO.OrdemDeServicoDao;
import Db.DB;
import Db.DbException;
import java.util.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import DAO.ClienteJuridicoDao;
import entidade.ClienteJuridico;

/**
 *
 * @author Usuário
 */
public class ClienteJuridicoDaoJDBC implements ClienteJuridicoDao{
    
    private Connection conn;
    
    public ClienteJuridicoDaoJDBC (Connection conn){
        this.conn = conn;
    }

    @Override
    public Integer insert(ClienteJuridico cliente) {
        Connection conn = null;
        PreparedStatement st = null;
        int rowsAffected = 0;
        try {
            conn = DB.getConnection();
            st = conn.prepareStatement(
                    "INSERT INTO cliente_juridico"
                    + "(cnpj,responsavel,razao_social,nome_fantasia,email,cel"
                            + ",cep,rua,numero,bairro,cidade,estado)"
                    + "VALUES"
                    + "(?,?,?,?,?,?,?,?,?,?,?,?)");
            st.setString(1, cliente.getCnpj());
            st.setString(2, cliente.getResponsavel());
            st.setString(3, cliente.getRazaoSocial());
            st.setString(4, cliente.getNomeFantasia());
            st.setString(5, cliente.getEmail());
            st.setLong(6, cliente.getCel());
            st.setLong(7, cliente.getCep());
            st.setString(8, cliente.getRua());
            st.setInt(9, cliente.getNumero());
            st.setString(10, cliente.getBairro());
            st.setString(11, cliente.getCidade());
            st.setString(12, cliente.getEstado());

            rowsAffected = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.closeStatement(st);
        }
        return rowsAffected;
    }

    @Override
    public void update(ClienteJuridico obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteByPK(String cnpj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ClienteJuridico findByPK(String cnpj) {
        
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = conn.prepareStatement(
                    "select * from cliente_juridico "
                            + "where cnpj = ?");
            st.setString(1, cnpj);
            rs = st.executeQuery();
            if (rs.next()){
                ClienteJuridico cliente= instanciaCliente(rs);;
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
    public List<ClienteJuridico> findAll() {
        
        List<ClienteJuridico> clienteList = new ArrayList<>();

        Statement st = null;
        ResultSet rs = null;
        try {
            st = conn.createStatement();

            rs = st.executeQuery("select  * \n"
                    + "from  cliente_juridico");

            while (rs.next()) {
                ClienteJuridico cliente = instanciaCliente(rs);      
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
    public ClienteJuridico instanciaCliente(ResultSet rs) throws SQLException{
        String cnpj= "";
        String fk_cnpj="";
        OrdemDeServicoDao osDao = DaoFactory.criaOrdemDeServicoDao();

            
            ClienteJuridico cliente = new ClienteJuridico();
            fk_cnpj = rs.getString("cnpj");
            if (rs.getString("cnpj").length() < 14) {
                cnpj = "0" + rs.getString("cnpj");
                }else {
                cnpj = rs.getString("cnpj");}

            cliente.setCnpj(cnpj);
            cliente.setResponsavel(rs.getString("responsavel"));
            cliente.setRazaoSocial(rs.getString("razao_social"));
            cliente.setNomeFantasia(rs.getString("nome_fantasia"));
            cliente.setEmail(rs.getString("email"));
            cliente.setCel(rs.getLong("cel"));  
            cliente.setCep(rs.getLong("cep"));
            cliente.setRua(rs.getString("rua"));
            cliente.setNumero(rs.getInt("numero"));
            cliente.setBairro(rs.getString("bairro"));
            cliente.setCidade(rs.getString("cidade"));
            cliente.setEstado(rs.getString("estado"));
            cliente.setOslist(osDao.findByFkCnpj(fk_cnpj));
            return cliente;


        }
    }
    

