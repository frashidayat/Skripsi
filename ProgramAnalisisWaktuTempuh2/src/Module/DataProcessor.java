/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Module;

import java.util.Collections;
import java.util.HashMap;

/**
 *
 * @author LENOVO
 */
public class DataProcessor {
        
    public String minDurationTimeDataProcess(String sampel, String[] hours, HashMap bestGuess, HashMap pessimist){
        String result = "";
        if (sampel.equals("-6.9536001,107.6193958")) {
            result += "Sampel Amaya Residence memiliki ";
        }else{
            result += "Sampel Jalan Puspa Utara memiliki ";
        }
        int idxcurrentHour = 25;
        Double minBestGuess = (Double) Collections.min(bestGuess.values());
        Double minPessimist = (Double) Collections.min(pessimist.values());
        for (int i = 0; i<hours.length;i++) {
            if (i < idxcurrentHour && bestGuess.get(hours[i]) == minBestGuess && pessimist.get(hours[i]) == minPessimist) {
                idxcurrentHour = i;
            }
        }
        result += "waktu terbaik ada pada jam " + hours[idxcurrentHour] + " dengan waktu tempuh " + minBestGuess + " menit jika tidak macet dan " + minPessimist + " menit jika macet";
        return result;
    }
    
}
