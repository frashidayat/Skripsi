/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Module;

import java.io.IOException;
import java.text.ParseException;

/**
 *
 * @author LENOVO
 */
public class main {

    /**
     * @param args the command line arguments
     * @throws java.text.ParseException
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws ParseException, IOException {
        WholeDayExtractor test = new WholeDayExtractor();
//        test.initialize("26 03 2017", "-6.8746025,107.6024968", "-6.9536001,107.6193958", "pessimistic");
//        test.extract();
        DurationInTrafficExtractor[] temp1;// = test.getWholeDay();
        String[] temp2;// = test.getHours();
        /*for (int i = 0; i < temp1.length && i < temp2.length; i++) {
         System.out.println(temp2[i]);
         System.out.println(temp1[i].getTime());
         System.out.println("");
         }
         */
        WeekExtractor test2 = new WeekExtractor();
        test2.initialize("29 03 2017", "-6.8746025,107.6024968", "-6.9536001,107.6193958", "pessimistic");
        test2.extract();
        WholeDayExtractor[] temp3 = test2.getOneWeek();
        String[] temp4 = test2.getDay();
        for (int i = 0; i < temp3.length && i < temp4.length; i++) {
            temp1 = temp3[i].getWholeDay();
            temp2 = temp3[i].getHours();
            for (int j = 0; j < temp1.length && j < temp2.length; j++) {
                System.out.println("day : " + temp4[i]);
                System.out.println("time : " + temp2[j]);
                System.out.println("duration in traffic : " + temp1[j].getTime());
                System.out.println("");
            }
        }
        
        
    }

}
