import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.json.*;
import org.jsoup.*;
import org.jsoup.helper.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author LENOVO
 */
public class JsonClient {
    
    public static final String DIRECTION_URL = "https://maps.googleapis.com/maps/api/directions/json";
    public static final String APIKEY = "AIzaSyAtiAnSkL1NFV7wWpPvunzRgVsn4dJTaPo";
    public static final String ORIGIN = "-6.874705,107.604904";
    public static final String DESTINATION = "-6.924829,107.636533";
    public static final String LANGUAGE = "id";
    
    public static void main(String[] args) throws Exception {
        Connection connection = HttpConnection.connect(DIRECTION_URL);
        connection.data("key", APIKEY);
        connection.data("origin", ORIGIN);
        connection.data("destination", DESTINATION);
        connection.data("language", LANGUAGE);
        connection.ignoreContentType(true);
        String content = connection.execute().body();
        JSONObject jsonResponse = new JSONObject(content);
        if(jsonResponse.getString("status").equals("OK")){
            JSONArray routes = jsonResponse.getJSONArray("routes");
            JSONObject route = routes.getJSONObject(0);
            JSONArray legs = route.getJSONArray("legs");
            JSONObject leg = legs.getJSONObject(0);
            JSONObject distance = leg.getJSONObject("distance");
            JSONObject duration = leg.getJSONObject("duration");
            System.out.println("waktu tempuhnya adalah " + duration.optString("text") + " dengan jarak " + distance.optString("text"));
            System.out.println("alamat awal : " + leg.optString("start_address"));
            System.out.println("alamat tujuan : " + leg.optString("end_address"));
            System.out.println("rute yang dilalui adalah rute " + route.optString("summary"));
            JSONArray steps = leg.getJSONArray("steps");
            System.out.println("");
            System.out.println("instuksi rute : ");
            for (int i = 0; i < steps.length(); i++) {
                JSONObject step = steps.getJSONObject(i);
                System.out.println(i+ " " + step.optString("html_instructions"));
            }
        }else{
            System.err.println(jsonResponse.getString("status"));
        }
    }
}
