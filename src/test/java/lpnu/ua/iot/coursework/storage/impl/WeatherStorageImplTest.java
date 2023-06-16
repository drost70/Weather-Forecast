package lpnu.ua.iot.coursework.storage.impl;

import lpnu.ua.iot.coursework.manager.FileManager;
import lpnu.ua.iot.coursework.models.Weather;
import lpnu.ua.iot.coursework.storage.WeatherStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WeatherStorageImplTest {
    private WeatherStorage weatherStorage;

    @BeforeEach
    void setUp() {
        FileManager fileManager = new FileManager(); // Замініть на вашу реалізацію FileManager, якщо це потрібно
        weatherStorage = new WeatherStorageImpl(fileManager);
    }

    @Test
    void testAddWeather() {
        Weather weather = new Weather(1, "Sunny", 25.0, "2023-06-15", "Clear sky");
        weatherStorage.addWeather(weather);

        Weather retrievedWeather = weatherStorage.getWeatherById(1);
        assertNotNull(retrievedWeather);
        assertEquals(weather.getId(), retrievedWeather.getId());
        assertEquals(weather.getCondition(), retrievedWeather.getCondition());
        assertEquals(weather.getTemperature(), retrievedWeather.getTemperature());
        assertEquals(weather.getDate(), retrievedWeather.getDate());
        assertEquals(weather.getWeatherReport(), retrievedWeather.getWeatherReport());
    }

    @Test
    void testGetWeatherById() {
        Weather weather = new Weather(1, "Cloudy", 20.0, "2023-06-15", "Partly cloudy");
        weatherStorage.addWeather(weather);

        Weather retrievedWeather = weatherStorage.getWeatherById(1);
        assertNotNull(retrievedWeather);
        assertEquals(weather.getId(), retrievedWeather.getId());
        assertEquals(weather.getCondition(), retrievedWeather.getCondition());
        assertEquals(weather.getTemperature(), retrievedWeather.getTemperature());
        assertEquals(weather.getDate(), retrievedWeather.getDate());
        assertEquals(weather.getWeatherReport(), retrievedWeather.getWeatherReport());
    }

    @Test
    void testGetAllWeathers() {
        Weather weather1 = new Weather(1, "Sunny", 25.0, "2023-06-15", "Clear sky");
        Weather weather2 = new Weather(2, "Cloudy", 20.0, "2023-06-16", "Partly cloudy");

        weatherStorage.addWeather(weather1);
        weatherStorage.addWeather(weather2);

        List<Weather> allWeathers = weatherStorage.getAllWeathers();
        assertNotNull(allWeathers);
        assertEquals(2, allWeathers.size());
    }

    @Test
    void testUpdateWeather() {
        Weather weather = new Weather(1, "Sunny", 25.0, "2023-06-15", "Clear sky");
        weatherStorage.addWeather(weather);

        Weather updatedWeather = new Weather(1, "Cloudy", 20.0, "2023-06-15", "Partly cloudy");
        Weather updated = weatherStorage.updateWeather(1, updatedWeather);
        assertNotNull(updated);
        assertEquals(updatedWeather.getId(), updated.getId());
        assertEquals(updatedWeather.getCondition(), updated.getCondition());
        assertEquals(updatedWeather.getTemperature(), updated.getTemperature());
        assertEquals(updatedWeather.getDate(), updated.getDate());
        assertEquals(updatedWeather.getWeatherReport(), updated.getWeatherReport());
    }

    @Test
    void testDeleteWeather() {
        Weather weather = new Weather(1, "Sunny", 25.0, "2023-06-15", "Clear sky");
        weatherStorage.addWeather(weather);

        boolean deleted = weatherStorage.deleteWeather(1);
        assertTrue(deleted);
    }
}
