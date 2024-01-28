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
    private String responsavel,nomeFantasia, razaoSocial;
    
    
    public String toString() {
        return "----------------" + "\n"
                + "Razão Social: " + razaoSocial + "\n"
                + "Nome Fantasia: " + nomeFantasia + "\n"
                + "Responsavel: " + responsavel + "\n"
                + "CNPJ: " + cnpj + "\n"
                + "Endereço: " + endereco + "\n"
                + "CEP: " + cep + "\n"
                + "Celular: " + cel + "\n"
                + "E-mail: " + email + "\n"
                + "Serviços:" + oslist.size() + "\n"
                + "----------------\n";
    }
}
