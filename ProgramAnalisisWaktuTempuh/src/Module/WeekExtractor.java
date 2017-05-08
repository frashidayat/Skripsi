/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Module;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author LENOVO
 */
public class WeekExtractor {
    
    private final WholeDayExtractor[] oneWeek =  new WholeDayExtractor[7];
    private final String[] day = new String[7];
    
    public void initialize(String date, String origin, String destination, String trafficModel) throws ParseException{
        SimpleDateFormat format = new SimpleDateFormat("dd MM yyyy");
        Calendar temp = new GregorianCalendar();
        Date tempDate = format.parse(date);
        temp.setTime(tempDate);
        temp.set(Calendar.HOUR_OF_DAY, 0);
        temp.set(Calendar.MINUTE, 0);
        Long unixDate = temp.getTimeInMillis()/1000;
        long counterUnixperDay = 86400;
        for (int i = 0; i < oneWeek.length; i++) {
            oneWeek[i] = new WholeDayExtractor();
            oneWeek[i].initialize(unixDate.toString(), origin, destination, trafficModel);
            unixDate = unixDate + counterUnixperDay;
        }
    }
    
    public void extract() throws IOException{
        for (int i = 0; i < oneWeek.length; i++) {
            oneWeek[i].extract();
            day[i] = i + 1 + "";
        }
    }

    public WholeDayExtractor[] getOneWeek() {
        return oneWeek;
    }

    public String[] getDay() {
        return day;
    }
    
    
    
}
