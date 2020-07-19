package pl.sadowski.teaipracadomowatydzien8.db.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name="weathers")
public class WeatherInfo {

    @Id
    @GeneratedValue()
    private long id;

    @OneToOne(mappedBy = "weatherInfo")
    private City city;
    @NonNull private Integer temperature;
    @NonNull private String weatherIcon;
    @NonNull private Integer windSpeed;
    @NonNull private String windDir;
    @NonNull private Integer pressure;
    @NonNull private Integer humidity;
    @NonNull private Integer cloudCover;



}



