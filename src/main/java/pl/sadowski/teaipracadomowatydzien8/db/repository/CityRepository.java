package pl.sadowski.teaipracadomowatydzien8.db.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.sadowski.teaipracadomowatydzien8.db.model.City;
import pl.sadowski.teaipracadomowatydzien8.db.model.WeatherInfo;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Long> {


    @Query("SELECT w FROM City c JOIN fetch WeatherInfo w ON c.weatherInfo.id = w.id WHERE c.name = :cityname ")
    WeatherInfo findWeatherForCity(@Param("cityname") String cityname);

    @Query("SELECT c FROM City c LEFT JOIN fetch c.weatherInfo")
    List<City> findAll();
}
