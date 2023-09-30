/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.Duration;
/**
 *
 * @author Usuário
 */
public class CalcTempo {
    
    public Duration t1;
    
    public String calcDuracao (String ti,String tf){
        DateTimeFormatter frm1 = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime d1 = LocalTime.parse(ti,frm1);
        LocalTime d2 = LocalTime.parse(tf,frm1);
        Duration t1 = Duration.between(d1, d2);
        return String.format("%2d:%02d"
                , t1.toHoursPart()
                , t1.toMinutesPart());
        
    }
  
}
