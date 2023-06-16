package lpnu.ua.iot.coursework.controllers;

import lpnu.ua.iot.coursework.models.WeatherAlert;
import lpnu.ua.iot.coursework.services.WeatherAlertService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/weather-alerts")
public class WeatherAlertController {
    private final WeatherAlertService weatherAlertService;

    public WeatherAlertController(final WeatherAlertService weatherAlertService) {
        this.weatherAlertService = weatherAlertService;
    }

    @PostMapping
    public ResponseEntity<Void> addWeatherAlert(@RequestBody final WeatherAlert weatherAlert) {
        weatherAlertService.addWeatherAlert(weatherAlert);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<WeatherAlert> getWeatherAlertById(@PathVariable final int id) {
        final WeatherAlert weatherAlert = weatherAlertService.getWeatherAlertById(id);
        if (weatherAlert != null) {
            return ResponseEntity.ok(weatherAlert);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<WeatherAlert>> getAllWeatherAlerts() {
        final List<WeatherAlert> weatherAlerts = weatherAlertService.getAllWeatherAlerts();
        return ResponseEntity.ok(weatherAlerts);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WeatherAlert> updateWeatherAlert(@PathVariable final int id, @RequestBody final WeatherAlert weatherAlert) {
        final WeatherAlert updatedWeatherAlert = weatherAlertService.updateWeatherAlert(id, weatherAlert);
        if (updatedWeatherAlert != null) {
            return ResponseEntity.ok(updatedWeatherAlert);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWeatherAlert(@PathVariable final int id) {
        final boolean deleted = weatherAlertService.deleteWeatherAlert(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
