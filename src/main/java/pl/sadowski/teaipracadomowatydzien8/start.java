package pl.sadowski.teaipracadomowatydzien8;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Component;
import pl.sadowski.teaipracadomowatydzien8.db.model.City;
import pl.sadowski.teaipracadomowatydzien8.db.model.WeatherInfo;
import pl.sadowski.teaipracadomowatydzien8.db.repository.CityRepository;
import pl.sadowski.teaipracadomowatydzien8.db.repository.WeatherRepository;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Component
public class start {

    WeatherRepository weatherRepository;
    CityRepository cityRepository;
@Autowired
    public start(WeatherRepository weatherRepository, CityRepository cityRepository) {
    this.cityRepository = cityRepository;
    this.weatherRepository = weatherRepository;
    }

    //@EventListener(ApplicationReadyEvent.class)
    public void start(){

    City cityRadom = new City("Radom", "Poland");
    City cityKrakow = new City("Kraków", "Poland");
    City cityWarszawa = new City("Warszawa", "Poland");
    City cityLublin = new City("Warszawa", "Poland");



        WeatherInfo weatherInfo = new WeatherInfo(1111, "DU{PA", 1012, "west", 1128, 650, 50);
        WeatherInfo weatherInfo2 = new WeatherInfo(2222, "DU{PA", 1012, "west", 1128, 650, 50);
        WeatherInfo weatherInfo3 = new WeatherInfo(3333, "DU{PA", 1012, "west", 1128, 650, 50);
        WeatherInfo weatherInfo4 = new WeatherInfo(3333, "DU{PA", 1012, "west", 1128, 650, 50);
        cityRadom.setWeatherInfo(weatherInfo);
        cityKrakow.setWeatherInfo(weatherInfo3);
        cityWarszawa.setWeatherInfo(weatherInfo2);
//        weatherInfo.setCity(radom);
//        weatherInfo2.setCity(krakow);
//        weatherInfo3.setCity(warszawa);

            weatherRepository.saveAll(Stream.of(weatherInfo, weatherInfo2, weatherInfo3, weatherInfo4).collect(Collectors.toSet()));
        cityRepository.saveAll(Set.of(cityRadom, cityKrakow, cityWarszawa, cityLublin));

//        System.out.println(cityRepository.findWeatherForCity("Radom").toString());
        cityRepository.findAll().forEach(System.out::println);
    }}