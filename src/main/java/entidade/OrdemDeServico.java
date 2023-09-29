/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidade;
import javax.swing.*;

/**
 *
 * @author Usuário
 */
public class OrdemDeServico {
    public String moveis = "";
    public String local = "";
    public String destino = "";
    public double distancia ;
    public String tempo = "";
    public double valortotal = 0;
    
    // Construtor padrão
    public OrdemDeServico(){};
    
    //Procedimento
    public void menu(){
     
        // Declaração de variáveis
        
        
        boolean sair = true;
        int op = 0;
                
        String t = "Ordem de Serviço";
        String itens = "";
        String add = "";
        while (sair){
            String menuos = "1 - Add item\n"
                +"2 - Local inicial:   "+local+"\n"
                +"3 - Destino:    "+destino+"\n"
                +"4 - Distância:   "+distancia+" KM\n"
                +"5 - Combustivel \n"
                +"6 - Tempo \n"
                +"7 - Valor total \n"
                +"8 - Fechar OS\n";
        
            
            op = Integer.parseInt(JOptionPane.
                    showInputDialog(null,
                    menuos,
                    t,
                    JOptionPane.QUESTION_MESSAGE));
            
            switch (op) {
                case 1 : 
                    //addItem();
                    break;
                case 2 : 
                    System.out.println(); 
                    local = JOptionPane.showInputDialog(null,
                            "Insira o local inicial: ",
                            t,
                            JOptionPane.QUESTION_MESSAGE);
                    break;
                case 3 : 
                    System.out.println();
                    destino = JOptionPane.showInputDialog(null,
                            "Insira o destino final: ",
                            t,
                            JOptionPane.QUESTION_MESSAGE);
                    break;
                case 4 :
                    
                    distancia = Double.parseDouble(JOptionPane.
                            showInputDialog(null,
                            "Insira distancia em KM: ",
                            t,
                            JOptionPane.QUESTION_MESSAGE));
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

        
        

        
    }
    
}
