/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;


import entidade.Veiculo;
import java.util.List;

/**
 *
 * @author Usuário
 */
public interface VeiculoDao {
    void insert(Veiculo obj);

    void update(Veiculo obj);

    void deleteByPlaca(String Placa);

    Veiculo findByPlaca(String Placa);

    List<Veiculo> findAll();
}
