package lpnu.ua.iot.coursework.services;

import lpnu.ua.iot.coursework.models.WeatherAlert;
import lpnu.ua.iot.coursework.storage.WeatherAlertStorage;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeatherAlertService {
    private final WeatherAlertStorage weatherAlertStorage;

    public WeatherAlertService(final WeatherAlertStorage weatherAlertStorage) {
        this.weatherAlertStorage = weatherAlertStorage;
    }

    public void addWeatherAlert(final WeatherAlert weatherAlert) {
        weatherAlertStorage.addWeatherAlert(weatherAlert);
    }

    public WeatherAlert getWeatherAlertById(final int id) {
        return weatherAlertStorage.getWeatherAlertById(id);
    }

    public List<WeatherAlert> getAllWeatherAlerts() {
        return weatherAlertStorage.getAllWeatherAlerts();
    }

    public WeatherAlert updateWeatherAlert(final int id, final WeatherAlert weatherAlert) {
        return weatherAlertStorage.updateWeatherAlert(id, weatherAlert);
    }

    public boolean deleteWeatherAlert(final int id) {
        return weatherAlertStorage.deleteWeatherAlert(id);
    }
}
