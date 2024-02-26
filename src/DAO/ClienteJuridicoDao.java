/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;



import entidade.ClienteJuridico;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Usuário
 */
public interface ClienteJuridicoDao {
    Integer insert (ClienteJuridico obj);
    void update(ClienteJuridico obj);
    void deleteByPK(String PK);
    ClienteJuridico findByPK(String PK);
    List<ClienteJuridico> findAll();
    ClienteJuridico instanciaCliente(ResultSet rs) throws SQLException;
}
