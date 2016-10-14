package Service;

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
 * Created by ilmaz on 14.10.16.
 */
public class LastFmAPI {
    private static final String KEY = "3aa3829cc3c67428c53c345d6e25ac21";

    public static String getListenersCount(String name){
        BufferedReader reader = null;
        String listeners = "";
        try {
            URL url = new URL("http://ws.audioscrobbler.com/2.0/?method=artist.getinfo&artist=" + name + "&api_key=" + KEY + "&format=json");
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
            JSONObject artist = (JSONObject) json.get("artist");
            JSONObject stats = (JSONObject) artist.get("stats");
            listeners = stats.get("listeners").toString();
            return listeners;
        } catch (NullPointerException e) {
            e.printStackTrace();
        }catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return listeners;
    }
}
