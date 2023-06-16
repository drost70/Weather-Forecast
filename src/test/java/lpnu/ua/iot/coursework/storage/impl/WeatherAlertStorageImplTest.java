package lpnu.ua.iot.coursework.storage.impl;

import lpnu.ua.iot.coursework.models.WeatherAlert;
import lpnu.ua.iot.coursework.storage.WeatherAlertStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WeatherAlertStorageImplTest {
    private WeatherAlertStorage weatherAlertStorage;

    @BeforeEach
    void setUp() {
        weatherAlertStorage = new WeatherAlertStorageImpl();
    }

    @Test
    void testAddWeatherAlert() {
        WeatherAlert weatherAlert = new WeatherAlert(1, "Storm", "High", "2023-06-15", "Rainfall expected");
        weatherAlertStorage.addWeatherAlert(weatherAlert);

        WeatherAlert retrievedAlert = weatherAlertStorage.getWeatherAlertById(1);
        assertNotNull(retrievedAlert);
        assertEquals(weatherAlert.getId(), retrievedAlert.getId());
        assertEquals(weatherAlert.getType(), retrievedAlert.getType());
        assertEquals(weatherAlert.getSeverityLevel(), retrievedAlert.getSeverityLevel());
        assertEquals(weatherAlert.getDate(), retrievedAlert.getDate());
        assertEquals(weatherAlert.getDescription(), retrievedAlert.getDescription());
    }

    @Test
    void testGetWeatherAlertById() {
        WeatherAlert weatherAlert = new WeatherAlert(1, "Storm", "Moderate", "2023-06-15", "Rainfall expected");
        weatherAlertStorage.addWeatherAlert(weatherAlert);

        WeatherAlert retrievedAlert = weatherAlertStorage.getWeatherAlertById(1);
        assertNotNull(retrievedAlert);
        assertEquals(weatherAlert.getId(), retrievedAlert.getId());
        assertEquals(weatherAlert.getType(), retrievedAlert.getType());
        assertEquals(weatherAlert.getSeverityLevel(), retrievedAlert.getSeverityLevel());
        assertEquals(weatherAlert.getDate(), retrievedAlert.getDate());
        assertEquals(weatherAlert.getDescription(), retrievedAlert.getDescription());
    }

    @Test
    void testGetAllWeatherAlerts() {
        WeatherAlert weatherAlert1 = new WeatherAlert(1, "Rain", "Moderate", "2023-06-15", "Rainfall expected");
        WeatherAlert weatherAlert2 = new WeatherAlert(2, "Snow", "High", "2023-06-16", "Heavy snowfall expected");

        weatherAlertStorage.addWeatherAlert(weatherAlert1);
        weatherAlertStorage.addWeatherAlert(weatherAlert2);

        List<WeatherAlert> weatherAlerts = weatherAlertStorage.getAllWeatherAlerts();
        assertNotNull(weatherAlerts);
        assertEquals(2, weatherAlerts.size());
    }

    @Test
    void testUpdateWeatherAlert() {
        WeatherAlert weatherAlert = new WeatherAlert(1, "Rain", "Moderate", "2023-06-15", "Rainfall expected");
        weatherAlertStorage.addWeatherAlert(weatherAlert);

        WeatherAlert updatedAlert = new WeatherAlert(1, "Storm", "High", "2023-06-15", "Severe storm expected");
        WeatherAlert updated = weatherAlertStorage.updateWeatherAlert(1, updatedAlert);
        assertNotNull(updated);
        assertEquals(updatedAlert.getId(), updated.getId());
        assertEquals(updatedAlert.getType(), updated.getType());
        assertEquals(updatedAlert.getSeverityLevel(), updated.getSeverityLevel());
        assertEquals(updatedAlert.getDate(), updated.getDate());
        assertEquals(updatedAlert.getDescription(), updated.getDescription());
    }

    @Test
    void testDeleteWeatherAlert() {
        WeatherAlert weatherAlert = new WeatherAlert(1, "Rain", "Moderate", "2023-06-15", "Rainfall expected");
        weatherAlertStorage.addWeatherAlert(weatherAlert);

        boolean deleted = weatherAlertStorage.deleteWeatherAlert(1);
        assertTrue(deleted);
    }
}
