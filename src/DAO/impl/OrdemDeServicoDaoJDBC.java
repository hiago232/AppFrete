/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.impl;
import DAO.DaoFactory;
import Db.DB;
import Db.DbException;
import DAO.OrdemDeServicoDao;
import DAO.VeiculoDao;
import entidade.OrdemDeServico;
import java.util.List;
import java.sql.Connection;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;




/**
 *
 * @author Usuário
 */
public class OrdemDeServicoDaoJDBC implements OrdemDeServicoDao{
    
    private Connection conn;
    
    public OrdemDeServicoDaoJDBC(Connection conn){
        this.conn = conn;
    }

    @Override
    public void insert(OrdemDeServico os) {

        PreparedStatement st = null;
        try {
            
            st = conn.prepareStatement(
                    "INSERT INTO ordem_servico"
                    + "(id,lista_itens,inicio,destino,distancia"
                    + ",tempo_servico,combustivel$"
                    + ",valor_hora,valor_total,fk_placa,fk_cpf_cnpj)"
                    + "VALUES"
                    + "(?,?,?,?,?,?,?,?,?,?)");
            st.setInt(1, os.getId());
            st.setString(2, os.getListaItens());
            st.setString(3, os.getInicio());
            st.setString(4, os.getDestino());
            st.setDouble(5, os.getDistancia());
            st.setString(6, os.getDuracao());
            st.setString(7, os.getVfcombust());
            st.setDouble(8, os.getValorhora());
            st.setDouble(9, os.getValortotal());
            st.setString(10, os.getPlaca());
            st.setString(11, os.getFKcpf_cnpj());

            int rowsAffected = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(OrdemDeServico obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<OrdemDeServico> findByFkCpf(String fkCpf) {
        List<OrdemDeServico> osList = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = conn.prepareStatement(
                    "select  * \n"
                    + "from  ordem_servico as os join  "
                    + "(select c.nome as nome_cliente, c.cpf as fk_cpf_cnpj from cliente_fisico as c) as cf\n"
                    + "on (os.fk_ClienteFisico_cpf = cf.fk_cpf_cnpj ) "
                     + "where os.fk_ClienteFisico_cpf = ?");
            st.setString(1, fkCpf);
            rs = st.executeQuery();
            while (rs.next()){
                OrdemDeServico os = instanciaOrdemDeServico(rs);;
                osList.add(os);
            }
            return osList;
        }catch(SQLException e){
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
        
        
    }

    public List<OrdemDeServico> findByFkCnpj(String fkCnpj) {
        List<OrdemDeServico> osList = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = conn.prepareStatement(
                    "select  * \n"
                    + "from  ordem_servico as os join  "
                    + "(select cj.responsavel, cj.cnpj as fk_cpf_cnpj from cliente_juridico as cj) as cj\n "
                    + "on (os.fk_ClienteJuridico_cnpj = cj.fk_cpf_cnpj ) "
                     + "where os.fk_ClienteJuridico_cnpj = ?");
            st.setString(1, fkCnpj);
            rs = st.executeQuery();
            while (rs.next()){
                OrdemDeServico os = instanciaOrdemDeServico(rs);;
                osList.add(os);
            }
            return osList;
        }catch(SQLException e){
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
        
        
    }
    @Override
    public List<OrdemDeServico> findAll() {

        List<OrdemDeServico> osList = new ArrayList<>();
        Statement st = null;
        ResultSet rs = null;
        try {
            st = conn.createStatement();

            rs = st.executeQuery("select  * \n" 
                    +"from  ordem_servico as os join  "
                    + "(select c.nome as nome_cliente, c.cpf from cliente_fisico as c) as cf\n"
                    +" on (os.fk_ClienteFisico_cpf = cf.cpf )  order by cf.nome_cliente;");


            while (rs.next()) {
                OrdemDeServico os = instanciaOrdemDeServico(rs);
                osList.add(os);
            }
            return osList;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    
        
        
    }
    
    private OrdemDeServico instanciaOrdemDeServico(ResultSet rs) throws SQLException  {
        OrdemDeServico os =new OrdemDeServico();
        
       
            String fk_cpf_cnpj = "";
            while (rs.getString("fk_cpf_cnpj").length() < 11) {
                fk_cpf_cnpj = "0" + rs.getString("fk_cpf_cnpj");
            } 
            
            // Esse bloco testa se é cpf ou cnpj
            if(rs.getString("fk_cpf_cnpj").length() <14 ) {
                if (rs.getString("fk_cpf_cnpj").length()>11)
                    while (rs.getString("fk_cpf_cnpj").length() < 11) {
                        fk_cpf_cnpj = "0" + rs.getString("fk_cpf_cnpj");
                }
            else {
                fk_cpf_cnpj = rs.getString("fk_cpf_cnpj");   
             }
            }

        os.setFKcpf_cnpj(fk_cpf_cnpj);
        os.setNomeCliente(rs.getString("nome_cliente"));
        os.setPlaca(rs.getString("fk_placa"));
        os.setListaItens(rs.getString("lista_itens"));
        os.setInicio(rs.getString("inicio"));
        os.setDestino(rs.getString("destino"));
        os.setDistancia(rs.getDouble("distancia"));
        os.setVfcombust(rs.getString("combustivel$"));
        os.setDuracao(rs.getString("tempo_servico"));
        os.setValorhora(rs.getDouble("valor_hora"));
        os.setValortotal(rs.getDouble("valor_total"));
        return os;
    
    
            }
}
