/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Module;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
//import org.jsoup.helper.HttpConnection;

/**
 *
 * @author LENOVO
 */
public class DurationInTrafficExtractor {

    private final String DIRECTION_URL = "https://maps.googleapis.com/maps/api/directions/json";
    //private final String APIKEY = "AIzaSyAtiAnSkL1NFV7wWpPvunzRgVsn4dJTaPo";
    private final String APIKEY = "AIzaSyA6uUMhhE5HxNOkBEKOTYjseToYlKY9qFA";
    private final String LANGUAGE = "id";
    private final String departureTime;
    private final String origin;
    private final String destination;
    private final String trafficModel;
    private int time;

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
    
    public DurationInTrafficExtractor(String unix, String origin, String destination, String trafficModel) {
        this.departureTime = unix;
        this.origin = origin;
        this.destination = destination;
        this.trafficModel = trafficModel;
    }

    public void extract() throws IOException {
        String content;
        JSONObject jsonResponse;
        //Connection connection = HttpConnection.connect(this.DIRECTION_URL);
        Connection connection = Jsoup.connect(this.DIRECTION_URL);
        connection.timeout(180000);
        connection.data("key", this.APIKEY);
        connection.data("origin", this.origin);
        connection.data("destination", this.destination);
        connection.data("language", this.LANGUAGE);
        connection.data("departure_time", this.departureTime);
        connection.data("traffic_model", this.trafficModel);
        connection.ignoreContentType(true);
        content = connection.execute().body();
        jsonResponse = new JSONObject(content);

        if (jsonResponse.getString("status").equals("OK")) {
            JSONArray routes = jsonResponse.getJSONArray("routes");
            JSONObject route = routes.getJSONObject(0);
            JSONArray legs = route.getJSONArray("legs");
            JSONObject leg = legs.getJSONObject(0);
            JSONObject duration = leg.getJSONObject("duration_in_traffic");
            int durationValue = Integer.parseInt(duration.optString("value"));
            if (durationValue%60 == 0) {
                this.setTime(durationValue/60);
            }else{
                this.setTime((durationValue/60)+1);
            }
        } else {
            System.err.println(jsonResponse.getString("status"));
        }
    }

    public String getDepartureTimeHours() {
        Calendar tempCal = new GregorianCalendar();
        Long tempLong = new Long(this.departureTime);
        tempLong =  tempLong*1000;
        Date tempDate = new Date(tempLong);
        tempCal.setTime(tempDate);
        return tempCal.get(Calendar.HOUR_OF_DAY) + "";
    }
}
