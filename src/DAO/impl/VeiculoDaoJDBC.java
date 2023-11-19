package DAO.impl;

import DAO.VeiculoDao;
import Db.DB;
import Db.DbException;
import entidade.Veiculo;
import java.sql.Connection;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Usuário
 */
public class VeiculoDaoJDBC implements VeiculoDao{
    private Connection conn;

    public VeiculoDaoJDBC(Connection conn) {
        this.conn = conn;
    }
    
    
    @Override
    public void insert(Veiculo obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Veiculo obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteByPlaca(String Placa) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Veiculo findByPlaca(String Placa) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = conn.prepareStatement(
                    "select * from veiculo "
                            + "where placa = ?");
            st.setString(1, Placa);
            rs = st.executeQuery();
            if (rs.next()){
                Veiculo veiculo = instanciaVeiculo(rs);;
                return veiculo;
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
    public List<Veiculo> findAll() {
        List<Veiculo> veiculos = new ArrayList<>();
        Statement st = null;
        ResultSet rs = null;
        try {
            st = conn.createStatement();

            rs = st.executeQuery("select * from veiculo");

            while (rs.next()) {
                Veiculo veiculo = instanciaVeiculo(rs);
                veiculos.add(veiculo);
            }
            return veiculos;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }

    private Veiculo instanciaVeiculo(ResultSet rs) throws SQLException  {
        Veiculo veiculo =new Veiculo();
        veiculo.setPlaca(rs.getString("placa"));
        veiculo.setKilometragem(rs.getDouble("kilometragem"));
        veiculo.setConsumo(rs.getDouble("consumo"));
        return veiculo;
    }
}


