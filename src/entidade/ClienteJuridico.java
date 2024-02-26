/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidade;

/**
 *
 * @author Usuário
 */
public class ClienteJuridico extends Cliente {

    private String cnpj = "";
    private String responsavel =  "";
    private String nomeFantasia = "";
    private String razaoSocial = "";
    
    
    public ClienteJuridico() {
    }

    public ClienteJuridico(String cnpj, String responsavel, String razaoSocial,
            String nomeFantasia, String email,long cel, long cep, String rua,
            int numero, String bairro, String cidade,
            String estado) {
        this.cnpj = cnpj;
        this.responsavel = responsavel;
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
        this.email = email;
        this.cel = cel;
        this.cep = cep;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;

    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }
    
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("----------------" + "\n");
        sb.append("Nome: " + responsavel + "\n");
        sb.append("CPF: " + cnpj+ "\n");
        sb.append("Data de nascimento: " + razaoSocial + "\n");
        sb.append("Data de nascimento: " + nomeFantasia + "\n");
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
