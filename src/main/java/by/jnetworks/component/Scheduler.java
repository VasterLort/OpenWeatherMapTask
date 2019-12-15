package by.jnetworks.component;

import by.jnetworks.pojo.CurrentWeatherDto;
import by.jnetworks.service.JsonReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

import static by.jnetworks.util.ConstantStorage.CURRENT_DATE;

@Component
public class Scheduler {

    @Autowired
    private JsonReaderService service;

    @Scheduled(fixedRate = 10000)
    public void fixedRateSch() {
        CurrentWeatherDto currentWeatherDto = null;
        String inline = service.jsonReader();
        SimpleDateFormat sdf = new SimpleDateFormat(CURRENT_DATE);
        Date now = new Date();
        String strDate = "ISODate(" + sdf.format(now) + ")";
        if (!inline.equals("")) {
            currentWeatherDto = service.jsonParser(inline);
            System.out.println(strDate);
            System.out.println(currentWeatherDto.toString());
        }

        if (currentWeatherDto != null) {
            service.save(currentWeatherDto, strDate);
        }
    }
}
