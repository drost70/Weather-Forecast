package lpnu.ua.iot.coursework.services;

import lpnu.ua.iot.coursework.models.City;
import lpnu.ua.iot.coursework.storage.CityStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {
    private final CityStorage cityStorage;

    @Autowired
    public CityService(final CityStorage cityStorage) {
        this.cityStorage = cityStorage;
    }

    public List<City> getAllCities() {
        return cityStorage.getAllCities();
    }

    public City getCityById(final int id) {
        return cityStorage.getCityById(id);
    }

    public void addCity(final City city) {
        cityStorage.addCity(city);
    }

    public City updateCity(final int id, final City city) {
        return cityStorage.updateCity(id, city);
    }

    public boolean deleteCity(final int id) {
        return cityStorage.deleteCity(id);
    }
}
