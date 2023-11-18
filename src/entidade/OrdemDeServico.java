/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidade;
import Db.DB;
import javax.swing.*;
import java.util.List;
import java.util.ArrayList;
import entidade.Veiculo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import util.CalcTempo;
import util.CalcCombustivel;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 *
 * @author Usuário
 */
public class OrdemDeServico {
    public String vfcombust = "";
    public String t = "Ordem de Serviço";
    public String ti = "";
    public String tf = "";
    public String duracao = "00:00";
    public String listaItens ="";
    public String moveis = "";
    public String inicio = "";
    public String destino = "";
    public int veicindex = 0;
    public double vhorareal = 0;
    public double valorhora = 0;
    public double distancia = 0;
    public double valortotal = 0;
    public double kmlitro = 0; // quanto o caminhao roda por litro
    public double preco = 0; // preço do combustivel por litro no posto
    public double vcombust = 0.0;// valor do combustivel consumido na viagem
    public List<Veiculo>veiculolist;
    // Construtor padrão
    public OrdemDeServico(){};
    public OrdemDeServico(List<Veiculo>veiculolist){
        this.veiculolist = veiculolist;
    };
    
    //Procedimento
    public void menu(){
     
        // Declaração de variáveis metodo menu
        List<String> itens = new ArrayList<>(); // Lista de itens da mudança
        CalcTempo calctemp = new CalcTempo();
        CalcCombustivel conscombust = new CalcCombustivel();
        DecimalFormat formatador = new DecimalFormat("#.###");
      
       
        
        boolean sair = true;
        int op = 0;
    
        while (sair){
            // Atribui o valor total a cada repetição
            valortotal = 10.00;// vcombust + valorhora; 
            String osmenu = "1 - Add item\n"
                +"2 - Exibir lista\n"
                +"3 - Local inicial:   "+inicio+"\n"
                +"4 - Destino:    "+destino+"\n"
                +"5 - Distância:   "+distancia+" KM\n"
                +"6 - Combustivel:  R$"+vfcombust+"\n"
                +"7 - Tempo de serviço:   "+duracao+"  R$"+String
                        .format("%.2f",valorhora)+"\n"
                +"8 - Valor total R$"+String
                        .format("%.2f", valortotal)+"\n"
                +"9 - Fechar OS\n";
            
            
            String tempmenu="1 - Calcular valor/h\n"
                    +"2 - Calcular duração\n"
                    +"3 - Voltar\n";
 
            
            String combustmenu = "1 - Preço\n"
                    +"2 - Consumo de combustivel Km/L\n"
                    +"3 - Voltar";
 
            
            op = Integer.parseInt(JOptionPane.
                    showInputDialog(null,
                    osmenu,
                    t,
                    JOptionPane.QUESTION_MESSAGE));
            
            switch (op) {
                case 1 : 
                    itens = addItem(itens);
                    break;
                case 2 :
                    listaItens = "";
                    for (String item : itens) {
                        listaItens = listaItens  + item+ "\n";
                    }
                    JOptionPane.showMessageDialog(null
                            , listaItens,t,1);
                    break ;
                case 3 : 
                    System.out.println(); 
                    inicio = JOptionPane.showInputDialog(null,
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
                    //Atualiza valor do consumo conforme a distancia
                    vcombust = distancia * conscombust.custoKm(preco, kmlitro);
                    vfcombust = formatador.format(vcombust);
                    break;
                case 6 : 
                    while(sair){
                        op = Integer.parseInt(JOptionPane
                                .showInputDialog(null,
                                combustmenu, t, 3));
                        switch (op){
                            case 1 :// Preço
                                preco = Double.parseDouble(JOptionPane
                                        .showInputDialog(null
                                        ,"valor do combustivel por litro"
                                        ,t, 3));
                                break;
                            case 2 : // Valor consumo
                                veicindex = consumoVeiculoList();
                                 kmlitro = veiculolist.get(veicindex)
                                         .consumo;
                                 
                                // retorna valor por kilometro do consumo 
                                vcombust = distancia * conscombust.custoKm(preco
                                        ,kmlitro);
                                vfcombust = formatador.format(vcombust);
                                break;
                            case 3 :// Voltar
                                sair = false;
                                break;
                        }
                    }
                    sair = true;
                    break;
                case 7 :
                    while (sair){
                    op = Integer.parseInt(JOptionPane.showInputDialog(null,
                             tempmenu, t, 3));
                    switch(op){
                        case 1 : // Calcular valor/h
                            vhorareal = Double.parseDouble(JOptionPane
                                    .showInputDialog(null,
                                    "Insira o valor/h abaixo: "
                                    , t, 3));
                            
                            break;
                        case 2 :// Calcular duração 
                            ti = JOptionPane.showInputDialog(null,
                                     "Hora inicial: (HH:mm)"
                                    , t, 3);
                            tf = JOptionPane.showInputDialog(null,
                                     "Hora final: (HH:mm)"
                                    , t, 3);
                            duracao = calctemp.calcDuracao(ti, tf);
                            valorhora = calctemp
                                    .calcValorHora(vhorareal);
                            break;
                        case 3 : // voltar ao osmenu
                            sair = false;
                            break;
                    }
                    }
                    sair = true;
                    //Tempo();
                    break;
                case 8 :
                    
                    //valorTotal();
                    break;
                case 9 :
                    
                    /**
                    Double kilometragem = (long)distancia + veiculolist.get(veicindex)
                            .kilometragem;
                    
                    // Atualiza a kilometragem do veiculo utilizado no serviço
                    veiculolist.get(veicindex).kilometragem = kilometragem;
                    **/
                    
                    //Reorganiza listaItens com ',' 
                    listaItens = "";
                    for (String item : itens) {
                        listaItens = listaItens + item + ",";
                    }
                    
                    Connection conn = null;
                    PreparedStatement st = null;
                    try {
                        conn = DB.getConnection();
                        st = conn.prepareStatement(
                                "INSERT INTO ordem_servico"
                                + "(lista_itens,inicio,destino,distancia"
                                        + ",combustivel$,tempo_servico"
                                        +",valor_hora,valor_total)"
                                + "VALUES"
                                + "(?,?,?,?,?,?,?,?)");
                        st.setString(1, listaItens);
                        st.setString(2, inicio);
                        st.setString(3, destino);
                        st.setDouble(4, distancia);
                        st.setString(5, vfcombust);
                        st.setString(6, duracao);
                        st.setDouble(7, valorhora);
                        st.setDouble(8, valortotal);

                        int rowsAffected = st.executeUpdate();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    
                    
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
    public int consumoVeiculoList() {
        String lista = "";
        int i = 0;
        int op = 0; 
       

        for (Veiculo veic : veiculolist) {
            lista = lista + i + " Placa: " + veic.placa + " " + veic.consumo + " Km/L";
            i++;
        }
        lista = "Digite o nº do veiculo desejado: \n" + lista;
        op = Integer.parseInt(JOptionPane.showInputDialog(null
                , lista));
        
        return op;
    }
    
}