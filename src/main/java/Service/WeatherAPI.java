package Service;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by ilmaz on 13.10.16.
 */
public class WeatherAPI {
    private static final String KEY = "874d54c18aa696a5b0a7921b50e35a73";

    public static String getTemperature(String city){
        BufferedReader reader = null;
        String temp = "";
        try {
            URL url = new URL("http://api.openweathermap.org/data/2.5/find?q=" + city + "&units=metric&appid=" + KEY);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(10000);
            connection.connect();

            reader = new BufferedReader( new InputStreamReader(connection.getInputStream()));

            StringBuffer buffer = new StringBuffer();
            String line;
            while ((line = reader.readLine()) != null){
                buffer.append(line);
            }

            JSONParser parser = new JSONParser();
            JSONObject json;
            json = (JSONObject) parser.parse(buffer.toString());
            JSONArray list = (JSONArray) json.get("list");
            JSONObject obj = (JSONObject) list.get(0);
            JSONObject main = (JSONObject) obj.get("main");
            temp = main.get("temp").toString();
            return temp + "°C";
        } catch (NullPointerException e) {
            e.printStackTrace();
        }catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return temp;
    }
}
