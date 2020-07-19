package pl.sadowski.teaipracadomowatydzien8.client.weather.service;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriBuilder;
import pl.sadowski.teaipracadomowatydzien8.client.weather.model.Weather;

import java.net.URI;
import java.net.URISyntaxException;


@Service
public class WeatherService {


    public void getWeatherForCity(String cityName) throws URISyntaxException {
        NameValuePair nameValuePair = new BasicNameValuePair("param1", cityName);
       URI uri = new URIBuilder()
                .setScheme("http")
                .setHost("myWebinfos.com")
                .setPathSegments("pierwszyPathSegment", "drugiPathSegment", "trzeciPathSegment")
                .setParameter(nameValuePair.getName(),nameValuePair.getValue())
                .build();

        System.out.println(uri.toString());

    }


    @EventListener(ApplicationReadyEvent.class)
    public void test() {
        try {
            getWeatherForCity("RADOM");
        } catch (URISyntaxException e) {
            System.out.println(e.getMessage());
        }
    }
}
