package by.jnetworks.service;

import by.jnetworks.pojo.*;
import by.jnetworks.repository.CurrentWeatherRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static by.jnetworks.util.ConstantStorage.*;

@Service
public class JsonReaderService {
    @Autowired
    private CurrentWeatherRepository repository;

    public String jsonReader() {
        String inline = "";

        try {
            URL url = new URL(JSON_WEATHER);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(GET_REQUEST);
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

    public CurrentWeatherDto jsonParser(String inline) {
        Object obj = null;
        try {
            obj = new JSONParser().parse(inline);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        JSONObject jo = (JSONObject) obj;
        JSONObject coordJson = (JSONObject) jo.get(CLASS_COORD);
        JSONArray weatherList = (JSONArray) jo.get(CLASS_WEATHER);
        JSONObject mainJson = (JSONObject) jo.get(CLASS_MAIN);
        JSONObject windJson = (JSONObject) jo.get(CLASS_WIND);
        JSONObject cloudsJson = (JSONObject) jo.get(CLASS_CLOUDS);
        JSONObject sysJson = (JSONObject) jo.get(CLASS_SYS);

        Coord coord = new Coord((Double) coordJson.get(FIELD_LON_COORD), (Double) coordJson.get(FIELD_LAT_COORD));
        Main main = new Main((Double) mainJson.get(FIELD_TEMP_MAIN), (Long) mainJson.get(FIELD_PRESSURE_MAIN),
                (Long) mainJson.get(FIELD_HUMIDITY_MAIN), (Double) mainJson.get(FIELD_TEMP_MIN_MAIN), (Double) mainJson.get(FIELD_TEMP_MAX_MAIN));
        Wind wind = new Wind((Long) windJson.get(FIELD_SPEED_WIND), (Long) windJson.get(FIELD_DEG_WIND));
        Clouds clouds = new Clouds((Long) cloudsJson.get(FIELD_ALL_CLOUDS));
        Sys sys = new Sys((Long) sysJson.get(FIELD_TYPE_SYS), (Long) sysJson.get(FIELD_ID_SYS),
                (String) sysJson.get(FIELD_COUNTRY_SYS), (Long) sysJson.get(FIELD_SUNRISE_SYS), (Long) sysJson.get(FIELD_SUNSET_SYS));


        List<Weather> weathers = new ArrayList<>();
        for (int i = 0; i < weatherList.size(); i++) {
            JSONObject weatherJson = (JSONObject) weatherList.get(i);
            weathers.add(new Weather((Long) weatherJson.get(FIELD_ID_WEATHER), (String) weatherJson.get(FIELD_MAIN_WEATHER),
                    (String) weatherJson.get(FIELD_DESCRIPTION_WEATHER), (String) weatherJson.get(FIELD_ICON_WEATHER)));
        }

        CurrentWeatherDto currentWeatherDto = new CurrentWeatherDto(coord, weathers, (String) jo.get(FIELD_BASE_CURRENT_WEATHER_DTO), main,
                (Long) jo.get(FIELD_VISIBILITY_CURRENT_WEATHER_DTO), wind, clouds, (Long) jo.get(FIELD_DT_CURRENT_WEATHER_DTO), sys,
                (Long) jo.get(FIELD_TIMEZONE_CURRENT_WEATHER_DTO), (Long) jo.get(FIELD_ID_CURRENT_WEATHER_DTO), (String) jo.get(FIELD_NAME_CURRENT_WEATHER_DTO),
                (Long) jo.get(FIELD_COD_CURRENT_WEATHER_DTO));


        return currentWeatherDto;
    }

    public void save(CurrentWeatherDto currentWeatherDto, String dateFormat) {
        CurrentWeather currentWeather = new CurrentWeather();
        currentWeather.setCurrentDate(dateFormat);
        currentWeather.setStatus(currentWeatherDto.getCod());
        currentWeather.setCurrentWeatherDto(currentWeatherDto);
        repository.insert(currentWeather);
    }
}
