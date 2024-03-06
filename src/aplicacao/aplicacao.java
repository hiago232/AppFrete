/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package aplicacao;
import DAO.DaoFactory;
import DAO.OrdemDeServicoDao;
import DAO.VeiculoDao;
import Db.DB;
import entidade.OrdemDeServico;
import entidade.Veiculo;
import java.awt.Dimension;
import javax.swing.*;
import java.util.List;
import java.util.ArrayList;
import DAO.ClienteFisicoDao;
import DAO.ClienteJuridicoDao;
import entidade.ClienteFisico;
import entidade.ClienteJuridico;
/**
 *
 * @author Usuário
 */
public class aplicacao {
    public static String t = "Tela Inicial";
    
    public static void main(String[] args)    {
        String cpf_cnpj = "";
        String cpf = "";
        String cnpj = "";
        int index = 0;
        String nome = "";
        boolean sair = true;
        int op = 0;
        String placa = "";
        
        ClienteFisico clienteFisico = new ClienteFisico();
        ClienteJuridico clienteJuridico = new ClienteJuridico();
        
        List<ClienteFisico> clienteFisicoList = new ArrayList<>();
        List<ClienteJuridico> clienteJuridicoList = new ArrayList<>();
        List<OrdemDeServico> oslist = new ArrayList<>();
        List<Veiculo> veiculolist = new ArrayList<>();
        
        // Importa  Clientes do MySql

        ClienteFisicoDao clienteFisicoDao = DaoFactory.criaClienteFisicoDao();
        ClienteJuridicoDao clienteJuridicoDao = DaoFactory.criaClienteJuridicoDao();
        clienteFisicoList = clienteFisicoDao.findAll();
        clienteJuridicoList = clienteJuridicoDao.findAll();


        // Importa Tabela ordem_servico do MySql
        OrdemDeServicoDao ordemDeServicoDao = DaoFactory.criaOrdemDeServicoDao();
        oslist = ordemDeServicoDao.findAll();

        
        // Importa Lista Veiculo do MySql
        VeiculoDao veiculoDao = DaoFactory.criaVeiculoDao();
        veiculolist = veiculoDao.findAll();

        while(sair){
            op = menu();
            switch (op) {
                case 1:
                    op = Integer.parseInt(JOptionPane
                        .showInputDialog(null, """
                                               1 - Nova OS
                                                2 - Servi\u00e7os Finalizados
                                                3 - Voltar""", t,
                                 3));
                    switch(op){
                        case 1:
                            op = Integer.parseInt(JOptionPane
                                .showInputDialog(null, """
                                                       1 - Cliente Fisico
                                                       2 - Cliente Jur\u00eddico
                                                       3 - Avulso
                                                       """, t,
                                         3));
                            if (op == 1) {

                                do{
                                cpf = JOptionPane
                                        .showInputDialog(null,
                                                "Insira o CPF: ",
                                                 t,
                                                 3);
                                sair = false;
                                try{
                                Long cpfl = Long.parseLong(cpf);
                                }catch(Exception e){
                                    JOptionPane
                                        .showMessageDialog(null,
                                                "Dado inválido1");
                                    sair = true;
                                }
                                
                                if (cpf.length() < 10) {
                                    sair = true;
                                } else if (cpf.length() > 11) {
                                    sair = true;
                                 }
                                }while(sair);
                                

                                sair = true;
                                
                                index = temCpf(clienteFisicoList, cpf);//retorna o indice
                                if (index == -1) {
                                    JOptionPane.
                                        showMessageDialog(null,
                                                "CPF não encontrado");
                                    break;
                                }

                                nome = clienteFisicoList.get(index).getNome();
                                OrdemDeServico os = new OrdemDeServico(nome, 
                                        cpf);

                                //Adciona veiculos à OS instanciada
                                for (Veiculo v : veiculolist) {
                                    os.addVeiculo(v);
                                }

                                os.menu();
                                ordemDeServicoDao.insert(os);
                                clienteFisicoList.get(index).addOs(os);

                                //atualiza Banco de lista de OS da aplicação
                                oslist.add(os);

                                //Atualiza lista de veiculos da aplicacao
                                veiculolist = os.getVeiculolist();
                                break;
                            }
                            if (op == 2) {
                                cnpj = JOptionPane
                                        .showInputDialog(null,
                                                "Insira o CNPJ: ",
                                                 t,
                                                 3);
                                index = temCnpj(clienteJuridicoList, cnpj);//retorna o indice
                                if (index == -1) {
                                    break;
                                }

                                nome = clienteJuridicoList.get(index).getResponsavel();
                                OrdemDeServico os = new OrdemDeServico(nome, cnpj);

                                //Adciona veiculos à OS instanciada
                                for (Veiculo v : veiculolist) {
                                    os.addVeiculo(v);
                                }

                                os.menu();
                                ordemDeServicoDao.insert(os);
                                clienteFisicoList.get(index).addOs(os);

                                //atualiza Banco de lista de OS da aplicação
                                oslist.add(os);

                                //Atualiza lista de veiculos da aplicacao
                                veiculolist = os.getVeiculolist();
                                break;
                            }
                            //Cliente Avulso
                            nome = JOptionPane.showInputDialog(null,
                                    "Nome: ") + "(AVULSO)";
                            cpf_cnpj = JOptionPane.showInputDialog(null,
                                    "CPF/CNPJ: ");
                            OrdemDeServico os = new OrdemDeServico(nome,cpf_cnpj);

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
    
                        case 2:
                            // Atualiza lista
                            oslist = ordemDeServicoDao.findAll();
                            exibeOSList(oslist);
                            break;
                            
                       default:
                            break;
                                }
                        
                    break;
     
                case 2 :
                    op = Integer.parseInt(JOptionPane
                            .showInputDialog(null
                                    , "1.Cadastrar\n2.Alterar\n"
                                    +"3.Exibir Lista\n4.Voltar"));
                    switch (op){                                                            
                        case 1 :
                            op = Integer.parseInt(JOptionPane
                                    .showInputDialog(null,
                                             "1.Cliente Fisico\n"
                                                     + "2.Cliente Jurídico\n"));
                            switch (op){
                                case 1:
                                    // Retorna o novo cliente
                                    clienteFisico =
                                        cadClienteFisico(clienteFisicoList);
                                    //Atualiza lista de clientes da aplicação
                                    clienteFisicoList.add(clienteFisico); 
                                    break;
                                case 2:
                                    // Retorna o novo cliente
                                    clienteJuridico = 
                                        cadClienteJuridico(clienteJuridicoList);
                                    //Atualiza lista de clientes da aplicação
                                    clienteJuridicoList.add(clienteJuridico); 
                                    break;
                            }                  
                         break;
                        case 3:
                            exibeClienteList(clienteFisicoList,clienteJuridicoList);
                            
                            break;
                        default:
                            break;
                        }     
                    break;
                case 3 : 
                    op = Integer.parseInt(JOptionPane.showInputDialog(null
                            , " 1 - Cadastrar\n "
                                    + "2 - Alterar Dados\n "
                                    + "3 - Exibir Lista de Veículos\n"
                                    + " 4 - Voltar"
                            , t, 3));
                    switch(op){
                        case 1:
                            
                        veiculolist.add(cadVeiculo());
                        //Implementar codigo para atualizar veiculos no BD apos cadastro
                        
                        break;
                        
                        case 2:
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
                        case 3:
                        exibeVeiculoList(veiculolist);
                            break;
                        default: break;

            }
        }
        
    }
        DB.closeConnection();
     }
    public static int menu (){
        int op = 0;
        String menu = """
                      1 - Ordem de Serviço
                      2 - Cliente
                      3 - Veículo
                      """;
        op = Integer.parseInt(JOptionPane.showInputDialog(null
                , menu, t, 3));
        return op;
    } 
    public static ClienteFisico cadClienteFisico(List<ClienteFisico>clienteFisicoList){
        ClienteFisicoDao clienteFisicoDao = DaoFactory.criaClienteFisicoDao();
        
         String nome = "";
         String rua = "";
         String bairro = "";
         String cidade = "";
         String estado = "";
         String email = "";
         String t = "Novo Cliente";
         String nasc = "";
         String cpf ="";
         long cel = 0;
         long cep = 0;
         int numero = 0;
         int sair = 1;
        

         nome = JOptionPane.showInputDialog(null, "Nome: "
                 , t, 3);

         do {

         cpf = JOptionPane.showInputDialog(null,
                 "CPF: ",
                t, 3);
         
         sair = 0; 
         if (cpf.length() <10){
             sair = 1;
             JOptionPane.showMessageDialog(null, "Dado inválido1");
         }else if (cpf.length()>11){            
             sair = 1;
             JOptionPane.showMessageDialog(null, "Dado inválido");
}

        } while (sair == 1);


         do {
             try{
         nasc = JOptionPane.showInputDialog(null,
                 "Data de Nascimento: ",
                t, 3);         
            } catch (Exception e) {
                sair = 1;
                JOptionPane.showMessageDialog(null, "Dado inválido");
            }
        } while (sair == 1);

         
         do {
             try{
         email = JOptionPane.showInputDialog(null
                 , "E-mail: ",
                 t, 3);
            } catch (Exception e) {
                sair = 1;
                JOptionPane.showMessageDialog(null, "Dado inválido");
            }
        } while (sair == 1);


         do {
             try{
         cel= Long.parseLong(JOptionPane.showInputDialog(null
                 , "Cel: ",
                 t, 3));
                 sair = 0;
            } catch (Exception e) {
                sair = 1;
                JOptionPane.showMessageDialog(null, "Dado inválido");
            }
        } while (sair == 1);

         do {
             try{
         cep = Long.parseLong(JOptionPane.showInputDialog(null
                 , "CEP: ",
                 t, 3));
                 sair = 0;
            } catch (Exception e) {
                sair = 1;
                JOptionPane.showMessageDialog(null, "Dado inválido");
            }
        } while (sair == 1);

         do {
             try{
         numero = Integer.parseInt(JOptionPane.showInputDialog(null
                 , "Número: ",
                 t, 3));
                 sair = 0;
            } catch (Exception e) {
                sair = 1;
                JOptionPane.showMessageDialog(null, "Dado inválido");
            }
        } while (sair == 1);

             
         do {
             try{
         rua = JOptionPane.showInputDialog(null
                 , "Rua: ",
                 t, 3);
            } catch (Exception e) {
                sair = 1;
                JOptionPane.showMessageDialog(null, "Dado inválido");
            }
        } while (sair == 1);

         
         
         do {
             try{
         bairro = JOptionPane.showInputDialog(null
                 , "Bairro: ",
                 t, 3);
            } catch (Exception e) {
                sair = 1;
                JOptionPane.showMessageDialog(null, "Dado inválido");
            }
        } while (sair == 1);

         
         
         do {
             try{
         cidade = JOptionPane.showInputDialog(null
                 , "Cidade: ",
                 t, 3);
            } catch (Exception e) {
                sair = 1;
                JOptionPane.showMessageDialog(null, "Dado inválido");
            }
        } while (sair == 1);

         
         do {
             try{

         estado = JOptionPane.showInputDialog(null
                 , "Estado: ",
                 t, 3);
         sair = 0;
             }
         catch(Exception e){
             sair = 1;
             JOptionPane.showMessageDialog(null, "Dado inválido");
                 }
         } while (sair == 1);
         ClienteFisico clienteFisico = new ClienteFisico(cpf,nome,nasc,email,cel,cep,
                 rua,numero,bairro,cidade,estado);

        int rowsAffected = clienteFisicoDao.insert(clienteFisico);
        if (rowsAffected > 0) {
            JOptionPane
                    .showMessageDialog(null,
                             "Cliente Cadastrado!");
        } else {
            JOptionPane
                    .showMessageDialog(null,
                            "Falha ao Cadastrar! Verifique os dados inseridos");
        }

         return clienteFisico;
    }
    public static ClienteJuridico cadClienteJuridico(List<ClienteJuridico>clienteJuridicoList){
        ClienteJuridicoDao clienteJuridicoDao = DaoFactory.criaClienteJuridicoDao();
        
         String responsavel,razaoSocial,nomeFantasia,rua,bairro,cidade,estado, email;
         String t = "Novo Cliente";
         String cnpj;
         long cel = 0;
         long cep = 0;
         int numero = 0;
         //Data de nascimento
         cnpj = JOptionPane.showInputDialog(null,
                 "CNPJ: ",
                t, 3);
         responsavel = JOptionPane.showInputDialog(null, "Responsavel: "
                 , t, 3);
         razaoSocial = JOptionPane.showInputDialog(null,
                 "Razao Social: ",
                t, 3);         
         nomeFantasia = JOptionPane.showInputDialog(null,
                 "Nome Fantasia: ",
                t, 3);         
         
         email = JOptionPane.showInputDialog(null
                 , "E-mail: ",
                 t, 3);

         cel= Long.parseLong(JOptionPane.showInputDialog(null
                 , "Cel: ",
                 t, 3));
         cep = Integer.parseInt(JOptionPane.showInputDialog(null
                 , "CEP: ",
                 t, 3));
         numero = Integer.parseInt(JOptionPane.showInputDialog(null
                 , "Número: ",
                 t, 3));
             
         rua = JOptionPane.showInputDialog(null
                 , "Rua: ",
                 t, 3);
         
         
         bairro = JOptionPane.showInputDialog(null
                 , "Bairro: ",
                 t, 3);
         
         
         cidade = JOptionPane.showInputDialog(null
                 , "Cidade: ",
                 t, 3);
         
         
         estado = JOptionPane.showInputDialog(null
                 , "Estado: ",
                 t, 3);
         
         ClienteJuridico clienteJuridico = new ClienteJuridico(cnpj,responsavel,
         razaoSocial,nomeFantasia,email,cel,cep,rua,numero,bairro,cidade,estado);
        int rowsAffected = clienteJuridicoDao.insert(clienteJuridico);
        if (rowsAffected > 0) {
            JOptionPane
                    .showMessageDialog(null,
                             "Cliente Cadastrado!");
        } else {
            JOptionPane
                    .showMessageDialog(null,
                            "Falha ao Cadastrar!");
        }

         return clienteJuridico;
    }    
    public static void exibeClienteList(List<ClienteFisico>clienteFisicoList,List<ClienteJuridico>clienteJuridicoList){
        String lista = "";
        
        for(ClienteFisico clienteFisico : clienteFisicoList){
            
            lista = lista +  clienteFisico.toString();
        }
        lista += clienteFisicoList.size();
        for(ClienteJuridico clienteJuridico : clienteJuridicoList){
            
            lista = lista +  clienteJuridico.toString();
        }
        lista+= clienteJuridicoList.size();
        JTextArea clientetxt = new JTextArea(lista);
        clientetxt.setLineWrap(true);
        clientetxt.setWrapStyleWord(true);
        clientetxt.setEditable(false);
        JScrollPane clientelistxt = new JScrollPane(clientetxt);
        clientelistxt.setPreferredSize(new Dimension(500, 500));
        
        JOptionPane.showMessageDialog(null, clientelistxt);

        

    }
    public static int temCpf(List<ClienteFisico> clienteFisicoList,String cpf){
        int i = 0;
        for (i = 0; i <  clienteFisicoList.size(); i++) {
            if ( clienteFisicoList.get((int) i).getCpf().equals(cpf)) {
                return i;
            }
        }
        return -1;
    }
    public static int temCnpj(List<ClienteJuridico> clienteJuridicoList,String cpf_cnpj){
        int i = 0;
        for (i = 0; i <  clienteJuridicoList.size(); i++) {
            if ( clienteJuridicoList.get((int) i).getCnpj().equals(cpf_cnpj)) {
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