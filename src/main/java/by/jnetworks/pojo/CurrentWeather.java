package by.jnetworks.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "openweathermap")
public class CurrentWeather {
    @Id
    private ObjectId idObj;
    @Field(value = "response_date")
    private String currentDate;
    @Field(value = "response_status")
    private Long status;
    @Field(value = "response_body")
    private CurrentWeatherDto currentWeatherDto;
}
