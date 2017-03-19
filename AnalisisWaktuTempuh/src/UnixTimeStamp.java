
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author LENOVO
 */
public class UnixTimeStamp {

    /**
     * @param args the command line arguments
     */
    
    
    
    public static void main(String[] args) {
        Date now = new Date();
        DateFormat formatDate = new SimpleDateFormat("EEE dd MM yyyy HH:mm");
        String datexx =  "06 03 2017 09:30";
        System.out.println(now);
        
        Date nextDay = new Date(now.getTime()+86400000);
        System.out.println(nextDay);
        
        Long unixTime = now.getTime()/1000;
        System.out.println(formatDate.format(now));
        System.out.println(unixTime);
        System.out.println((unixTime/3600));
        System.out.println(((unixTime+3600)/3600)*3600);
        System.out.println(formatDate.format(new Date((unixTime/3600)*3600*1000)));
        System.out.println(formatDate.format(new Date(((unixTime+3600)/3600)*3600*1000)));
        System.out.println(formatDate.format(new Date(((unixTime+7200)/3600)*3600*1000)));
    }
    
}
