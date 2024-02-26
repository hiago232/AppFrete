/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;


import entidade.ClienteFisico;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Usuário
 */
public interface ClienteFisicoDao {
    Integer insert (ClienteFisico clienteFisico);
    void update(ClienteFisico obj);
    void deleteByPK(String PK);
    ClienteFisico findByPK(String PK);
    List<ClienteFisico> findAll();
    ClienteFisico instanciaCliente(ResultSet rs) throws SQLException;
}
