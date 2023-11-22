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
        boolean sair = true;
        int op = 0;
        String placa = "";
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
                        int index = temCpf(clientelist,cpf);//retorna o indice
                        if (index == -1 ){break;}
                        //Instancia OS passando lista de veiculos como argumento
                        OrdemDeServico os = new OrdemDeServico(veiculolist,cpf);
                        os.menu();
                        //Vamos manipular a lista de OS do cliente
                        List<OrdemDeServico> clienteoslist = new ArrayList<>();
                        /** Lembrando que o indice do cliente agora esta na 
                         * variavel cpf.
                         * Vamos puxar a lista de OS do cliente.
                         */
                        clienteoslist=clientelist.get(index).getOslist();
                        
                        //atualiza lista de os
                        clienteoslist.add(os);
                        
                        //atualiza Banco de lista de OS
                        // Agora é realizado dentro do objeto direto p/ BD
                        //oslist.add(os);
                        
                        //Atualiza lista de veiculos
                        veiculolist = os.getVeiculolist();
                        
                        //Atualiza lista de OS do cliente
                        clientelist.get(index).setOslist(clienteoslist);
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
                    /**if (veiculolist.isEmpty()){
                        JOptionPane.showMessageDialog(null
                                ,"Nenhum Veículo Cadastrado!");
                        break;
                    }**/
                    

                    exibeVeiculoList(veiculolist);

                    //consultaVeiculo();
                    break;
                    
                    
                case 5 : 
                    
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
         clientelist.add(cliente); // Atualiza Lista de clientes
         
         Connection conn = null;
         PreparedStatement st = null;
         try {
            conn = DB.getConnection();
            st = conn.prepareStatement(
                    "INSERT INTO cliente"
                    + "(nome,endereco,data_nasc,cpf_cnpj,cep,cel,email)"
                    + "VALUES"
                    + "(?,?,?,?,?,?,?)");
            st.setString(1, nome);
            st.setString(2, endereco);
            st.setString(3, nasc);
            
            String string_cpf_cnpj = "";
            string_cpf_cnpj = string_cpf_cnpj + cpf_cnpj;
            st.setString(4, string_cpf_cnpj); 
            
            st.setLong(5, cep);
            st.setLong(6, cel);
            st.setString(7, email);

            int rowsAffected = st.executeUpdate();
         } catch (SQLException e) {
            e.printStackTrace();
        }
         finally{
        DB.closeStatement(st);  
         }
         return clientelist;
    }
    public static void exibeClienteList(List<Cliente>clientelist){
        String lista = "";
        
        for(Cliente cliente : clientelist){
            
            lista = lista +  cliente.toString();
        }
        
        JTextArea clientetxt = new JTextArea(lista);
        clientetxt.setLineWrap(true);
        clientetxt.setWrapStyleWord(true);
        clientetxt.setEditable(false);
        JScrollPane clientelistxt = new JScrollPane(clientetxt);
        clientelistxt.setPreferredSize(new Dimension(300, 300));
        
        JOptionPane.showMessageDialog(null, clientelistxt);

        

    }
    public static int temCpf(List<Cliente> clientelist,String cpf){
        int i = 0;
        for (i = 0; i < clientelist.size(); i++) {
            if (clientelist.get((int) i).equals(cpf)) {
                return i;
            }
        }
        return -1;
    }
    public static Veiculo cadVeiculo (){
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
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = DB.getConnection();
            st = conn.prepareStatement(
                    "INSERT INTO veiculo"
                    + "(placa,kilometragem,consumo)"
                    + "VALUES"
                    + "(?,?,?)");
            st.setString(1, placa);
            st.setDouble(2, kilometragem);
            st.setDouble(3, consumo);

            int rowsAffected = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DB.closeConnection();
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