
package lpnu.ua.iot.coursework.storage.impl;

import lpnu.ua.iot.coursework.models.Country;
import lpnu.ua.iot.coursework.storage.CountryStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CountryStorageImplTest {
    private CountryStorage countryStorage;

    @BeforeEach
    void setUp() {
        countryStorage = new CountryStorageImpl();
    }

    @Test
    void testAddCountry() {
        Country country = new Country(1, "Ukraine");  // Задайте ідентифікатор
        countryStorage.addCountry(country);

        Country retrievedCountry = countryStorage.getCountryById(1);
        assertNotNull(retrievedCountry);
        assertEquals(country.getId(), retrievedCountry.getId());
        assertEquals(country.getName(), retrievedCountry.getName());
    }


    @Test
    void testGetCountryById() {
        Country country = new Country(1, "Ukraine");
        countryStorage.addCountry(country);

        Country retrievedCountry = countryStorage.getCountryById(1);
        assertNotNull(retrievedCountry);
        assertEquals(country.getId(), retrievedCountry.getId());
        assertEquals(country.getName(), retrievedCountry.getName());
    }

    @Test
    void testGetAllCountries() {
        Country country1 = new Country(1, "Ukraine");
        Country country2 = new Country(2, "USA");

        countryStorage.addCountry(country1);
        countryStorage.addCountry(country2);

        List<Country> countries = countryStorage.getAllCountries();
        assertNotNull(countries);
        assertEquals(2, countries.size());
    }

    @Test
    void testUpdateCountry() {
        Country country = new Country(1, "Ukraine");
        countryStorage.addCountry(country);

        Country updatedCountry = new Country(1, "United States");
        Country updated = countryStorage.updateCountry(1, updatedCountry);
        assertNotNull(updated);
        assertEquals(updatedCountry.getId(), updated.getId());
        assertEquals(updatedCountry.getName(), updated.getName());
    }

    @Test
    void testDeleteCountry() {
        Country country = new Country(1, "Ukraine");
        countryStorage.addCountry(country);

        boolean deleted = countryStorage.deleteCountry(1);
        assertTrue(deleted);
    }
}
