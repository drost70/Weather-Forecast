package lpnu.ua.iot.coursework.storage;

import lpnu.ua.iot.coursework.models.City;

import java.util.List;

public interface CityStorage {
    List<City> getAllCities();

    void addCity(City city);

    City getCityById(int id);

    City updateCity(int id, City city);

    boolean deleteCity(int id);
}
