package lpnu.ua.iot.coursework.controllers;

import lpnu.ua.iot.coursework.models.Weather;
import lpnu.ua.iot.coursework.services.WeatherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/weathers")
public class WeatherController {
    private final WeatherService weatherService;

    public WeatherController(final WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @PostMapping
    public ResponseEntity<Void> addWeather(@RequestBody final Weather weather) {
        weatherService.addWeather(weather);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Weather> getWeatherById(@PathVariable final int id) {
        final Weather weather = weatherService.getWeatherById(id);
        if (weather != null) {
            return ResponseEntity.ok(weather);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Weather>> getAllWeathers() {
        final List<Weather> weathers = weatherService.getAllWeathers();
        return ResponseEntity.ok(weathers);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Weather> updateWeather(@PathVariable final int id, @RequestBody final Weather weather) {
        final Weather updatedWeather = weatherService.updateWeather(id, weather);
        if (updatedWeather != null) {
            return ResponseEntity.ok(updatedWeather);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWeather(@PathVariable final int id) {
        final boolean deleted = weatherService.deleteWeather(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
