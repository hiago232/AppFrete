/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidade;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Db.DB;
/**
 *
 * @author Usuário
 */
public class Veiculo {
    public String placa = "";
    public Double kilometragem ;
    public double consumo = 0;
    
    public Veiculo(){}
    public Veiculo(String placa,Double kilometragem,double consumo){
        this.placa = placa;
        this.kilometragem = kilometragem;
        this.consumo = consumo;
    }
    
    public void menu (){
        int op = 0;
        String menu = "1 - Placa: "+ placa+"\n"
                +"2 - Kilometragem: "+ kilometragem+"\n"
                +"3 - Consumo: "+ consumo+"\n";
        
    }
    
}
