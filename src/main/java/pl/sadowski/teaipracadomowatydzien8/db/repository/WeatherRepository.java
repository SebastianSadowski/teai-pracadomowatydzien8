package pl.sadowski.teaipracadomowatydzien8.db.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.sadowski.teaipracadomowatydzien8.db.model.City;
import pl.sadowski.teaipracadomowatydzien8.db.model.WeatherInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Repository
public interface WeatherRepository extends JpaRepository<WeatherInfo, Long> {

    @Query("SELECT c.country, c.name as cityName, w.temperature FROM WeatherInfo w JOIN City c ON  w.id = c.weatherInfo.id ")
    Set<Map<String, Object>> findAllWeathers();

}
