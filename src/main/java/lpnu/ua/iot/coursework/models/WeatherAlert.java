package lpnu.ua.iot.coursework.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherAlert {
    private int id;
    private String type;
    private String severityLevel;
    private String date;
    private String description;
}


