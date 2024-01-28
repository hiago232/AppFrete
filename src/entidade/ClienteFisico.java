package entidade;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Usuário
 */
public class ClienteFisico extends Cliente{
    public int idade = 0;
    private String cpf = "";
    private String nome,nasc;
    
    public int getIdade(){
        return idade;
    }

    //Getters & Setters
    public void setIdade(int idade) {
        this.idade = idade;
    }

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
     return "----------------" + "\n"
                + "Nome: " + nome + "\n"
                + "Data de nascimento: " + nasc + "\n"
                + "Idade: " + idade + "\n"
                + "Endereço: " + endereco + "\n"
                + "CPF: " + cpf + "\n"
                + "CEP: " + cep + "\n"
                + "Celular: " + cel + "\n"
                + "E-mail: " + email + "\n"
                + "Serviços:" + oslist.size() + "\n"
                + "----------------" + "\n";

    }
}
