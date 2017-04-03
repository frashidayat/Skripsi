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
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author LENOVO
 */
public class DurationTimeController {
    
    private final DataProcessor processor;
    
    public DurationTimeController (JFrame gui){
        this.processor =  new DataProcessor(gui);
    }
    
    public String doCalculate(JTextField date, JTextField origin, JTextField destination, JCheckBox trafficModel1, JCheckBox traffficModel2) throws ParseException, IOException{
        String result;
        if (traffficModel2 == null) {
            result = processor.resultProcessingData(date, origin, destination, trafficModel1);
        } else {
            result = processor.resultProcessingData(date, origin, destination, trafficModel1, traffficModel2);
        }
        return result;
    }
    
    public String doShowData(){
        String result;
        result = processor.printAllData();
        return result;
    }
    
    public boolean checkData(){
        return processor.dataIsEmpty();
    }
    
    public void saveData(String dir, String filename) throws IOException{
        processor.saveFile(dir, filename);
    }
            
}
