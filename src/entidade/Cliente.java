/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidade;
import javax.swing.*;
import java.util.List;
import java.util.ArrayList;
import entidade.OrdemDeServico;
import java.io.Serializable;
import java.util.Objects;
import util.CalcTempo;

/**
 *
 * @author Usuário
 */
public class Cliente implements Serializable {
    // Declaração de variáveis
    public String t = "Cliente";
    private String nome,endereco,email,nasc;
    public int idade = 0;
    private String cpf_cnpj= "";
    private long cep = 0;
    private long cel = 0;
    boolean sair = true;
    private List <OrdemDeServico> oslist; // lista de ordens de serviço
    
    private String menu =  "1 - Abrir ordem de serviço";
     
    
    
    //Contrutores
    public Cliente (){
        List<OrdemDeServico> osl = new ArrayList<>();
        this.oslist = osl;
    }
    
    public Cliente(String nome) {
        List<OrdemDeServico> osl = new ArrayList<>();
        this.oslist = osl;
        this.nome = nome;
    }
    
    public Cliente(String nome, String endereco,String nasc,String cpf_cnpj
            ,long cep,long cel) {
        List<OrdemDeServico> osl = new ArrayList<>();
        CalcTempo calcidade = new CalcTempo();
        this.oslist = osl;
        this.nome = nome;
        this.endereco = endereco;
        this.nasc = nasc;
        idade = calcidade.calcNasc(nasc);
        this.cpf_cnpj = cpf_cnpj;
        this.cep = cep;
        this.cel = cel;
    }
    
    public Cliente (String nome,String endereco,String nasc,String cpf_cnpj
            ,long cep,long cel ,String email){
        List <OrdemDeServico> osl = new ArrayList<>();
        CalcTempo calcidade = new CalcTempo();
        this.oslist = osl;
        this.nome = nome;
        this.endereco = endereco;
        this.nasc = nasc;
        idade = calcidade.calcNasc(nasc);
        this.email = email;
        this.cpf_cnpj = cpf_cnpj;
        this.cep = cep;
        this.cel = cel;
    
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the endereco
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * @param endereco the endereco to set
     */
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the cpf_cnpj
     */
    public String getCpf_cnpj() {
        return cpf_cnpj;
    }

    /**
     * @param cpf_cnpj the cpf_cnpj to set
     */
    public void setCpf_cnpj(String cpf_cnpj) {
        this.cpf_cnpj = cpf_cnpj;
    }

    /**
     * @return the cep
     */
    public long getCep() {
        return cep;
    }

    /**
     * @param cep the cep to set
     */
    public void setCep(long cep) {
        this.cep = cep;
    }

    /**
     * @return the cel
     */
    public long getCel() {
        return cel;
    }

    /**
     * @param cel the cel to set
     */
    public void setCel(long cel) {
        this.cel = cel;
    }
    public String getNasc(){
        return nasc;
    }
    public void setNasc(String nasc){
        this.nasc = nasc;
    }

    /**
     * @return the oslist
     */
    public List <OrdemDeServico> getOslist() {
        return oslist;
    }

    /**
     * @param oslist the oslist to set
     */
    public void setOslist(List <OrdemDeServico> oslist) {
        this.oslist = oslist;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.cpf_cnpj);
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
        final Cliente other = (Cliente) obj;
        return Objects.equals(this.cpf_cnpj, other.cpf_cnpj);
    }
    
    
    
    public void menu (){
        int op = 0;
        while(sair){
            op = Integer.parseInt(JOptionPane.
                    showInputDialog(null,
                     menu, t, 3));
            switch (op) {
                case 1:
                    addOs();
                    break;
            }
        }


    }
    
    
    
    
    // Este metodo ainda não possui utilidade
    public void addOs (){
        OrdemDeServico osobj = new OrdemDeServico();
        osobj.menu();
        oslist.add(osobj);
    }
    public String toString (){
        return "----------------" + "\n"
                +"Nome: "+ nome + "\n"
                +"Data de nascimento: "+nasc+"\n"
                +"Idade: "+idade+"\n"
                +"Endereço: "+ endereco + "\n"
                +"CPF: "+ cpf_cnpj + "\n"
                +"CEP: "+ cep + "\n"
                +"Celular: "+ cel + "\n"
                +"E-mail: "+ email + "\n"
                +"----------------" + "\n";

                }
    
}
