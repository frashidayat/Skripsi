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
//import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 *
 * @author LENOVO
 */
public class WholeDayDurationTimeExtractor {

    private final DurationTimeExtractor[] oneDay1 = new DurationTimeExtractor[24];
    private final DurationTimeExtractor[] oneDay2 = new DurationTimeExtractor[24];
    private final LinkedHashMap<String, Double> oneDayBestGuess1 = new LinkedHashMap<>();
    private final LinkedHashMap<String, Double> oneDayPessimist1 = new LinkedHashMap<>();
    private final LinkedHashMap<String, Double> oneDayBestGuess2 = new LinkedHashMap<>();
    private final LinkedHashMap<String, Double> oneDayPessimist2 = new LinkedHashMap<>();
    private final String[] hours = new String[24];
    private final String sampeldestination1 = "-6.9536001,107.6193958";// amaya residence
    private final String sampeldestination2 = "-6.937021,107.6643817";// Jalan Puspa Utara
    private final String origin = "-6.8746025,107.6024968";// Unpar
    private final DateUnixConverter converter = new DateUnixConverter();
    private final Calendar calendar = GregorianCalendar.getInstance();
    private Date theDate;
    
    public void initialize(String date) throws ParseException {
        SimpleDateFormat formatDate = new SimpleDateFormat("EEE dd MM yyyy HH:mm");
        this.theDate = formatDate.parse(date);
        Long unixDate = converter.dateToUnix(theDate);
        long counterUnixperDay = 3600;
        for (int i = 0; i < oneDay1.length && i < oneDay2.length; i++) {
            oneDay1[i] = new DurationTimeExtractor(unixDate.toString(), origin, sampeldestination1);
            oneDay2[i] = new DurationTimeExtractor(unixDate.toString(), origin, sampeldestination2);
            unixDate = unixDate + counterUnixperDay;
        }
    }
    
    public void initialize(Long unix) throws ParseException {
        Long temp = unix;
        this.theDate = converter.unixTodate(temp);
        long counterUnixperDay = 3600;
        for (int i = 0; i < oneDay1.length && i < oneDay2.length; i++) {
            oneDay1[i] = new DurationTimeExtractor(temp.toString(), origin, sampeldestination1);
            oneDay2[i] = new DurationTimeExtractor(temp.toString(), origin, sampeldestination2);
            temp += counterUnixperDay;
        }
    }

    public void extractWholeDay() throws IOException {
        for (int i = 0; i < oneDay1.length && i < oneDay2.length; i++) {
            oneDay1[i].extractDurationTime();
            oneDay2[i].extractDurationTime();
            String tempString = oneDay1[i].getDEPARTURE_TIME();
            Date tempDate = converter.unixTodate(Long.parseLong(tempString));
            calendar.setTime(tempDate);
            hours[i] = "" + calendar.get(Calendar.HOUR_OF_DAY);
            oneDayBestGuess1.put(hours[i], Double.parseDouble(oneDay1[i].getBESTGUESSTIME()));
            oneDayBestGuess2.put(hours[i], Double.parseDouble(oneDay2[i].getBESTGUESSTIME()));
            oneDayPessimist1.put(hours[i], Double.parseDouble(oneDay1[i].getPESSIMISTTIME()));
            oneDayPessimist2.put(hours[i], Double.parseDouble(oneDay2[i].getPESSIMISTTIME()));
            //oneDay1[i].printOptimistPessimist();
            //oneDay2[i].printOptimistPessimist();
        }
    }
    
    /*public void printHashMap(){
        for (int i = 0; i < hours.length; i++) {
            System.out.print(hours[i]+" ");
        }
        System.out.println("");
        oneDayBestGuess1.keySet().stream().forEach((name) -> {
            String key =name;
            String value = oneDayBestGuess1.get(name);
            System.out.println(key + " " + value);
        }); 
        System.out.println("");
        oneDayBestGuess2.keySet().stream().forEach((name) -> {
            String key = name;
            String value = oneDayBestGuess2.get(name);
            System.out.println(key + " " + value);
        });
        System.out.println("");
        oneDayPessimist1.keySet().stream().forEach((name) -> {
            String key = name;
            String value = oneDayPessimist1.get(name);
            System.out.println(key + " " + value);
        });
        System.out.println("");
        oneDayPessimist2.keySet().stream().forEach((name) -> {
            String key = name;
            String value = oneDayPessimist2.get(name);
            System.out.println(key + " " + value);
        });
        System.out.println("");
    }*/

    public Date getTheDate() {
        return theDate;
    }

    public LinkedHashMap<String, Double> getOneDayBestGuess1() {
        return oneDayBestGuess1;
    }

    public LinkedHashMap<String, Double> getOneDayPessimist1() {
        return oneDayPessimist1;
    }

    public LinkedHashMap<String, Double> getOneDayBestGuess2() {
        return oneDayBestGuess2;
    }

    public LinkedHashMap<String, Double> getOneDayPessimist2() {
        return oneDayPessimist2;
    }

    public String[] getHours() {
        return hours;
    }

    public String getSampeldestination1() {
        return sampeldestination1;
    }

    public String getSampeldestination2() {
        return sampeldestination2;
    }
    
    
}
