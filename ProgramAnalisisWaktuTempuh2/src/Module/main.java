/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Module;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 *
 * @author LENOVO
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ParseException {
        
        /*Date now = new Date();
        long nowUnix = now.getTime()/1000+7200;
        System.out.println(now);
        
        String depart_time = nowUnix + "";
        System.out.println(depart_time);
        String destination1 = "-6.9536001,107.6193958";
        String destination2 = "-6.937021,107.6643817";
        String origin = "-6.8746025,107.6024968";
        DurationTimeExtractor test1 = new DurationTimeExtractor(depart_time, origin, destination1);
        DurationTimeExtractor test2 = new DurationTimeExtractor(depart_time, origin, destination2);
        test1.extractDurationTime();
        test2.extractDurationTime();
        test1.printOptimistPessimist();
        test2.printOptimistPessimist();
        */
        
        DataProcessor test5 =  new DataProcessor();
        
        WholeDayDurationTimeExtractor test3 = new WholeDayDurationTimeExtractor();
        test3.initialize("Mon 20 03 2017 12:00");
        test3.extractWholeDay();
        //System.out.println(test5.minDurationTimeDataProcess(test3.getSampeldestination1(),test3.getHours(), test3.getOneDayBestGuess1(), test3.getOneDayPessimist1()));
        //System.out.println(test5.minDurationTimeDataProcess(test3.getSampeldestination2(),test3.getHours(), test3.getOneDayBestGuess2(), test3.getOneDayPessimist2()));        
        test3.printHashMap();
        
        //OneWeekDurationTimeExtractor test4 = new OneWeekDurationTimeExtractor();
        //test4.initialize("Sun 19 03 2017 13:00");
        //test4.extractOneWeek();
    }
    
}
