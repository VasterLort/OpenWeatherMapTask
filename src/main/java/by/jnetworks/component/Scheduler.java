package by.jnetworks.component;

import by.jnetworks.pojo.CurrentWeather;
import by.jnetworks.service.JsonReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class Scheduler {

    private static final String JSON_WEATHER = "https://api.openweathermap.org/data/2.5/weather?q=Minsk,by&appid=e6021436b583cf2c896f2f1eba5e47c7";

    @Autowired
    private JsonReaderService service;

    @Scheduled(fixedRate = 5000)
    public void fixedRateSch() {
        CurrentWeather currentWeather;
        String inline = service.jsonReader(JSON_WEATHER);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date now = new Date();
        String strDate = sdf.format(now);
        if (!inline.equals("")){
            currentWeather = service.jsonParser(inline);
            System.out.println(strDate);
            System.out.println(currentWeather.toString());
        }
    }
}
