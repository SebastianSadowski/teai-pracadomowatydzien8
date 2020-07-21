package pl.sadowski.teaipracadomowatydzien8.client.weather.model;

import lombok.*;

import java.util.List;

@Getter
@ToString
public class WeatherBasic {
    WeatherBasic(WeatherBasicBuilder weatherBasicBuilder) {
        this.country = weatherBasicBuilder.country;
        this.city = weatherBasicBuilder.city;
        this.temperature = weatherBasicBuilder.temperature;
        this.weatherIcons = weatherBasicBuilder.weatherIcons;
        this.windSpeed = weatherBasicBuilder.windSpeed;
        this.windDir = weatherBasicBuilder.windDir;
        this.pressure = weatherBasicBuilder.pressure;
        this.humidity = weatherBasicBuilder.humidity;
        this.cloudCover = weatherBasicBuilder.cloudCover;
    }

    private final String country;
    private final String city;
    private final Integer temperature;
    private final List<String> weatherIcons;
    private final Integer windSpeed;
    private final String windDir;
    private final Integer pressure;
    private final Integer humidity;
    private final Integer cloudCover;


    public static class WeatherBasicBuilder {

        private String country;
        private String city;
        private Integer temperature;
        private List<String> weatherIcons;
        private Integer windSpeed;
        private String windDir;
        private Integer pressure;
        private Integer humidity;
        private Integer cloudCover;

        public WeatherBasicBuilder buildCountry(String country) {
            this.country = country;
            return this;
        }

        public WeatherBasicBuilder buildCity(String city) {
            this.city = city;
            return this;
        }

        public WeatherBasicBuilder buildTemperature(Integer temperature) {
            this.temperature = temperature;
            return this;
        }

        public WeatherBasicBuilder buildWeatherIcon(List<String> weatherIcons) {
            this.weatherIcons = weatherIcons;
            return this;
        }

        public WeatherBasicBuilder buildWindSpeed(Integer windSpeed) {
            this.windSpeed = windSpeed;
            return this;
        }

        public WeatherBasicBuilder buildWindDir(String windDir) {
            this.windDir = windDir;
            return this;
        }

        public WeatherBasicBuilder buildPressure(Integer pressure) {
            this.pressure = pressure;
            return this;
        }

        public WeatherBasicBuilder buildHumidity(Integer humidity) {
            this.humidity = humidity;
            return this;
        }

        public WeatherBasicBuilder buildCloudCover(Integer cloudCover) {
            this.cloudCover = cloudCover;
            return this;
        }

        public WeatherBasic build() {
            return new WeatherBasic(this);
        }
    }
}
