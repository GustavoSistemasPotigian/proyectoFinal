/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DateManager;

import java.util.Date;
import java.util.Calendar;

/**
 *
 * @author Usuario
 */
public class DateProvider {
    public Integer getDayofMonth(){
        //METODO QUE DEVUELVE EL DIA DEL MES
        int dayofMonth=0;    
        //TODO
        Date now = new Date();
        java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("dd");
        dayofMonth = Integer.valueOf((String)sdf.format(now));
        return dayofMonth;
    }
    
    public Integer getDayofWeek(){
        //METODO QUE DEVUELVE EL DIA DE LA SEMANA
        int dayofWeek=0;    
        //TODO
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        dayofWeek= calendar.get(Calendar.DAY_OF_WEEK);
        return dayofWeek;
    }
}
