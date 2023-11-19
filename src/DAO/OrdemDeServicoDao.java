/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import entidade.OrdemDeServico;
import entidade.Veiculo;
import java.util.List;

/**
 *
 * @author Usuário
 */
public interface OrdemDeServicoDao {
    void insert(OrdemDeServico obj);

    void update(OrdemDeServico obj);

    void deleteById(int id);

    OrdemDeServico findById(int id);

    List<OrdemDeServico> findAll();
    
}
