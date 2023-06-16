package lpnu.ua.iot.coursework.storage.impl;

import lpnu.ua.iot.coursework.manager.FileManager;
import lpnu.ua.iot.coursework.models.City;
import lpnu.ua.iot.coursework.storage.CityStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;

public class CityStorageImplTest {
    private CityStorage cityStorage;
    private FileManager fileManagerMock;

    @BeforeEach
    public void setUp() {
        fileManagerMock = mock(FileManager.class);
        cityStorage = new CityStorageImpl(fileManagerMock);
    }

    @Test
    public void testAddCity() {
        City city = new City(1, "City 1", null, null, null);
        cityStorage.addCity(city);
        assertEquals(city, cityStorage.getCityById(1));
    }

    @Test
    public void testGetCityById() {
        City city = new City(1, "City 1", null, null, null);
        cityStorage.addCity(city);
        assertEquals(city, cityStorage.getCityById(1));
    }

    @Test
    public void testGetCityById_NonExistentId() {
        assertNull(cityStorage.getCityById(1));
    }
}
