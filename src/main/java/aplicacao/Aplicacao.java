/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package aplicacao;
import entidade.OrdemDeServico;
import javax.swing.*;
/**
 *
 * @author Usuário
 */
public class Aplicacao {

    public static void main(String[] args) {
      OrdemDeServico os = new OrdemDeServico();
      String t = "Menu";
      os.menu();
      JOptionPane.showMessageDialog(null, os.local, t, JOptionPane.INFORMATION_MESSAGE);
    }
    
}

