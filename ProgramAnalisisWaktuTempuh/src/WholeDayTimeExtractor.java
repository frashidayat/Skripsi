
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author LENOVO
 */
public class WholeDayTimeExtractor {

    private final String[] hours = new String[24];
    private final HashMap hourOptimist1 = new HashMap();
    private final HashMap hourPessimist1 = new HashMap();
    private final HashMap hourOptimist2 = new HashMap();
    private final HashMap hourPessimist2 = new HashMap();
    private final DurationTimeExtractor[] eachHourExtractor1 = new DurationTimeExtractor[24];
    private final DurationTimeExtractor[] eachHourExtractor2 = new DurationTimeExtractor[24];
    private final String SAMPEL1 = "ChIJbd6XqljoaC4RhbVnNqC6aeM";
    private final String SAMPEL2 = "ChIJQ2lnJR3oaC4RhajeHgXKUdE";
    private final String ORIGIN = "ChIJbYmcEu7maC4RRijB2oKhHLA";

    public void initialize() throws ParseException {
        Date now = new Date();
        Long unixNow = now.getTime()/1000;
        System.out.println("you will calculating in " + printDate(unixNow));
        System.out.println(unixNow);
        //if (unixNow % 3600 != 0) {
        unixNow = (unixNow +3600)/ 3600 * 3600;
        System.out.println(unixNow);
        //} else {
        //    hour = (unixNow + 3600)*1000;
        //}
        for (int i = 0; i < eachHourExtractor1.length && i < eachHourExtractor2.length; i++) {
            eachHourExtractor1[i] = new DurationTimeExtractor(unixNow, ORIGIN, SAMPEL1);
            eachHourExtractor2[i] = new DurationTimeExtractor(unixNow, ORIGIN, SAMPEL2);
            unixNow = unixNow + 3600;
        }
    }

    public String printDate(Long unix) throws ParseException {
        String result;
        DateFormat formatDate = new SimpleDateFormat("EEE dd MM yyyy HH:mm");
        result = formatDate.format(new Date(unix * 1000));
        return result;
    }

    public void extractWholeDay() throws IOException {
        for (int i = 0; i < eachHourExtractor1.length && i < eachHourExtractor2.length; i++) {
            eachHourExtractor1[i].extractDurationTime();
            eachHourExtractor2[i].extractDurationTime();
//            hours[i] = eachHourExtractor1[i].getDEPARTTIME().toString();
            hourOptimist1.put(hours[i], eachHourExtractor1[i].getOPTIMISTTIME());
            hourOptimist2.put(hours[i], eachHourExtractor2[i].getOPTIMISTTIME());
            hourPessimist1.put(hours[i], eachHourExtractor1[i].getPESSIMISTTIME());
            hourPessimist2.put(hours[i], eachHourExtractor2[i].getPESSIMISTTIME());
        }
    }

    public void printAll() {
        for (int i = 0; i < hours.length; i++) {
            System.out.println(hours[i] + hourOptimist1.get(hours[i]) + hourPessimist1.get(hours[i]));
            System.out.println("");
            System.out.println(hours[i] + hourOptimist2.get(hours[i]) + hourPessimist2.get(hours[i]));
            System.out.println("");
        }
    }
}
