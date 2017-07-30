/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Module.DataProcessor;
import java.io.IOException;
import java.text.ParseException;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;

/**
 *
 * @author LENOVO
 */
public class DurationTimeController {
    
    private final DataProcessor processor;
    
    public DurationTimeController (){
        this.processor = new DataProcessor();
    }
    
    public void doCalculate(JFormattedTextField date, String origin, String destination, JCheckBox trafficModel1, JCheckBox trafficModel2, JCheckBox trafficModel3) throws ParseException, IOException{
        if (trafficModel3==null) {
            if(trafficModel2==null){
                processor.initalize(date, origin, destination, trafficModel1);
            }else{
                processor.initalize(date, origin, destination, trafficModel1, trafficModel2);
             }
        }else{
            processor.initalize(date, origin, destination, trafficModel1, trafficModel2, trafficModel3);
        }
    }
    public String getResult(){
        return processor.resultProcessingData();
    }
    /*public boolean checkData(){
        return processor.dataIsEmpty();
    }
    */
    public void saveData(String dir, String filename) throws IOException{
        processor.saveFile(dir, filename);
    }
            
}
