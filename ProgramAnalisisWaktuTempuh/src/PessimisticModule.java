
import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class PessimisticModule extends Thread {

    private final String DIRECTION_URL = "https://maps.googleapis.com/maps/api/directions/json";
    private final String APIKEY = "AIzaSyAtiAnSkL1NFV7wWpPvunzRgVsn4dJTaPo";
    private final String LANGUAGE = "id";
    private final String TRAFFIC_MODEL = "pessimistic";
    private final String DEPARTURE_TIME;
    private final String ORIGIN;// = "ChIJbYmcEu7maC4RRijB2oKhHLA"; //unpar
    private final String DESTINATION;// = "ChIJQ2lnJR3oaC4RhajeHgXKUdE"; //puspa utara
    private JSONObject JSONRespond;

    public PessimisticModule(String departureTime, String origin, String destination){
        this.DEPARTURE_TIME = departureTime;
        this.ORIGIN = origin;
        this.DESTINATION = destination;
    }

    public void setJSONResponse(JSONObject JSONRespond) {
        this.JSONRespond = JSONRespond;
    }

    public JSONObject getJSONResponse() {
        return this.JSONRespond;
    }

    @Override
    public void run() {
        try {
            Connection connection = HttpConnection.connect(this.DIRECTION_URL);
            connection.data("key", this.APIKEY);
            connection.data("origin", this.ORIGIN);
            connection.data("destination", this.DESTINATION);
            connection.data("language", this.LANGUAGE);
            connection.data("departure_time", this.DEPARTURE_TIME);
            connection.data("traffic_model", this.TRAFFIC_MODEL);
            connection.ignoreContentType(true);
            String content;
            content = connection.execute().body();
            JSONObject jsonResponse = new JSONObject(content);
            this.setJSONResponse(jsonResponse);
        } catch (IOException ex) {
            Logger.getLogger(OptimisticModule.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
