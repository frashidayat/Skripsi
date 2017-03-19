/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Module;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 *
 * @author LENOVO
 */
public class DateUnixConverter {
    DateFormat formatDate;
    
    public DateUnixConverter(){
        formatDate = new SimpleDateFormat("EEE dd MM yyyy HH:mm");
        TimeZone gmt = TimeZone.getTimeZone("GMT");
        formatDate.setTimeZone(gmt);
    }
    
    public Long dateToUnix(Date theDate){
        Long unix =  theDate.getTime()/1000;
        return unix;
    }
    
    public Date unixTodate(long unix){
        Long temp = unix*1000;
        Date theDate = new Date(temp);
        return theDate;
    }
    
    public Date StringToDate(String string) throws ParseException{
        Date temp = formatDate.parse(string);
        return temp;
    }
    
    public String DateToString(Date date){
        String temp = formatDate.format(date);
        return temp;
    }
}
