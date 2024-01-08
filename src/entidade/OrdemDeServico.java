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
import java.awt.Dimension;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import util.CalcTempo;
import util.CalcCombustivel;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Objects;

/**
 *
 * @author Usuário
 */
public class OrdemDeServico implements Serializable{
    private String cpf_cnpj= "";
    private String nome = "";
    private String placa = "";
    private String vfcombust = "";
    private String t = "|Ordem de Serviço|";
    private String ti = "";
    private String tf = "";
    private String duracao = "00:00";
    private String listaItens ="";
    private String moveis = "";
    private String inicio = "";
    private String destino = "";
    private Integer id;
    private int veicindex = 0;
    private double vhorareal = 0;
    private double valorhora = 0;
    private double distancia = 1;
    private double valortotal = 0;
    private double kmlitro = 0; // quanto o caminhao roda por litro
    private double preco = 0; // preço do combustivel por litro no posto
    private double vcombust = 0.0;// valor do combustivel consumido na viagem
    private List<Veiculo>veiculolist = new ArrayList<>();
    private Veiculo veiculo = new Veiculo();
    
    // Construtor padrão
    public OrdemDeServico(){};
    
    public OrdemDeServico(String nome, String cpf_cnpj){
       
        this.cpf_cnpj = cpf_cnpj;
        this.nome = nome;
    };

    public String getCpf_cnpj() {
        return cpf_cnpj;
    }

    public void setCpf_cnpj(String cpf_cnpj) {
        this.cpf_cnpj = cpf_cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public List<Veiculo> getVeiculolist() {
        return veiculolist;
    }

    public void setVeiculolist(List<Veiculo> veiculolist) {
        this.veiculolist = veiculolist;
    }
    

    public String getVfcombust() {
        return vfcombust;
    }

    public void setVfcombust(String vfcombust) {
        this.vfcombust = vfcombust;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public String getListaItens() {
        return listaItens;
    }

    public void setListaItens(String listaItens) {
        this.listaItens = listaItens;
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public double getValorhora() {
        return valorhora;
    }

    public void setValorhora(double valorhora) {
        this.valorhora = valorhora;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public double getValortotal() {
        return valortotal;
    }

    public void setValortotal(double valortotal) {
        this.valortotal = valortotal;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OrdemDeServico other = (OrdemDeServico) obj;
        return Objects.equals(this.id, other.id);
    }
    
    
    
    //métodos
    public void menu(){
     
        // Declaração de variáveis metodo menu
        List<String> itens = new ArrayList<>(); // Lista de itens da mudança
        CalcTempo calctemp = new CalcTempo();
        CalcCombustivel conscombust = new CalcCombustivel();
        DecimalFormat formatador = new DecimalFormat("#.###");
      
        if (nome.equals("")) {
            nome = "avulso";
        }
        
        boolean sair = true;
        int op = 0;
    
        while (sair){
            // Atribui o valor total a cada repetição
            valortotal = vcombust + valorhora; 
            String osmenu = "Cliente: "+nome.toUpperCase()+"\n"
                +"1 - Add item\n"
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
                                //Atualiza valor do consumo conforme a distancia
                                vcombust = distancia * conscombust.custoKm
                                        (preco, kmlitro);
                                vfcombust = formatador.format(vcombust);
                                break;
                            case 2 : // Valor consumo
                                veicindex = consumoVeiculoList();
                                 
                                 veiculo  = veiculolist.get(veicindex);
                                 kmlitro = veiculo.getConsumo();
                                 placa = veiculo.getPlaca();
                                         
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
                    
                   
                    Double kilometragem = (long)distancia + veiculolist
                            .get(veicindex)
                            .getKilometragem();
                    
                    // Atualiza a kilometragem do veiculo utilizado no serviço
                    veiculolist.get(veicindex)
                            .setKilometragem(kilometragem) ;
                    
                    
                    //Reorganiza listaItens com ',' 
                    listaItens = "";
                    for (String item : itens) {
                        listaItens = listaItens + item + ",";
                    }
                    sair = false;
                    break;
                default :
                    break;
            }
        }

        
        

        
    }
    public void addVeiculo(Veiculo veic){
        veiculolist.add(veic);
        
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
            lista = lista + "###"+i +"###\n"+ veic.toString();
            i++;
        }
        lista = "Digite o nº do veiculo desejado: \n\n" + lista;
        JTextArea veiculotxt = new JTextArea(lista);
        veiculotxt.setLineWrap(true);
        veiculotxt.setWrapStyleWord(true);
        veiculotxt.setEditable(false);
        JScrollPane veiculolistxt = new JScrollPane(veiculotxt);
        veiculolistxt.setPreferredSize(new Dimension(300, 300));

        
        op = Integer.parseInt(JOptionPane.showInputDialog(null
                , veiculolistxt));
        
        return op;
    }
    public String toString(){
        return 
        "Nome: "+ nome+"\n"
        +"CPF\"CNPJ: "+cpf_cnpj+"\n"
        +"Lista de Itens: "+listaItens+"\n"
        +"Inicio: "+inicio+"\n"
        +"Destino: "+destino+"\n"
        +"Placa: "+placa + "\n"        
        +"Distância: "+ String.format("%.2f", distancia)+"Km"+"\n"
        +"Duração: "+duracao+"\n"
        +"Valor Hora: "+String.format("%.2f",valorhora)+"\n"
        +"Valor Combustível: " + vfcombust + "\n"
        +"Valor Total: "+String.format("%.2f",valortotal)+"\n"
        +"----------------" + "\n"        ;
    }
}
