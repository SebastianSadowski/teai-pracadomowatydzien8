package pl.sadowski.teaipracadomowatydzien8.client.weather.controller;


import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.sadowski.teaipracadomowatydzien8.client.weather.model.WeatherBasic;
import pl.sadowski.teaipracadomowatydzien8.client.weather.service.WeatherService;

import java.net.URISyntaxException;
import java.util.Optional;

@Controller
@Log4j2
public class WeatherClient {


    WeatherService weatherService;

    @Autowired
    public WeatherClient(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    public Optional<WeatherBasic> getWeatherBasic(String cityName){
        Optional<WeatherBasic> wb = null;
try {
  wb =  weatherService.getWeatherForCity(cityName);

}catch(URISyntaxException e){
    log.error("popraw składnie miasta");
    log.error(e.getReason());
}
        return wb;
    }

}
