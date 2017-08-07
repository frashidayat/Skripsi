/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Module;

import java.io.IOException;
import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.GregorianCalendar;

/**
 *
 * @author LENOVO
 */
public class WholeDayExtractor {
    private final DurationInTrafficExtractor[] wholeDay = new DurationInTrafficExtractor[24];
    private final String[] hours = new String[24];
    
    public void initialize(String unix, String origin, String destination, String trafficModel) throws ParseException{
        //SimpleDateFormat format = new SimpleDateFormat("dd MM yyyy");
        //Calendar temp = new GregorianCalendar();
        //Date tempDate = format.parse(unix);
        //temp.setTime(tempDate);
        //temp.set(Calendar.HOUR_OF_DAY, 0);
        //temp.set(Calendar.MINUTE, 0);
        Long unixDate = new Long(unix);//temp.getTimeInMillis()/1000;
        long counterUnixperHour = 3600;
        for (int i = 0; i < wholeDay.length; i++) {
            wholeDay[i] =  new DurationInTrafficExtractor(unixDate.toString(), origin, destination, trafficModel);
            unixDate += counterUnixperHour;
        }
    }
    
    public void extract() throws IOException{
        for (int i = 0; i < wholeDay.length; i++) {
            wholeDay[i].extract();
            hours[i] =  wholeDay[i].getDepartureTimeHours();
        }
    }

    public DurationInTrafficExtractor[] getWholeDay() {
        return wholeDay;
    }

    public String[] getHours() {
        return hours;
    }
    
    
}
