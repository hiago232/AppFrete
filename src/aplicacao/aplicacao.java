/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package aplicacao;
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
                        if (cpf == -1 ){break;}
                        //Instancia OS passando lista de veiculos como argumento
                        OrdemDeServico os = new OrdemDeServico(veiculolist);
                        os.menu();
                        //Vamos manipular a lista de OS do cliente
                        List<OrdemDeServico> clienteoslist = new ArrayList<>();
                        /** Lembrando que o indice do cliente agora esta na 
                         * variavel cpf.
                         * Vamos puxar a lista de OS do cliente.
                         */
                        clienteoslist=clientelist.get((int)cpf).getOslist();
                        
                        //atualiza lista de os
                        clienteoslist.add(os);
                        
                        //atualiza Banco de lista de OS
                        oslist.add(os);
                        
                        //Atualiza lista de veiculos
                        veiculolist = os.veiculolist;
                        
                        //Atualiza lista de OS do cliente
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
                    // exibeClienteList(clientelist);
                    consultaCliente();
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
                    /**if (veiculolist.isEmpty()){
                        JOptionPane.showMessageDialog(null
                                ,"Nenhum Veículo Cadastrado!");
                        break;
                    }**/
                    //exibeVeiculoList(veiculolist);
                    consultaVeiculo();
                    break;

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
         Long cpf_cnpj ;
         int cep = 0;
         long cel = 0;
         //Data de nascimento
         nome = JOptionPane.showInputDialog(null, "Nome: "
                 , t, 3);
         cpf_cnpj = Long.parseLong(JOptionPane.showInputDialog(null,
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
        int i = 0;
        for(Cliente cliente : clientelist){
            
            lista = lista +"Id: "+i+"  "+cliente.getNome()+" Idade: "+cliente.idade+" "
                    +"Ordens de Serviços: "
                    +cliente.getOslist().size()+"\n";
            i++;
        }
        JOptionPane.showMessageDialog(null, lista);
    }
    public static void consultaCliente(){
        String tabela = "";
        String cpf_cnpj = "";
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            conn = DB.getConnection();

            st = conn.createStatement();
            

            rs = st.executeQuery("select * from cliente");
            while (rs.next()) {
                if (rs.getString("cpf_cnpj").length() < 11){
                    cpf_cnpj = "0"+rs.getString("cpf_cnpj");
                }
                tabela = tabela + "NOME: " + rs.getString("nome") + "\n"
                        + "CPF/CNPJ: " + cpf_cnpj + "\n"
                        + "E-MAIL: " + rs.getString("email") + "\n"
                        + "CEP: " + rs.getLong("cep") + "\n"
                        + "NASCIMENTO: " + (String)rs.getString("data_nasc") + "\n"
                        + "ENDEREÇO: " + rs.getString("endereco") + "\n"
                        + "CELULAR: " + rs.getLong("cel") + "\n"+"\n";
            } 
            
            JTextArea clientestxt = new JTextArea(tabela);
                clientestxt.setLineWrap(true);
                clientestxt.setWrapStyleWord(true);
                clientestxt.setEditable(false);
            JScrollPane listclientes = new JScrollPane(clientestxt);
                listclientes.setPreferredSize(new Dimension(500, 500));
            JOptionPane.showMessageDialog(null, listclientes,
                    "Lista de Clientes", 3);
        }catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }
    public static long temCpf(List<Cliente> clientelist,long cpf){
        long i;
        for (i = 0; i < clientelist.size(); i++) {
            if (clientelist.get((int) i).getCpf_cnpj() == cpf) {
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
    public static void consultaVeiculo(){
        String tabela = "";
        Connection conn = null;
        Statement st = null;
        ResultSet rs  = null;
        try {
            conn = DB.getConnection();
            
            st = conn.createStatement();
            
            rs = st.executeQuery("select * from veiculo");
            while (rs.next()){
                tabela = tabela+"Placa: "+rs.getString("placa")+"\n"
                        +"Kilometragem: "+rs.getDouble("kilometragem")+"\n"
                        +"Consumo: "+rs.getDouble("consumo")+"\n"+"\n";
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        JTextArea veiculotxt = new JTextArea(tabela);
        veiculotxt.setLineWrap(true);
        veiculotxt.setWrapStyleWord(true);
        veiculotxt.setEditable(false);
        JScrollPane listveiculo = new JScrollPane(veiculotxt);
        listveiculo.setPreferredSize(new Dimension(300, 300));
        JOptionPane.showMessageDialog(null, listveiculo);
    }
        
   
}