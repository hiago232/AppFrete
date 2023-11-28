/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import entidade.Cliente;
import java.util.List;

/**
 *
 * @author Usuário
 */
public interface ClienteDao {
    Integer insert (Cliente obj);
    void update(Cliente obj);
    void deleteByCpf_Cnpj(String cpf_cnpj);
    Cliente findByCpf_Cnpj(String cpf_cnpj);
    List<Cliente> findAll();
}
