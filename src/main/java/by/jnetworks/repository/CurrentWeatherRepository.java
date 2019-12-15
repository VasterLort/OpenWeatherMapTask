package by.jnetworks.repository;

import by.jnetworks.pojo.CurrentWeather;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrentWeatherRepository extends MongoRepository<CurrentWeather, String> {
}