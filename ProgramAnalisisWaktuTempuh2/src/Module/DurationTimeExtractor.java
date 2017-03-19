
package Module;

import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.helper.HttpConnection;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author LENOVO
 */
public class DurationTimeExtractor {

  
    private final String DIRECTION_URL = "https://maps.googleapis.com/maps/api/directions/json";
    private final String APIKEY = "AIzaSyAtiAnSkL1NFV7wWpPvunzRgVsn4dJTaPo";
    private final String LANGUAGE = "id";
    private final String OPTIMIST_TRAFFIC_MODEL = "best_guess";
    private final String PESSIMIST_TRAFFIC_MODEL = "pessimistic";
    private final String DEPARTURE_TIME;
    private final String ORIGIN;
    private final String DESTINATION;
    private String PESSIMISTTIME;
    private String BESTGUESSTIME;
    DateUnixConverter xxx = new DateUnixConverter();

    public DurationTimeExtractor(String departureTime, String origin, String destination) {
        this.DEPARTURE_TIME = departureTime;
        this.ORIGIN = origin;
        this.DESTINATION = destination;
    }

    public void setPESSIMISTTIME(String PESSIMISTTIME) {
        this.PESSIMISTTIME = PESSIMISTTIME;
    }

    public void setBESTGUESSTIME(String BESTGUESSTIME) {
        this.BESTGUESSTIME = BESTGUESSTIME;
    }

    public String getPESSIMISTTIME() {
        return PESSIMISTTIME;
    }

    public String getBESTGUESSTIME() {
        return BESTGUESSTIME;
    }

    public String getDEPARTURE_TIME() {
        return DEPARTURE_TIME;
    }
    
    public void extractDurationTime() throws IOException {
        String content;
        JSONObject jsonResponse;

        Connection connection1 = HttpConnection.connect(this.DIRECTION_URL);
        connection1.data("key", this.APIKEY);
        connection1.data("origin", this.ORIGIN);
        connection1.data("destination", this.DESTINATION);
        connection1.data("language", this.LANGUAGE);
        connection1.data("departure_time", this.DEPARTURE_TIME);
        connection1.data("traffic_model", this.OPTIMIST_TRAFFIC_MODEL);
        connection1.ignoreContentType(true);
        content = connection1.execute().body();
        jsonResponse = new JSONObject(content);

        if (jsonResponse.getString("status").equals("OK")) {
            JSONArray routes = jsonResponse.getJSONArray("routes");
            JSONObject route = routes.getJSONObject(0);
            JSONArray legs = route.getJSONArray("legs");
            JSONObject leg = legs.getJSONObject(0);
            JSONObject duration = leg.getJSONObject("duration");
            String[] durationArray = duration.optString("text").split(" ");
            this.setBESTGUESSTIME(durationArray[0]);
        } else {
            System.err.println(jsonResponse.getString("status"));
        }

        Connection connection2 = HttpConnection.connect(this.DIRECTION_URL);
        connection2.data("key", this.APIKEY);
        connection2.data("origin", this.ORIGIN);
        connection2.data("destination", this.DESTINATION);
        connection2.data("language", this.LANGUAGE);
        connection2.data("departure_time", this.DEPARTURE_TIME);
        connection2.data("traffic_model", this.PESSIMIST_TRAFFIC_MODEL);
        connection2.ignoreContentType(true);
        
        content = connection2.execute().body();
        jsonResponse = new JSONObject(content);
        
        if (jsonResponse.getString("status").equals("OK")) {
            JSONArray routes = jsonResponse.getJSONArray("routes");
            JSONObject route = routes.getJSONObject(0);
            JSONArray legs = route.getJSONArray("legs");
            JSONObject leg = legs.getJSONObject(0);
            JSONObject duration = leg.getJSONObject("duration");
            String[] durationArray = duration.optString("text").split(" ");
            this.setPESSIMISTTIME(durationArray[0]);
        } else {
            System.err.println(jsonResponse.getString("status"));
        }

    }
    
    public void printOptimistPessimist(){
        System.out.println("Unix time : " + this.getDEPARTURE_TIME());
        System.out.println("Date : " + xxx.unixTodate(Long.parseLong(this.getDEPARTURE_TIME())));
        System.out.println("best_guess : " + this.getBESTGUESSTIME() + " menit");
        System.out.println("pessimistic : " +this.getPESSIMISTTIME() + " menit");
        System.out.println(" ");
    }

}
