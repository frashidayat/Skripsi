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

/**
 *
 * @author LENOVO
 */
public class OneWeekDurationTimeExtractor {

    private final WholeDayDurationTimeExtractor[] oneWeek = new WholeDayDurationTimeExtractor[7];
    private final DateUnixConverter converter = new DateUnixConverter();

    public void initialize(String date) throws ParseException {
        SimpleDateFormat formatDate = new SimpleDateFormat("EEE dd MM yyyy HH:mm");
        Date theDate = formatDate.parse(date);
        Long unixDate = converter.dateToUnix(theDate);
        long counterUnixperWeek = 86400;
        for (int i = 0; i < oneWeek.length; i++) {
            oneWeek[i] = new WholeDayDurationTimeExtractor();
            oneWeek[i].initialize(unixDate);
            unixDate += counterUnixperWeek;
        }

    }

    public void extractOneWeek() throws IOException {
        for (WholeDayDurationTimeExtractor oneWeek1 : oneWeek) {
            oneWeek1.extractWholeDay();
        }
    }

}
