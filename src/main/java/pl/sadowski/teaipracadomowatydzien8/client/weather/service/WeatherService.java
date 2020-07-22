package pl.sadowski.teaipracadomowatydzien8.client.weather.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.sadowski.teaipracadomowatydzien8.client.weather.model.WeatherBasic;
import pl.sadowski.teaipracadomowatydzien8.client.weather.model.weatherApiResponse.WeatherFull;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@Service
@Log4j2
@AllArgsConstructor
@NoArgsConstructor
public class WeatherService {

    @Value("${weatherstack_acces_key}")
    private String accesKey;

    public Optional<WeatherBasic> getWeatherForCity(String cityName) throws URISyntaxException {
        URI uri = buildURI(cityName);
        ResponseEntity<WeatherFull> entity = askWeatherAPI(uri);
        WeatherFull weather = entity.getBody();

        return getWeatherFromRequest(weather);
    }





    protected Optional<WeatherBasic> getWeatherFromRequest(@Nullable WeatherFull weatherFull) {
        return Optional.ofNullable(
                new WeatherBasic.WeatherBasicBuilder()
                        .buildCountry(weatherFull.getLocation().getCountry())
                        .buildCity(weatherFull.getLocation().getName())
                        .buildTemperature(weatherFull.getCurrent().getTemperature())
                        .buildPressure(weatherFull.getCurrent().getPressure())
                        .buildWindSpeed(weatherFull.getCurrent().getWindSpeed())
                        .buildWindDir(weatherFull.getCurrent().getWindDir())
                        .buildHumidity(weatherFull.getCurrent().getHumidity())
                        .buildCloudCover(weatherFull.getCurrent().getCloudcover())
                        .buildWeatherIcon(weatherFull.getCurrent().getWeatherIcons())
                        .build());
    }

    protected URI buildURI(String cityName) throws URISyntaxException {
        NameValuePair[] nameValuePairs = {new BasicNameValuePair("access_key", accesKey), new BasicNameValuePair("query", cityName)};
        URI uri = new URIBuilder()
                .setScheme("http")
                .setHost("api.weatherstack.com")
                .setPath("current")
                .setParameters(nameValuePairs)
                .build();
        log.info(uri.toString());
        return uri;
    }

    protected ResponseEntity<WeatherFull> askWeatherAPI(URI uri) {
        ResponseEntity<WeatherFull> entity = new RestTemplate().exchange(uri, HttpMethod.GET, null, WeatherFull.class);
        log.info(entity.hasBody());
        log.info(entity.getStatusCode());
        log.info(entity.getBody().toString());
        return entity.hasBody() ? entity : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
