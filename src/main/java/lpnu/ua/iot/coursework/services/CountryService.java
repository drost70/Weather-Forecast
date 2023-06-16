package lpnu.ua.iot.coursework.services;

import lpnu.ua.iot.coursework.models.Country;
import lpnu.ua.iot.coursework.storage.CountryStorage;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {
    private final CountryStorage countryStorage;

    public CountryService(final CountryStorage countryStorage) {
        this.countryStorage = countryStorage;
    }

    public List<Country> getAllCountries() {
        return countryStorage.getAllCountries();
    }

    public Country getCountryById(final int id) {
        return countryStorage.getCountryById(id);
    }

    public void addCountry(final Country country) {
        countryStorage.addCountry(country);
    }

    public Country updateCountry(final int id, final Country country) {
        return countryStorage.updateCountry(id, country);
    }

    public boolean deleteCountry(final int id) {
        return countryStorage.deleteCountry(id);
    }
}
