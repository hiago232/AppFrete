/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidade;

/**
 *
 * @author Usuário
 */
public class Veiculo {
    public String placa = "";
    public long kilometragem = 0;
    public double consumo = 0;
    
    public Veiculo(){}
    public Veiculo(String placa,long kilometragem,double consumo){
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
