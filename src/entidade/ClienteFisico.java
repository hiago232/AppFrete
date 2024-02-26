package entidade;

import java.io.Serializable;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Usuário
 */
public class ClienteFisico extends Cliente implements Serializable {
    
    private String cpf = "";
    private String nome = "";
    private String nasc = "";
    
    public ClienteFisico(){}
    public ClienteFisico(String cpf,String nome,String nasc,String email,
            long cel,long cep,String rua,int numero,String bairro,String cidade,
            String estado){
        this.cpf = cpf  ;
        this.nome = nome;
        this.nasc = nasc;
        this.email = email;
        this.cel = cel;
        this.cep = cep;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        
    }

    //Getters & Setters


    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getCpf(){
        return cpf;
    }
    public void setNome(String nome){
        this.nome = nome; 
    }
    public String getNome(){
        return nome;
    }
    public void setNasc(String nasc){
        this.nasc = nasc;
    }
    public String getNasc(){
        return nasc;
    }
    
    public String toString (){
        StringBuilder sb =  new StringBuilder();
        sb.append("----------------" + "\n");
        sb.append("Nome: " + nome + "\n");
        sb.append("CPF: " + cpf + "\n");
        sb.append("Data de nascimento: " + nasc + "\n");
        sb.append("E-mail: " + email + "\n");
        sb.append("Celular: " + cel + "\n");
        sb.append("CEP: " + cep + "\n");
        sb.append("CEP: " + rua + "\n");
        sb.append("CEP: " + numero + "\n");
        sb.append("CEP: " + bairro + "\n");
        sb.append("CEP: " + cidade + "\n");
        sb.append("CEP: " + estado + "\n");
        sb.append("Serviços:" + oslist.size() + "\n");
        sb.append("----------------" + "\n");
     return sb.toString();
                
    }
}
