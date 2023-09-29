/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidade;
import javax.swing.*;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Usuário
 */
public class OrdemDeServico {
    public String listaItens ="";
    public String moveis = "";
    public String local = "";
    public String destino = "";
    public double distancia = 0 ;
    public String tempo = "";
    public double valortotal = 0;
    public String t = "Ordem de Serviço";
    // Construtor padrão
    public OrdemDeServico(){};
    
    //Procedimento
    public void menu(){
     
        // Declaração de variáveis
        List<String> itens = new ArrayList<>();
        boolean sair = true;
        int op = 0;
    
        while (sair){
            String menuos = "1 - Add item\n"
                +"2 - Exibir lista\n"
                +"3 - Local inicial:   "+local+"\n"
                +"4 - Destino:    "+destino+"\n"
                +"5 - Distância:   "+distancia+" KM\n"
                +"6 - Combustivel \n"
                +"7 - Tempo \n"
                +"8 - Valor total \n"
                +"9 - Fechar OS\n";
        
            
            op = Integer.parseInt(JOptionPane.
                    showInputDialog(null,
                    menuos,
                    t,
                    JOptionPane.QUESTION_MESSAGE));
            
            switch (op) {
                case 1 : 
                    itens = addItem(itens);
                    break;
                case 2 :
                    for (String item : itens) {
                        listaItens = listaItens + "\n" + item;
                    }
                    JOptionPane.showMessageDialog(null, listaItens);
                    break ;
                case 3 : 
                    System.out.println(); 
                    local = JOptionPane.showInputDialog(null,
                            "Insira o local inicial: ",
                            t,
                            JOptionPane.QUESTION_MESSAGE);
                    break;
                case 4:                    
                    destino = JOptionPane.showInputDialog(null,
                            "Insira o destino final: ",
                            t,
                            JOptionPane.QUESTION_MESSAGE);
                    break;
                case 5 :
                    
                    distancia = Double.parseDouble(JOptionPane.
                            showInputDialog(null,
                            "Insira distancia em KM: ",
                            t,
                            JOptionPane.QUESTION_MESSAGE));
                    break;
                case 6 : 
                    
                    //combustivel();
                    break;
                case 7 :
                    //Tempo();
                    break;
                case 8 :
                    //valorTotal();
                    break;
                case 9 :
                    sair = false;
                    break;
                default :
                    break;
            }
        }

        
        

        
    }
    public List<String> addItem (List<String> itens){
        boolean voltar = true;
        String item = "";
        while (voltar){
            
            item = JOptionPane.showInputDialog(null,
                    "Insira o item abaixo: ", t,1);
            if ("voltar".equals(item)){
                return itens;
            }
            itens.add(item);
        }
        return itens;
    
    }
    
}
