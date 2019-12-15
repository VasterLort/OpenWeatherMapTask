package by.jnetworks.util;

public class ConstantStorage {
    private ConstantStorage() {
    }

    public static final String JSON_WEATHER = "https://api.openweathermap.org/data/2.5/weather?q=Minsk,by&appid=e6021436b583cf2c896f2f1eba5e47c7";
    public static final String CURRENT_DATE = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
    public static final String GET_REQUEST = "GET";

    public static final String CLASS_COORD = "coord";
    public static final String CLASS_WEATHER = "weather";
    public static final String CLASS_MAIN = "main";
    public static final String CLASS_WIND = "wind";
    public static final String CLASS_CLOUDS = "clouds";
    public static final String CLASS_SYS = "sys";

    public static final String FIELD_BASE_CURRENT_WEATHER_DTO = "base";
    public static final String FIELD_VISIBILITY_CURRENT_WEATHER_DTO = "visibility";
    public static final String FIELD_DT_CURRENT_WEATHER_DTO = "dt";
    public static final String FIELD_TIMEZONE_CURRENT_WEATHER_DTO = "timezone";
    public static final String FIELD_ID_CURRENT_WEATHER_DTO = "id";
    public static final String FIELD_NAME_CURRENT_WEATHER_DTO = "name";
    public static final String FIELD_COD_CURRENT_WEATHER_DTO = "cod";
    public static final String FIELD_LON_COORD = "lon";
    public static final String FIELD_LAT_COORD = "lat";
    public static final String FIELD_TEMP_MAIN = "temp";
    public static final String FIELD_PRESSURE_MAIN = "pressure";
    public static final String FIELD_HUMIDITY_MAIN = "humidity";
    public static final String FIELD_TEMP_MIN_MAIN = "temp_min";
    public static final String FIELD_TEMP_MAX_MAIN = "temp_max";
    public static final String FIELD_SPEED_WIND = "speed";
    public static final String FIELD_DEG_WIND = "deg";
    public static final String FIELD_ALL_CLOUDS = "all";
    public static final String FIELD_TYPE_SYS = "type";
    public static final String FIELD_ID_SYS = "id";
    public static final String FIELD_COUNTRY_SYS = "country";
    public static final String FIELD_SUNRISE_SYS = "sunrise";
    public static final String FIELD_SUNSET_SYS = "sunset";
    public static final String FIELD_ID_WEATHER = "id";
    public static final String FIELD_MAIN_WEATHER = "main";
    public static final String FIELD_DESCRIPTION_WEATHER = "description";
    public static final String FIELD_ICON_WEATHER = "icon";
}
