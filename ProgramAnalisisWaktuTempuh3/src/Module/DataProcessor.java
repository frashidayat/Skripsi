/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Module;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.Vector;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author LENOVO
 */
public class DataProcessor {

    private final JFrame gui;
    private final String csvSplitBy = ",";
    private final Vector<String> data = new Vector();
    private final Vector<String> trafficModels = new Vector<>();

    public DataProcessor(JFrame gui) {
        this.gui = gui;
    }

    public String resultProcessingData(JTextField date, JTextField origin, JTextField destination, JCheckBox trafficModel) throws ParseException, IOException {
        String result = "";
        String day = "";
        String hour = "";
        int tempCurrent;
        int min = 999999;
        this.initalize(date, origin, destination, trafficModel);
        for (String data1 : data) {
            tempCurrent = Integer.parseInt(data1.split(csvSplitBy)[2]);
            if (tempCurrent < min) {
                day = data1.split(csvSplitBy)[0];
                hour = data1.split(csvSplitBy)[1];
                min = tempCurrent;
            }
        }
        result = result + "Waktu tempuh tercepat adalah pada : hari ke " + day + " jam " + hour + " dengan durasi " + min + "\n";
        return result;
    }

    public String resultProcessingData(JTextField date, JTextField origin, JTextField destination, JCheckBox trafficModel1, JCheckBox trafficModel2) throws ParseException, IOException {
        String result = "";
        String day = "";
        String hour = "";
        int tempCurrent1;
        int tempCurrent2;
        int tempAvg;
        int avgCurrent = 999999;
        int min1 = 999999;
        int min2 = 999999;
        this.initalize(date, origin, destination, trafficModel1, trafficModel2);
        for (String data1 : data) {
            tempCurrent1 = Integer.parseInt(data1.split(csvSplitBy)[2]);
            tempCurrent2 = Integer.parseInt(data1.split(csvSplitBy)[3]);
            tempAvg = (tempCurrent1 + tempCurrent2) / 2;
            if (avgCurrent > tempAvg) {
                day = data1.split(csvSplitBy)[0];
                hour = data1.split(csvSplitBy)[1];
                min1 = tempCurrent1;
                min2 = tempCurrent2;
                avgCurrent = tempAvg;
            }

        }
        result = result + "Waktu tempuh tercepat adalah pada : hari ke " + day + " jam " + hour + " dengan durasi " + trafficModel1.getText() + " : " + min1 + " " + trafficModel2.getText() + " : " + min2 + "\n";
        return result;
    }

    public String printAllData() {
        String result = "";
        if (trafficModels.size() == 1) {
            result = result + "traffic model : " + trafficModels.get(0) + "\n";
            for (String data1 : data) {
                String[] temp = data1.split(csvSplitBy);
                result = result + "day : " + temp[0] + " time : " + temp[1] + " duration : " + temp[2] + "\n";
            }
        } else if (trafficModels.size() == 2) {
            result = result + "traffic model : " + trafficModels.get(0) + csvSplitBy + trafficModels.get(1) + "\n";
            for (String data1 : data) {
                String[] temp = data1.split(csvSplitBy);
                result = result + "day : " + temp[0] + " time : " + temp[1] + " duration : " + temp[2] + " duration : " + temp[3] + "\n";
            }
        } else {
            JOptionPane.showMessageDialog(gui, "Error atau Data tidak ada", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return result;
    }

    private void initalize(JTextField date, JTextField origin, JTextField destination, JCheckBox trafficModel) throws ParseException, IOException {
        data.clear();
        trafficModels.clear();
        WeekExtractor temp = new WeekExtractor();
        temp.initialize(date.getText(), origin.getText(), destination.getText(), trafficModel.getText());
        temp.extract();
        trafficModels.add(trafficModel.getText());

        DurationInTrafficExtractor[] tempDuration;
        String[] tempHours;
        WholeDayExtractor[] tempWeek = temp.getOneWeek();
        String[] tempDays = temp.getDay();
        for (int i = 0; i < tempWeek.length && i < tempDays.length; i++) {
            tempDuration = tempWeek[i].getWholeDay();
            tempHours = tempWeek[i].getHours();
            for (int j = 0; j < tempDuration.length && j < tempHours.length; j++) {
                data.add(tempDays[i] + csvSplitBy + tempHours[j] + csvSplitBy + tempDuration[j].getTime());
            }
        }
    }

    private void initalize(JTextField date, JTextField origin, JTextField destination, JCheckBox trafficModel1, JCheckBox trafficModel2) throws ParseException, IOException {
        data.clear();
        trafficModels.clear();
        WeekExtractor temp1 = new WeekExtractor();
        WeekExtractor temp2 = new WeekExtractor();

        temp1.initialize(date.getText(), origin.getText(), destination.getText(), trafficModel1.getText());
        temp1.extract();
        temp2.initialize(date.getText(), origin.getText(), destination.getText(), trafficModel2.getText());
        temp2.extract();
        trafficModels.add(trafficModel1.getText());
        trafficModels.add(trafficModel2.getText());

        DurationInTrafficExtractor[] tempDuration1;
        DurationInTrafficExtractor[] tempDuration2;
        String[] tempHours1;
        String[] tempHours2;
        WholeDayExtractor[] tempWeek1 = temp1.getOneWeek();
        WholeDayExtractor[] tempWeek2 = temp2.getOneWeek();
        String[] tempDays1 = temp1.getDay();

        for (int i = 0; i < tempWeek1.length && i < tempDays1.length; i++) {
            tempDuration1 = tempWeek1[i].getWholeDay();
            tempDuration2 = tempWeek2[i].getWholeDay();
            tempHours1 = tempWeek1[i].getHours();
            tempHours2 = tempWeek2[i].getHours();
            for (int j = 0; j < tempDuration1.length && j < tempHours1.length; j++) {
                System.out.println(tempDays1[i] + csvSplitBy + tempHours1[j] + csvSplitBy + tempDuration1[j].getTime() + csvSplitBy + tempDuration2[j].getTime());
                data.add(tempDays1[i] + csvSplitBy + tempHours1[j] + csvSplitBy + tempDuration1[j].getTime() + csvSplitBy + tempDuration2[j].getTime());
            }
        }
    }

    public void saveFile(String directory, String fileName) throws IOException {
        File file = new File(directory + "\\" + fileName);
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        for (String data1 : data) {
            writer.write(data1);
            writer.newLine();
        }
        writer.flush();
        writer.close();
    }

    public boolean dataIsEmpty() {
        return data.isEmpty();
    }
}
