/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DAO.impl.VeiculoDaoJDBC;

import DAO.impl.ClienteFisicoDaoJDBC;
import DAO.impl.ClienteJuridicoDaoJDBC;
import DAO.impl.OrdemDeServicoDaoJDBC;
import Db.DB;

/**
 *
 * @author Usuário
 */
public class DaoFactory {
    public static ClienteFisicoDao criaClienteFisicoDao(){
        return new ClienteFisicoDaoJDBC(DB.getConnection());
    }
    public static ClienteJuridicoDao criaClienteJuridicoDao(){
        return new ClienteJuridicoDaoJDBC(DB.getConnection());
    }
    public static VeiculoDao criaVeiculoDao() {
        return new VeiculoDaoJDBC(DB.getConnection());
    }   
    public static OrdemDeServicoDao criaOrdemDeServicoDao(){
        return new OrdemDeServicoDaoJDBC(DB.getConnection());
    }
}
