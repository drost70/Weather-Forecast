package lpnu.ua.iot.coursework.storage;

import lpnu.ua.iot.coursework.models.Country;

import java.util.List;

public interface CountryStorage {
    void addCountry(Country country);

    Country getCountryById(int id);

    List<Country> getAllCountries();

    Country updateCountry(int id, Country country);

    boolean deleteCountry(int id);
}
