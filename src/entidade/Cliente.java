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
public abstract class Cliente implements Serializable {
    // Declaração de variáveis
    public String t = "Cliente";
    protected String endereco,email;

    protected long cep = 0;
    protected long cel = 0;
    boolean sair = true;
    protected List <OrdemDeServico> oslist; // lista de ordens de serviço
    
    //Implementar menu #em processo de construção
    protected String menu =  "";
     
    
    
    /**Contrutores
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

*/
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

   
    
    
    public void menu (){
        int op = 0;
        while(sair){
            op = Integer.parseInt(JOptionPane.
                    showInputDialog(null,
                     menu, t, 3));
            switch (op) {
                case 1:
                    abrirOs();
                    break;
            }
        }


    }
    
    
    
    public void addOs(OrdemDeServico osobj){
       oslist.add(osobj); 
    }
    
    // Este metodo ainda não possui utilidade
    public void abrirOs (){
        OrdemDeServico osobj = new OrdemDeServico();
        osobj.menu();
        oslist.add(osobj);
    }
   
    public abstract String toString ();
    
}
