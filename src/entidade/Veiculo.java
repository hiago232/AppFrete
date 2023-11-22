/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidade;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Db.DB;
import java.io.Serializable;
import java.util.Objects;
import javax.swing.JOptionPane;
/**
 *
 * @author Usuário
 */
public class Veiculo implements Serializable{
    private String placa = "";
    private Double kilometragem ;
    private double consumo = 0;
    
    public Veiculo(){}
    public Veiculo(String placa,Double kilometragem,double consumo){
        this.placa = placa;
        this.kilometragem = kilometragem;
        this.consumo = consumo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Double getKilometragem() {
        return kilometragem;
    }

    public void setKilometragem(Double kilometragem) {
        this.kilometragem = kilometragem;
    }

    public double getConsumo() {
        return consumo;
    }

    public void setConsumo(double consumo) {
        this.consumo = consumo;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Veiculo other = (Veiculo) obj;
        return Objects.equals(this.placa, other.placa);
    }
    
    
    
    public void menu (){
        int op = 0;
        String menu = "1 - Placa: "+ placa+"\n"
                +"2 - Kilometragem: "+ kilometragem+"\n"
                +"3 - Consumo: "+ consumo+"\n";
        JOptionPane.showInputDialog(menu);
        
    }
    
    public String toString(){
         return "----------------"+"\n"
                +"Placa: " + placa + "\n"
                + "Kilometragem: " + String.format("%.3f", kilometragem) + "\n"
                + "Consumo: " + String.format("%.2f", consumo) + "\n"
                +"----------------"+"\n";
    }
    
}
