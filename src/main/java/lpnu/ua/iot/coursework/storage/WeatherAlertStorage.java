package lpnu.ua.iot.coursework.storage;

import lpnu.ua.iot.coursework.models.WeatherAlert;

import java.util.List;

public interface WeatherAlertStorage {
    void addWeatherAlert(WeatherAlert weatherAlert);

    WeatherAlert getWeatherAlertById(int id);

    List<WeatherAlert> getAllWeatherAlerts();

    WeatherAlert updateWeatherAlert(int id, WeatherAlert weatherAlert);

    boolean deleteWeatherAlert(int id);
}
