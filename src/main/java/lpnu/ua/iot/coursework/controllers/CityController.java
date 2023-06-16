package lpnu.ua.iot.coursework.controllers;

import lpnu.ua.iot.coursework.models.City;
import lpnu.ua.iot.coursework.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {
    private final CityService cityService;

    @Autowired
    public CityController(final CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    public ResponseEntity<List<City>> getAllCities() {
        final List<City> cities = cityService.getAllCities();
        return ResponseEntity.ok(cities);
    }

    @GetMapping("/{id}")
    public ResponseEntity<City> getCityById(@PathVariable final int id) {
        final City city = cityService.getCityById(id);
        if (city != null) {
            return ResponseEntity.ok(city);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<City> addCity(@RequestBody final City city) {
        cityService.addCity(city);
        return ResponseEntity.status(HttpStatus.CREATED).body(city);
    }

    @PutMapping("/{id}")
    public ResponseEntity<City> updateCity(@PathVariable final int id, @RequestBody final City city) {
        final City updatedCity = cityService.updateCity(id, city);
        if (updatedCity != null) {
            return ResponseEntity.ok(updatedCity);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCity(@PathVariable final int id) {
        final boolean deleted = cityService.deleteCity(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
