/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package aplicacao;
import entidade.OrdemDeServico;
/**
 *
 * @author Usuário
 */
public class Aplicacao {

    public static void main(String[] args) {
      OrdemDeServico os = new OrdemDeServico();
      os.menu();
      System.out.println(os.local);
    }
    
}

