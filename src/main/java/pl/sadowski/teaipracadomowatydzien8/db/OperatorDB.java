package pl.sadowski.teaipracadomowatydzien8.db;


import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.sadowski.teaipracadomowatydzien8.client.weather.controller.WeatherClient;
import pl.sadowski.teaipracadomowatydzien8.client.weather.model.WeatherBasic;
import pl.sadowski.teaipracadomowatydzien8.db.model.City;
import pl.sadowski.teaipracadomowatydzien8.db.model.WeatherInfo;
import pl.sadowski.teaipracadomowatydzien8.db.repository.CityRepository;
import pl.sadowski.teaipracadomowatydzien8.db.repository.WeatherRepository;

import java.text.SimpleDateFormat;
import java.util.*;

@Log4j2
@Component
public class OperatorDB {

    WeatherClient weatherClient;
    CityRepository cityRepository;
    WeatherRepository weatherRepository;

    @Autowired
    public OperatorDB(WeatherClient weatherClient, CityRepository cityRepository, WeatherRepository weatherRepository) {
        this.weatherClient = weatherClient;
        this.cityRepository = cityRepository;
        this.weatherRepository = weatherRepository;
    }

    public void saveDataToDB(String cityname) {

        WeatherInfo weatherInfo = new WeatherInfo();
        City city;
        Date timestamp = parseDate();
        Optional<WeatherBasic> weather = weatherClient.getWeatherBasic(cityname);

        //parse
        weatherInfo = weather.map(w -> new WeatherInfo(timestamp, w.getTemperature(), w.getWeatherIcons().toString(), w.getWindSpeed(),
                w.getWindDir(), w.getPressure(), w.getHumidity(), w.getCloudCover()))
                .orElse(weatherInfo.weatherInfoNotAvailable());

        city = weather.map(w -> new City(w.getCity(), w.getCountry())).orElse(new City());
        city.setWeatherInfo(weatherInfo);

        //check whether weather for specyfic date exists


        //save to data bases
        if(checkIfRecordExists(cityname, timestamp)) {
            weatherRepository.save(weatherInfo);
            cityRepository.save(city);
        }

    }

    @SneakyThrows
    private Date parseDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:00");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Warsaw"));
        String s = simpleDateFormat.format(new Date());
        return simpleDateFormat.parse(s);
    }

    private boolean checkIfRecordExists(String name, Date timestamp){
        Set<Date> timestamps = cityRepository.findAllTimestampsForCity(name);
        return timestamps.stream().noneMatch(t -> t.compareTo(timestamp) == 0);

    }

}


