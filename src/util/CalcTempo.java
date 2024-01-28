/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.Duration;
import java.time.ZoneId;
/**
 *
 * @author Usuário
 */
public class CalcTempo {
    private double horaminuto = 0;
 
    public CalcTempo() {}

    
    public String calcDuracao (String ti,String tf){
        
        DateTimeFormatter frm1 = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime t1 = LocalTime.parse(ti,frm1);
        LocalTime t2 = LocalTime.parse(tf,frm1);
        Duration d1 = Duration.between(t1, t2);
        //Converte os minutos em horas,soma com a hora e atribui à variavel
        horaminuto = d1.toHoursPart() +  d1.toMinutesPart() /60d;
        //@return hora formatada
        return String.format("%2d:%02d"
                , d1.toHoursPart()
                , d1.toMinutesPart());
        
    }
    public double calcValorHora(double valorhora){
        return valorhora*horaminuto;      
    }    
    public int calcNasc(String nasc){
        String agora = "";
        DateTimeFormatter fm1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate t1 = LocalDate.parse(nasc, fm1);
        LocalDate t2 = LocalDate.now(ZoneId.systemDefault());

        return t2.getYear()-t1.getYear();
    
    }
  
}
