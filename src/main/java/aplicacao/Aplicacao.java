/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package aplicacao;
import entidade.OrdemDeServico;
import entidade.Cliente;
import javax.swing.*;
import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author Usuário
 */
public class Aplicacao {
    public static String t = "Tela Inicial";

    public static void main(String[] args) {
        boolean sair = true;
        int op = 0;
        List<Cliente> clientelist = new ArrayList<>();
        
        OrdemDeServico os = new OrdemDeServico();
        
        while(sair){
            op = menu();
            switch (op) {
                case 1 :
                    os.menu();
                    break;
                case 2 :
                    clientelist = cadCliente(clientelist);
                    break;
                case 3 :
                    exibeClienteList(clientelist);
            }

        }

        
      /**Cliente cliente = new Cliente();
      cliente.menu();
      */
    }
    public static int menu (){
        int op = 0;
        String menu = """
                      1 - Abrir Ordem de Servi\u00e7o
                      2 - Cadastrar Cliente
                      3 - Exibir Lista de Clientes
                      4 - Veículo
                      5 - Info.
                      6 - Sair
                      """;
        op = Integer.parseInt(JOptionPane.showInputDialog(null
                , menu, t, 3));
        return op;
    }
    
    public static List<Cliente> cadCliente(List<Cliente>clientelist){
         String nome , endereco, email;
         String t = "Novo Cliente";
         int cpf = 0;
         int cep = 0;
         int cel = 0;
         //Data de nascimento
         nome = JOptionPane.showInputDialog(null, "Nome: "
                 , t, 3);
         /**
         endereco = JOptionPane.showInputDialog(null
                 , "Endereço: ",
                 t, 3);
         email = JOptionPane.showInputDialog(null
                 , "E-mail: ",
                 t, 3);
         cpf = Integer.parseInt(JOptionPane.showInputDialog(null
                 , "CPF: ",
                 t, 3));
         cep = Integer.parseInt(JOptionPane.showInputDialog(null
                 , "CEP: ",
                 t, 3));
         cel= Integer.parseInt(JOptionPane.showInputDialog(null
                 , "Cel: ",
                 t, 3));
                 * */
         //Cliente cliente = new Cliente(nome,endereco,cpf,cep,cel,email);
         Cliente cliente = new Cliente(nome);
         clientelist.add(cliente);
         return clientelist;
    }
    public static void exibeClienteList(List<Cliente>clientelist){
        String lista = "";
        for(Cliente cliente : clientelist){
            
            lista = lista + cliente.getNome()+"\n";
        }
        JOptionPane.showMessageDialog(null, lista);
    }
}


