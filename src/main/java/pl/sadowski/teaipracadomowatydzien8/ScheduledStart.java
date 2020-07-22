package pl.sadowski.teaipracadomowatydzien8;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.sadowski.teaipracadomowatydzien8.client.weather.controller.WeatherClient;
import pl.sadowski.teaipracadomowatydzien8.db.OperatorDB;

import java.util.*;

@Component
@EnableScheduling
public class ScheduledStart {

    @Setter
    @Getter
    private Set<String> cities = new TreeSet<>();


    OperatorDB parser;
    WeatherClient weatherClient;

    @Autowired
    public ScheduledStart(OperatorDB parser, WeatherClient weatherClient) {
        this.parser = parser;
        this.weatherClient = weatherClient;
    }

    @Scheduled(fixedDelay = 60 * 60 * 1000)
    public void start() {
        cities.forEach(parser::saveDataToDB);
    }



}
