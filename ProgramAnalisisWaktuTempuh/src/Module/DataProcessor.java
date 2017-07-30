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
import javax.swing.JFormattedTextField;

/**
 *
 * @author LENOVO
 */
public class DataProcessor {

    private final String csvSplitBy = ",";
    private final Vector<String> data = new Vector();
    private final Vector<String> trafficModels = new Vector<>();

    public String resultProcessingData() {
        String result = "";
        if (trafficModels.size() == 1) {
            String daymin = "";
            String hourmin = "";
            String daymax = "";
            String hourmax = "";
            int tempCurrent;
            int min = 999999;
            int max = 0;
            for (String data1 : data) {
                tempCurrent = Integer.parseInt(data1.split(csvSplitBy)[2]);
                if (tempCurrent < min) {
                    daymin = data1.split(csvSplitBy)[0];
                    hourmin = data1.split(csvSplitBy)[1];
                    min = tempCurrent;
                }
                if (tempCurrent > max) {
                    daymax = data1.split(csvSplitBy)[0];
                    hourmax = data1.split(csvSplitBy)[1];
                    max = tempCurrent;
                }
            }
            result = result + "Waktu tempuh tercepat adalah : hari ke " + daymin + " jam " + hourmin + " dengan durasi " + min + "\n";
            result = result + "Waktu tempuh terlambat adalah : hari ke " + daymax + " jam " + hourmax + " dengan durasi " + max + "\n";
        } else if (trafficModels.size() == 2) {
            String daymin1 = "";
            String hourmin1 = "";
            String daymin2 = "";
            String hourmin2 = "";
            String daymax1 = "";
            String hourmax1 = "";
            String daymax2 = "";
            String hourmax2 = "";
            int tempCurrent1;
            int tempCurrent2;
            int max1 = 0;
            int max2 = 0;
            int min1 = 999999;
            int min2 = 999999;
            for (String data1 : data) {
                tempCurrent1 = Integer.parseInt(data1.split(csvSplitBy)[2]);
                tempCurrent2 = Integer.parseInt(data1.split(csvSplitBy)[3]);
                if (tempCurrent1 < min1) {
                    daymin1 = data1.split(csvSplitBy)[0];
                    hourmin1 = data1.split(csvSplitBy)[1];
                    min1 = tempCurrent1;
                }
                if (tempCurrent1 > max1) {
                    daymax1 = data1.split(csvSplitBy)[0];
                    hourmax1 = data1.split(csvSplitBy)[1];
                    max1 = tempCurrent1;
                }
                if (tempCurrent2 < min2) {
                    daymin2 = data1.split(csvSplitBy)[0];
                    hourmin2 = data1.split(csvSplitBy)[1];
                    min2 = tempCurrent2;
                }
                if (tempCurrent2 > max2) {
                    daymax2 = data1.split(csvSplitBy)[0];
                    hourmax2 = data1.split(csvSplitBy)[1];
                    max2 = tempCurrent1;
                }
            }
            result = result + "Waktu tempuh tercepat adalah pada : hari ke " + daymin1 + " jam " + hourmin1 + " pada model " + trafficModels.get(0) + " adalah " + min1 + ", hari ke " + daymin2 + " jam " + hourmin2 + " pada model " + trafficModels.get(1) + " : " + min2 + "\n";
            result = result + "Waktu tempuh terlambat adalah pada : hari ke " + daymax1 + " jam " + hourmax1 + " pada model " + trafficModels.get(0) + " adalah " + max1 + ", hari ke " + daymax2 + " jam " + hourmax2 + " pada model " + trafficModels.get(1) + " : " + max2 + "\n";
        } else if (trafficModels.size() == 3) {
            String daymin1 = "";
            String hourmin1 = "";
            String daymin2 = "";
            String hourmin2 = "";
            String daymin3 = "";
            String hourmin3 = "";
            String daymax1 = "";
            String hourmax1 = "";
            String daymax2 = "";
            String hourmax2 = "";
            String daymax3 = "";
            String hourmax3 = "";
            int tempCurrent1;
            int tempCurrent2;
            int tempCurrent3;
            int max1 = 0;
            int max2 = 0;
            int max3 = 0;
            int min1 = 999999;
            int min2 = 999999;
            int min3 = 999999;
            for (String data1 : data) {
                tempCurrent1 = Integer.parseInt(data1.split(csvSplitBy)[2]);
                tempCurrent2 = Integer.parseInt(data1.split(csvSplitBy)[3]);
                tempCurrent3 = Integer.parseInt(data1.split(csvSplitBy)[4]);
                if (tempCurrent1 < min1) {
                    daymin1 = data1.split(csvSplitBy)[0];
                    hourmin1 = data1.split(csvSplitBy)[1];
                    min1 = tempCurrent1;
                }
                if (tempCurrent1 > max1) {
                    daymax1 = data1.split(csvSplitBy)[0];
                    hourmax1 = data1.split(csvSplitBy)[1];
                    max1 = tempCurrent1;
                }
                if (tempCurrent2 < min2) {
                    daymin2 = data1.split(csvSplitBy)[0];
                    hourmin2 = data1.split(csvSplitBy)[1];
                    min2 = tempCurrent2;
                }
                if (tempCurrent2 > max2) {
                    daymax2 = data1.split(csvSplitBy)[0];
                    hourmax2 = data1.split(csvSplitBy)[1];
                    max2 = tempCurrent1;
                }
                if (tempCurrent3 < min3) {
                    daymin3 = data1.split(csvSplitBy)[0];
                    hourmin3 = data1.split(csvSplitBy)[1];
                    min3 = tempCurrent3;
                }
                if (tempCurrent3 > max3) {
                    daymax3 = data1.split(csvSplitBy)[0];
                    hourmax3 = data1.split(csvSplitBy)[1];
                    max3 = tempCurrent3;
                }
            }
            result = result + "Waktu tempuh tercepat adalah pada : hari ke " + daymin1 + " jam " + hourmin1 + " pada model " + trafficModels.get(0) + " adalah " + min1 + ", hari ke " + daymin2 + " jam " + hourmin2 + " pada model " + trafficModels.get(1) + " : " + min2 + ", hari ke " + daymin3 + " jam " + hourmin3 + " pada model " + trafficModels.get(2) + " : " + min3 + "\n";
            result = result + "Waktu tempuh terlambat adalah pada : hari ke " + daymax1 + " jam " + hourmax1 + " pada model " + trafficModels.get(0) + " adalah " + max1 + ", hari ke " + daymax2 + " jam " + hourmax2 + " pada model " + trafficModels.get(1) + " : " + max2 + ", hari ke " + daymax3 + " jam " + hourmax3 + " pada model " + trafficModels.get(2) + " : " + max3 + "\n";
        } else {
            result = result + "data kosong";
        }
        return result;
    }

    /*public String printAllData() {
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

    */

    public void initalize(JFormattedTextField date, String origin, String destination, JCheckBox trafficModel) throws ParseException, IOException {
        data.clear();
        trafficModels.clear();
        WeekExtractor temp = new WeekExtractor();
        temp.initialize(date.getText(), origin, destination, trafficModel.getText());
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

    public void initalize(JFormattedTextField date, String origin, String destination, JCheckBox trafficModel1, JCheckBox trafficModel2) throws ParseException, IOException {
        data.clear();
        trafficModels.clear();
        WeekExtractor temp1 = new WeekExtractor();
        WeekExtractor temp2 = new WeekExtractor();

        temp1.initialize(date.getText(), origin, destination, trafficModel1.getText());
        temp1.extract();
        temp2.initialize(date.getText(), origin, destination, trafficModel2.getText());
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
                data.add(tempDays1[i] + csvSplitBy + tempHours1[j] + csvSplitBy + tempDuration1[j].getTime() + csvSplitBy + tempDuration2[j].getTime());
            }
        }
    }

    public void initalize(JFormattedTextField date, String origin, String destination, JCheckBox trafficModel1, JCheckBox trafficModel2, JCheckBox trafficModel3) throws ParseException, IOException {
        data.clear();
        trafficModels.clear();
        WeekExtractor temp1 = new WeekExtractor();
        WeekExtractor temp2 = new WeekExtractor();
        WeekExtractor temp3 = new WeekExtractor();

        temp1.initialize(date.getText(), origin, destination, trafficModel1.getText());
        temp1.extract();
        temp2.initialize(date.getText(), origin, destination, trafficModel2.getText());
        temp2.extract();
        temp3.initialize(date.getText(), origin, destination, trafficModel3.getText());
        temp3.extract();
        trafficModels.add(trafficModel1.getText());
        trafficModels.add(trafficModel2.getText());
        trafficModels.add(trafficModel3.getText());

        DurationInTrafficExtractor[] tempDuration1;
        DurationInTrafficExtractor[] tempDuration2;
        DurationInTrafficExtractor[] tempDuration3;
        String[] tempHours1;
        String[] tempHours2;
        String[] tempHouts3;
        WholeDayExtractor[] tempWeek1 = temp1.getOneWeek();
        WholeDayExtractor[] tempWeek2 = temp2.getOneWeek();
        WholeDayExtractor[] tempWeek3 = temp3.getOneWeek();
        String[] tempDays1 = temp1.getDay();

        for (int i = 0; i < tempWeek1.length && i < tempDays1.length; i++) {
            tempDuration1 = tempWeek1[i].getWholeDay();
            tempDuration2 = tempWeek2[i].getWholeDay();
            tempDuration3 = tempWeek3[i].getWholeDay();
            tempHours1 = tempWeek1[i].getHours();
            tempHours2 = tempWeek2[i].getHours();
            tempHouts3 = tempWeek3[i].getHours();
            for (int j = 0; j < tempDuration1.length && j < tempHours1.length; j++) {
                data.add(tempDays1[i] + csvSplitBy + tempHours1[j] + csvSplitBy + tempDuration1[j].getTime() + csvSplitBy + tempDuration2[j].getTime() + csvSplitBy + tempDuration3[j].getTime());
            }
        }
    }

    public void saveFile(String directory, String fileName) throws IOException {
        File file = new File(directory + "\\" + fileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (String data1 : data) {
                writer.write(data1);
                writer.newLine();
            }
            writer.flush();
        }
    }
    /*
     public boolean dataIsEmpty() {
     return data.isEmpty();
     }
     */
}
