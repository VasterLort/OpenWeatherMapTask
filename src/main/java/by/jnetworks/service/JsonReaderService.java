package by.jnetworks.service;

import by.jnetworks.pojo.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class JsonReaderService {

    public String jsonReader(String JSON_WEATHER){
        String inline = "";

        try {
            URL url = new URL(JSON_WEATHER);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int responsecode = conn.getResponseCode();
            System.out.println("Response code is: " + responsecode);

            if (responsecode != 200)
                throw new RuntimeException("HttpResponseCode: " + responsecode);
            else {
                Scanner sc = new Scanner(url.openStream());
                while (sc.hasNext()) {
                    inline += sc.nextLine();
                }
                sc.close();
            }

            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return inline;
    }

    public CurrentWeather jsonParser(String inline){
        CurrentWeather currentWeather = new CurrentWeather();
        try {
            Object obj = new JSONParser().parse(inline);
            JSONObject jo = (JSONObject) obj;

            JSONObject coordJson = (JSONObject) jo.get("coord");
            JSONArray weatherList = (JSONArray) jo.get("weather");
            JSONObject mainJson = (JSONObject) jo.get("main");
            JSONObject windJson = (JSONObject) jo.get("wind");
            JSONObject cloudsJson = (JSONObject) jo.get("clouds");
            JSONObject sysJson = (JSONObject) jo.get("sys");
            Coord coord = new Coord((Double) coordJson.get("lon"), (Double) coordJson.get("lat"));
            Main main = new Main((Double) mainJson.get("temp"), (Long) mainJson.get("pressure"),
                    (Long) mainJson.get("humidity"), (Double) mainJson.get("temp_min"), (Double) mainJson.get("temp_max"));
            Wind wind = new Wind((Long) windJson.get("speed"), (Long) windJson.get("deg"));
            Clouds clouds = new Clouds((Long) cloudsJson.get("all"));
            Sys sys = new Sys((Long) sysJson.get("type"), (Long) sysJson.get("id"),
                    (String) sysJson.get("country"), (Long) sysJson.get("sunrise"), (Long) sysJson.get("sunset"));


            List<Weather> weathers = new ArrayList<>();
            for (int i = 0; i < weatherList.size(); i++) {

                JSONObject weatherJson = (JSONObject) weatherList.get(i);
                weathers.add(new Weather((Long) weatherJson.get("id"), (String) weatherJson.get("main"),
                        (String) weatherJson.get("description"), (String) weatherJson.get("icon")));
            }

            currentWeather = new CurrentWeather(coord, weathers, (String) jo.get("base"), main,
                    (Long) jo.get("visibility"), wind, clouds, (Long) jo.get("dt"), sys, (Long) jo.get("timezone"),
                    (Long) jo.get("id"), (String) jo.get("name"), (Long) jo.get("cod"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return currentWeather;
    }
}
