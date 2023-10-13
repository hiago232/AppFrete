/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package aplicacao;
import entidade.OrdemDeServico;
import entidade.Cliente;
import entidade.Veiculo;
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
        long cpf = 0;
        boolean sair = true;
        int op = 0;
        List<Cliente> clientelist = new ArrayList<>();
        List<OrdemDeServico> oslist = new ArrayList<>();
        List<Veiculo> veiculolist = new ArrayList<>();
        
        while(sair){
            op = menu();
            switch (op) {
                case 1 :
                    op = Integer.parseInt(JOptionPane
                            .showInputDialog(null
                                    ,"1 - Cliente\n 2 - Avulso\n", t
                                    , 3));
                    if(op == 1){
                        cpf = Long.parseLong((JOptionPane
                                .showInputDialog(null,
                                         "Insira o CPF: "
                                        , t
                                        , 3)));
                        cpf = temCpf(clientelist,cpf);//retorna o indice
                        OrdemDeServico os = new OrdemDeServico(veiculolist);
                        os.menu();
                        List<OrdemDeServico> clienteoslist = new ArrayList<>();
                        clienteoslist=clientelist.get((int)cpf).getOslist();
                        clienteoslist.add(os);//atualiza lista de os do cliente
                        oslist.add(os);//atualiza lista de os geral
                        veiculolist = os.veiculolist;
                        clientelist.get((int)cpf).setOslist(clienteoslist);
                        break;
                    }
                        OrdemDeServico os = new OrdemDeServico(veiculolist);
                        os.menu();
                        oslist.add(os);
                        break;                  
                   
                case 2 :
                    clientelist = cadCliente(clientelist);
                    break;
                case 3 :
                    exibeClienteList(clientelist);
                    break;
                case 4 : 
                    op = Integer.parseInt(JOptionPane.showInputDialog(null
                            , "1 - Cadastrar\n "
                                    + "2 - Alterar Dados\n "
                                    + "3 - Exibir Lista de Veículos\n"
                            , t, 3));
                    if(op == 1){
                        veiculolist.add(cadVeiculo());
                        break;
                    }
                    if (op == 2){    
                        if (veiculolist.isEmpty()) {
                            JOptionPane.showMessageDialog(null,
                                     "Nenhum Veículo Cadastrado!");
                            break;
                        }
                        int i = temPlaca(veiculolist);
                        if (i == -1) {
                            JOptionPane.showMessageDialog(null,
                                    "Veículo Não Encontrado!");
                            break;
                        }
                        veiculolist.get(i).menu();
                        break;
                    }
                    if (veiculolist.isEmpty()){
                        JOptionPane.showMessageDialog(null
                                ,"Nenhum Veículo Cadastrado!");
                        break;
                    }
                    exibeVeiculoList(veiculolist);
                    break;

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
         String nasc = "";
         long cpf = 0;
         int cep = 0;
         long cel = 0;
         //Data de nascimento
         nome = JOptionPane.showInputDialog(null, "Nome: "
                 , t, 3);
         cpf = Long.parseLong(JOptionPane.showInputDialog(null,
                 "CPF: ",
                t, 3));
         nasc = JOptionPane.showInputDialog(null,
                 "Data de Nascimento: ",
                t, 3);         
         
         endereco = JOptionPane.showInputDialog(null
                 , "Endereço: ",
                 t, 3);
         
         email = JOptionPane.showInputDialog(null
                 , "E-mail: ",
                 t, 3);

         cep = Integer.parseInt(JOptionPane.showInputDialog(null
                 , "CEP: ",
                 t, 3));
         cel= Long.parseLong(JOptionPane.showInputDialog(null
                 , "Cel: ",
                 t, 3));
             
         Cliente cliente = new Cliente(nome,endereco,nasc,cpf,cep,cel,email);
         
         clientelist.add(cliente);
         return clientelist;
    }
    public static void exibeClienteList(List<Cliente>clientelist){
        String lista = "";
        int i = 0;
        for(Cliente cliente : clientelist){
            
            lista = lista +"Id: "+i+"  "+cliente.getNome()+" Idade: "+cliente.idade+" "
                    +"Ordens de Serviços: "
                    +cliente.getOslist().size()+"\n";
            i++;
        }
        JOptionPane.showMessageDialog(null, lista);
    }
    public static long temCpf(List<Cliente> clientelist,long cpf){
        long i;
        for (i = 0; i < clientelist.size(); i++) {
            if (clientelist.get((int) i).getCpf() == cpf) {
                return i;
            }
        }
        return -1;
    }
    public static Veiculo cadVeiculo (){
        String placa = "";
        long kilometragem = 0;
        double consumo = 0;
        
        placa =  JOptionPane.showInputDialog(null
                , "Digite a Placa do Veiculo Abaixo:");
        kilometragem = Long.parseLong(JOptionPane.showInputDialog(null
                , "Digite a Kilometragem Abaixo:"));
        consumo = Double.parseDouble(JOptionPane.showInputDialog(null
                , "Digite o Consumo de Combustivel do Veiculo Abaixo: (Km/L)"));
        Veiculo veiculo = new Veiculo(placa,kilometragem,consumo);
        return veiculo;
    }
    public static int temPlaca (List<Veiculo> veiculolist){
        String placa =  "";
        int i = 0;
        
        placa = JOptionPane.showInputDialog(null
                , "Digite a Placa do Veiculo Abaixo:");
        for (i = 0; i < veiculolist.size(); i++) {
            if (veiculolist.get( i).placa.equals(placa)) {
                
                return i;
            }
        }
        return -1;
    }
    public static void exibeVeiculoList(List<Veiculo>veiculolist){
        String lista = "";
        int i = 0;
        
        for (Veiculo veic : veiculolist) {
            lista = lista + i +" Placa: "+veic.placa+" "+veic.kilometragem+" Km";
            i++;
        }
        JOptionPane.showMessageDialog(null, lista);
    }
        
   
}


