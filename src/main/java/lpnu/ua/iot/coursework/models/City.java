package lpnu.ua.iot.coursework.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class City {
    private int id;
    private String name;
    private Country country;
    private List<Weather> weatherList;
    private List<WeatherAlert> weatherAlertList;
}
