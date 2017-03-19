
import java.util.Date;
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
    public static final String SAMPEL1 = "-6.9536001,107.6193958";
    public static final String SAMPEL2 = "-6.937021,107.6643817";
    public static final String ORIGIN = "-6.8746025,107.6024968";
    public static final String LANGUAGE = "id";
    public static final String PARAMETER1 = "pessimistic";
    public static final String PARAMETER2 = "optimistic";

    public static void main(String[] args) throws Exception {
        //Long now = (new Date().getTime()+3600)/1000;

        Connection connection = HttpConnection.connect(DIRECTION_URL);
        connection.data("key", APIKEY);
        connection.data("origin", ORIGIN);
        //connection.data("destination", SAMPEL2);
        connection.data("destination", SAMPEL1);
        connection.data("language", LANGUAGE);
        //connection.data("departure_time", now.toString());
        //connection.data("traffic_model", PARAMETER1);
        //connection.data("traffic_model", PARAMETER2);
        connection.ignoreContentType(true);
        String content = connection.execute().body();
        JSONObject jsonResponse = new JSONObject(content);
        if (jsonResponse.getString("status").equals("OK")) {
            JSONArray routes = jsonResponse.getJSONArray("routes");
            JSONObject route = routes.getJSONObject(0);
            JSONArray legs = route.getJSONArray("legs");
            JSONObject leg = legs.getJSONObject(0);
            //JSONObject distance = leg.getJSONObject("distance");
            JSONObject duration = leg.getJSONObject("duration");
            System.out.println("waktu tempuhnya adalah " + duration.optString("text"));// + " dengan jarak " + distance.optString("text"));
            //System.out.println("alamat awal : " + leg.optString("start_address"));
            //System.out.println("alamat tujuan : " + leg.optString("end_address"));
            //System.out.println("rute yang dilalui adalah rute " + route.optString("summary"));
            //JSONArray steps = leg.getJSONArray("steps");
            //System.out.println("");
            //System.out.println("instuksi rute : ");
            //for (int i = 0; i < steps.length(); i++) {
            //JSONObject step = steps.getJSONObject(i);
            //System.out.println(i+ " " + step.optString("html_instructions"));
            //}
        } else {
            System.err.println(jsonResponse.getString("status"));
        }
    }
}
