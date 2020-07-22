package pl.sadowski.teaipracadomowatydzien8.api;

import lombok.Getter;
import lombok.Setter;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.sadowski.teaipracadomowatydzien8.ScheduledStart;
import pl.sadowski.teaipracadomowatydzien8.db.model.City;
import pl.sadowski.teaipracadomowatydzien8.db.repository.CityRepository;

import java.util.*;

@RestController
@RequestMapping("/city")
public class CityApi {

    ScheduledStart scheduledStart;
    CityRepository cityRepository;

    @Autowired
    public CityApi(ScheduledStart scheduledStart, CityRepository cityRepository) {
        this.scheduledStart = scheduledStart;
        this.cityRepository = cityRepository;
    }

    @GetMapping("/")
    public List<String> getCityList() {
        return Lists.newArrayList(scheduledStart.getCities());
    }

    @PostMapping("/{city}")
    public void addCity(@PathVariable(name = "city") String cityName) {
//        scheduledStart.
        System.out.println(cityName);
        Set<String> cities = scheduledStart.getCities();
        cities.add(cityName);
        scheduledStart.setCities(cities);
    }

    @DeleteMapping("/{cityName}")
    public void removeCity(@PathVariable String cityName) {

        System.out.println(cityName);
        Set<String> cities = scheduledStart.getCities();
        cities.add(cityName);
        scheduledStart.setCities(cities);
    }

    @GetMapping("/do")
    public List<City> doStart() {
        scheduledStart.start();
        return cityRepository.findAll();
    }
}
