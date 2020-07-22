package pl.sadowski.teaipracadomowatydzien8.db.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.sadowski.teaipracadomowatydzien8.db.model.City;
import pl.sadowski.teaipracadomowatydzien8.db.model.WeatherInfo;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface CityRepository extends JpaRepository<City, Long> {


    @Query("SELECT w FROM City c JOIN fetch WeatherInfo w ON c.weatherInfo.id = w.id WHERE c.name = :cityname ")
    WeatherInfo findWeatherForCity(@Param("cityname") String cityname);

    @Query("SELECT c FROM City c LEFT JOIN fetch c.weatherInfo")
    List<City> findAll();

    @Query("SELECT w.timestamp FROM City c JOIN FETCH WeatherInfo w ON c.weatherInfo.id = w.id WHERE c.name=:cityname")
    Set<Date> findAllTimestampsForCity(@Param("cityname") String name);
}
