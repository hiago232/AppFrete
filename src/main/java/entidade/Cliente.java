/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidade;
import java.util.List;
import java.util.ArrayList;
import entidade.OrdemDeServico;

/**
 *
 * @author Usuário
 */
public class Cliente {
    // Declaração de variáveis
    private String nome,endereco,email;
    private int cpf = 0;
    private int cep = 0;
    private int cel = 0;
    private List <OrdemDeServico> oslist; // lista de ordens de serviço
    
    //Contrutores
    public Cliente (){}
    public Cliente(String nome, String endereco,int cpf, int cep,int cel) {
        List<OrdemDeServico> osl = new ArrayList<>();
        this.oslist = osl;
        this.nome = nome;
        this.endereco = endereco;
        this.cpf = cpf;
        this.cep = cep;
        this.cel = cel;
    }
    public Cliente (String nome,String endereco,int cpf,int cep,int cel
            ,String email){
        List <OrdemDeServico> osl = new ArrayList<>();
        this.oslist = osl;
        this.nome = nome;
        this.endereco = endereco;
        this.email = email;
        this.cpf = cpf;
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
     * @return the cpf
     */
    public int getCpf() {
        return cpf;
    }

    /**
     * @param cpf the cpf to set
     */
    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    /**
     * @return the cep
     */
    public int getCep() {
        return cep;
    }

    /**
     * @param cep the cep to set
     */
    public void setCep(int cep) {
        this.cep = cep;
    }

    /**
     * @return the cel
     */
    public int getCel() {
        return cel;
    }

    /**
     * @param cel the cel to set
     */
    public void setCel(int cel) {
        this.cel = cel;
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
    
    public void addOs (){
        OrdemDeServico osobj = new OrdemDeServico();
        osobj.menu();
        oslist.add(osobj);
    }
    public String toString (){
        return "Nome: "+ nome + "\n"
                //Data de nascimento
                +"Endereço: "+ endereco + "\n"
                +"CPF: "+ cpf + "\n"
                +"CEP: "+ cep + "\n"
                +"Celular: "+ cel + "\n"
                +"E-mail: "+ email + "\n";

                }
    
}
