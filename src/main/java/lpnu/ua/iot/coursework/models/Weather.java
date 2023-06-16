package lpnu.ua.iot.coursework.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Weather {
    private int id;
    private String condition;
    private double temperature;
    private String date;
    private String weatherReport;
}
