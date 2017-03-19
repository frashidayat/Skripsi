
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    private final DateFormat timeFormat = new SimpleDateFormat("HH");
    private final String DIRECTION_URL = "https://maps.googleapis.com/maps/api/directions/json";
    private final String APIKEY = "AIzaSyAtiAnSkL1NFV7wWpPvunzRgVsn4dJTaPo";
    private final String LANGUAGE = "id";
    private final String OPTIMIST_TRAFFIC_MODEL = "optimistic";
    private final String PESSIMIST_TRAFFIC_MODEL = "pessimistic";
    private final String DEPARTURE_TIME;
    private final String ORIGIN;// = "ChIJbYmcEu7maC4RRijB2oKhHLA"; //unpar
    private final String DESTINATION;// = "ChIJQ2lnJR3oaC4RhajeHgXKUdE"; //puspa utara
    private String PESSIMISTTIME;
    private String OPTIMISTTIME;

    public DurationTimeExtractor(Long departureTime, String origin, String destination) throws ParseException {
        this.DEPARTURE_TIME = Long.toString(departureTime);
        System.out.println(this.DEPARTURE_TIME);
        this.ORIGIN = origin;
        this.DESTINATION = destination;
    }

    public void setPESSIMISTTIME(String PESSIMISTTIME) {
        this.PESSIMISTTIME = PESSIMISTTIME;
    }

    public void setOPTIMISTTIME(String OPTIMISTTIME) {
        this.OPTIMISTTIME = OPTIMISTTIME;
    }

    public String getPESSIMISTTIME() {
        return PESSIMISTTIME;
    }

    public String getOPTIMISTTIME() {
        return OPTIMISTTIME;
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
            this.setOPTIMISTTIME(durationArray[0]);
            System.out.println(durationArray[0]);
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
            System.out.println(durationArray[0]);
        } else {
            System.err.println(jsonResponse.getString("status"));
        }

    }

}
