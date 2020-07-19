package pl.sadowski.teaipracadomowatydzien8.db.model;


import lombok.*;


import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@ToString(exclude = {"weatherInfo"})
@Getter
@Setter
@Entity
@Table(name = "cities")
public class City {

    @Id
    @GeneratedValue()
    private long id;

    @OneToOne
    private WeatherInfo weatherInfo;
    @NonNull private String name;
    @NonNull private String country;


}
