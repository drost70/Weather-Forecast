package lpnu.ua.iot.coursework.storage;

import lpnu.ua.iot.coursework.models.Weather;

import java.util.List;

public interface WeatherStorage {
    void addWeather(Weather weather);
    Weather getWeatherById(int id);
    List<Weather> getAllWeathers();
    Weather updateWeather(int id, Weather weather);
    boolean deleteWeather(int id);
}
