/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidade;
import javax.swing.*;
import java.util.Scanner;
/**
 *
 * @author Usuário
 */
public class OrdemDeServico {
    public String moveis = "";
    public String local = "";
    public String destino = "";
    public double distancia = 0;
    public String tempo = "";
    public double valortotal = 0;
    
    // Construtor padrão
    public OrdemDeServico(){};
    
    //Procedimento
    public void menu(){
        Scanner sc = new Scanner(System.in);
        // Declaração de variáveis
        
        
        boolean sair = true;
        int op = 0;
        
        
        String itens = "";
        String add = "";
        String menuos = "1 - Add item       2 - Local inicial\n"
                +"3 - Destino        4 - Distância\n"
                +"5 - Combustivel    6 - Tempo\n"
                +"7 - Valor total    8 - Fechar OS\n";
        while (sair){
            System.out.println(menuos);
            op = sc.nextInt();
            sc.nextLine();
            switch (op) {
                case 1 : 
                    //addItem();
                    break;
                case 2 : 
                    System.out.println("Insira o local inicial: "); 
                    local =  sc.nextLine();
                    break;
                case 3 : 
                    System.out.println("Insira o destino final: ");
                    destino = sc.nextLine();
                    break;
                case 4 :
                    System.out.println("Insira distancia em KM: ");
                    distancia = sc.nextDouble();
                    sc.nextLine();
                    break;
                case 5 : 
                    //combustivel();
                    break;
                case 6 :
                    //Tempo();
                    break;
                case 7 :
                    //valorTotal();
                    break;
                case 8 :
                    sair = false;
                    break;
                default :
                    break;
            }
        }
        
        
        sc.close();
        
    }
    
}
