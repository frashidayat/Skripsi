
import java.io.IOException;
import java.text.ParseException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author LENOVO
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException, IOException {
        WholeDayTimeExtractor test = new WholeDayTimeExtractor();
        test.initialize();
        test.extractWholeDay();
        test.printAll();
    }
    
}
