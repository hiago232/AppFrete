/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DAO.impl.VeiculoDaoJDBC;
import DAO.impl.ClienteDaoJDBC;
import Db.DB;

/**
 *
 * @author Usuário
 */
public class DaoFactory {
    public static ClienteDao criaClienteDao(){
        return new ClienteDaoJDBC(DB.getConnection());
    }
    public static VeiculoDao criaVeiculoDao() {
        return new VeiculoDaoJDBC(DB.getConnection());}
}
