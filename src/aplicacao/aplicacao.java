/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package aplicacao;
import DAO.ClienteDao;
import DAO.DaoFactory;
import DAO.OrdemDeServicoDao;
import DAO.VeiculoDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Db.DB;
import entidade.OrdemDeServico;
import entidade.Cliente;
import entidade.Veiculo;
import java.awt.Dimension;
import javax.swing.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Formatter;
/**
 *
 * @author Usuário
 */
public class aplicacao {
    public static String t = "Tela Inicial";
    
    public static void main(String[] args) {
        String cpf = "";
        int index = 0;
        String nome = "";
        boolean sair = true;
        int op = 0;
        String placa = "";
        
        Cliente cliente = new Cliente();
        
        List<Cliente> clientelist = new ArrayList<>();
        List<OrdemDeServico> oslist = new ArrayList<>();
        List<Veiculo> veiculolist = new ArrayList<>();
        
        // Importa Tabela Cliente do MySql

        ClienteDao clienteDao = DaoFactory.criaClienteDao();
        clientelist = clienteDao.findAll();


        // Importa Tabela ordem_servico do MySql
        OrdemDeServicoDao ordemDeServicoDao = DaoFactory.criaOrdemDeServicoDao();
        oslist = ordemDeServicoDao.findAll();

        
        // Importa Lista Veiculo do MySql
        VeiculoDao veiculoDao = DaoFactory.criaVeiculoDao();
        veiculolist = veiculoDao.findAll();

        while(sair){
            op = menu();
            switch (op) {
                case 1 :
                    op = Integer.parseInt(JOptionPane
                            .showInputDialog(null
                                    ,"1 - Cliente\n 2 - Avulso\n", t
                                    , 3));
                    if(op == 1){
                        cpf = JOptionPane
                                .showInputDialog(null,
                                         "Insira o CPF: "
                                        , t
                                        , 3);
                        index = temCpf(clientelist,cpf);//retorna o indice
                        if (index == -1 ){break;}
                          
                        nome = clientelist.get(index).getNome();
                        OrdemDeServico os = new OrdemDeServico(nome,cpf);
                        
                        //Adciona veiculos à OS instanciada
                        for(Veiculo v : veiculolist){
                            os.addVeiculo(v);
                        }
                        
                        os.menu();
                        ordemDeServicoDao.insert(os);
                        clientelist.get(index).addOs(os);

                        //atualiza Banco de lista de OS da aplicação
                        
                        oslist.add(os);
                        
                        //Atualiza lista de veiculos da aplicacao
                        veiculolist = os.getVeiculolist();
                        break;
                    }
                        //Cliente Avulso
                        OrdemDeServico os = new OrdemDeServico();
                        
                        //Adciona veiculos à OS criada
                        for (Veiculo v : veiculolist) {
                        os.addVeiculo(v);
                         }
                        os.menu();
                        ordemDeServicoDao.insert(os);
                        //Atualiza lista de veiculos da aplicacao
                        veiculolist = os.getVeiculolist();
                     
                        //atualiza Banco de lista de OS na aplicação
                        oslist.add(os);
                        break;                  
                   
                case 2 :
                    // Retorna o novo cliente
                    cliente = cadCliente(clientelist);
                    //Atualiza lista de clientes da aplicação
                    clientelist.add(cliente);

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
                        veiculolist.add(cadVeiculo());//Implementar codigo para atualizar veiculos apos cadastro
                        
                        
                        break;
                    }
                    if (op == 2){
                        if (veiculolist.isEmpty()) {
                            JOptionPane.showMessageDialog(null,
                                    "Nenhum Veículo Cadastrado!");

                            break;}

                        
                        int i = temPlaca(veiculolist,veiculoDao);
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
                case 5 : 
                    // Atualiza lista
                    oslist = ordemDeServicoDao.findAll();
                    exibeOSList(oslist);
            }
        }
        DB.closeConnection();
    }
    public static int menu (){
        int op = 0;
        String menu = """
                      1 - Abrir Ordem de Servi\u00e7o
                      2 - Cadastrar Cliente
                      3 - Exibir Lista de Clientes
                      4 - Veículo
                      5 - Serviços Realizados
                      6 - Sair
                      """;
        op = Integer.parseInt(JOptionPane.showInputDialog(null
                , menu, t, 3));
        return op;
    } 
    public static Cliente cadCliente(List<Cliente>clientelist){
        ClienteDao clienteDao = DaoFactory.criaClienteDao();
        
         String nome , endereco, email;
         String t = "Novo Cliente";
         String nasc = "";
         String cpf_cnpj ;
         int cep = 0;
         long cel = 0;
         //Data de nascimento
         nome = JOptionPane.showInputDialog(null, "Nome: "
                 , t, 3);
         cpf_cnpj = JOptionPane.showInputDialog(null,
                 "CPF: ",
                t, 3);
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
             
         Cliente cliente = new Cliente(nome,endereco,nasc,cpf_cnpj,cep,cel,email);
        int rowsAffected = clienteDao
                .insert(cliente);
        if (rowsAffected > 0) {
            JOptionPane
                    .showMessageDialog(null,
                             "Cliente Cadastrado!");
        } else {
            JOptionPane
                    .showMessageDialog(null,
                            "Falha ao Cadastrar!");
        }

         return cliente;
    }
    public static void exibeClienteList(List<Cliente>clientelist){
        String lista = "";
        
        for(Cliente cliente : clientelist){
            
            lista = lista +  cliente.toString();
        }
        lista+= clientelist.size();
        JTextArea clientetxt = new JTextArea(lista);
        clientetxt.setLineWrap(true);
        clientetxt.setWrapStyleWord(true);
        clientetxt.setEditable(false);
        JScrollPane clientelistxt = new JScrollPane(clientetxt);
        clientelistxt.setPreferredSize(new Dimension(500, 500));
        
        JOptionPane.showMessageDialog(null, clientelistxt);

        

    }
    public static int temCpf(List<Cliente> clientelist,String cpf){
        int i = 0;
        for (i = 0; i < clientelist.size(); i++) {
            if (clientelist.get((int) i).getCpf_cnpj().equals(cpf)) {
                return i;
            }
        }
        return -1;
    }
    public static Veiculo cadVeiculo (){
        
        VeiculoDao veiculoDao = DaoFactory.criaVeiculoDao();
        String placa = "";
        Double kilometragem ;
        double consumo = 0;
        
        placa =  JOptionPane.showInputDialog(null
                , "Digite a Placa do Veiculo Abaixo:");
        kilometragem = Double.parseDouble(JOptionPane.showInputDialog(null
                , "Digite a Kilometragem Abaixo:"));
        consumo = Double.parseDouble(JOptionPane.showInputDialog(null
                , "Digite o Consumo de Combustivel do Veiculo Abaixo: (Km/L)"));
        Veiculo veiculo = new Veiculo(placa,kilometragem,consumo);
        int rowsAffected = veiculoDao.insert(veiculo);
        if (rowsAffected > 0) {
            JOptionPane
                    .showMessageDialog(null,
                            "Veiculo Cadastrado!");
        } else {
            JOptionPane
                    .showMessageDialog(null,
                            "Falha ao Cadastrar!");
        }
        return veiculo;
    }
    public static int temPlaca (List<Veiculo> veiculolist, VeiculoDao veiculoDao){
        String placa =  "";
        int i = 0;
        
        placa = JOptionPane.showInputDialog(
                "Nº da Placa: ");

        Veiculo veiculo = veiculoDao.findByPlaca(placa);
        if (veiculo != null){

        JTextArea veiculotxt = new JTextArea(veiculo.toString());
        veiculotxt.setLineWrap(true);
        veiculotxt.setWrapStyleWord(true);
        veiculotxt.setEditable(false);
        JScrollPane veiculoScroll = new JScrollPane(veiculotxt);
        veiculoScroll.setPreferredSize(new Dimension(200, 300));
        JOptionPane.showMessageDialog(null, veiculoScroll,
                "Veiculo", 3);
        
        for (Veiculo vv : veiculolist){
            if(vv.getPlaca().equalsIgnoreCase(placa)){
                return i;
            }
            i++;
        }
        }
        return -1;
    }
    public static void exibeVeiculoList(List<Veiculo>veiculolist){
        String lista = "";


        for (Veiculo veiculo : veiculolist) {
            lista += veiculo.toString();
        }
        JTextArea veiculotxt = new JTextArea(lista);
        veiculotxt.setLineWrap(true);
        veiculotxt.setWrapStyleWord(true);
        veiculotxt.setEditable(false);
        JScrollPane veiculolistxt = new JScrollPane(veiculotxt);
        veiculolistxt.setPreferredSize(new Dimension(300, 300));

        JOptionPane.showMessageDialog(null, veiculolistxt);
    }
    public static void exibeOSList (List<OrdemDeServico>oslist){
        String lista = "";

        for (OrdemDeServico os : oslist) {
            lista += os.toString();
        }
        JTextArea ostxt = new JTextArea(lista);
        ostxt.setLineWrap(true);
        ostxt.setWrapStyleWord(true);
        ostxt.setEditable(false);
        JScrollPane oslistxt = new JScrollPane(ostxt);
        oslistxt.setPreferredSize(new Dimension(300, 300));

        JOptionPane.showMessageDialog(null, oslistxt);
    
    }
    
}