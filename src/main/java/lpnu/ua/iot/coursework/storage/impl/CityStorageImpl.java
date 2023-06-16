package lpnu.ua.iot.coursework.storage.impl;

import lpnu.ua.iot.coursework.manager.FileManager;
import lpnu.ua.iot.coursework.models.City;
import lpnu.ua.iot.coursework.models.Country;
import lpnu.ua.iot.coursework.storage.CityStorage;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class CityStorageImpl implements CityStorage {
    private final FileManager fileManager;
    private static final int CITY_ID_LINE_INDEX = 1;
    private static final int CITY_NAME_LINE_INDEX = 2;
    private static final int COUNTRY_NAME_LINE_INDEX = 3;

    public CityStorageImpl(final FileManager fileManager) {
        this.fileManager = fileManager;
    }

    @Override
    public List<City> getAllCities() {
        List<City> cities = new ArrayList<>();
        File[] files = fileManager.getFileFromCurrentMonth(new City());
        if (files != null) {
            for (File file : files) {
                try {
                    List<String> lines = fileManager.readLinesFromFile(file);
                    if (lines.size() >= COUNTRY_NAME_LINE_INDEX + 1) {
                        int id = Integer.parseInt(lines.get(CITY_ID_LINE_INDEX));
                        String name = lines.get(CITY_NAME_LINE_INDEX);
                        String countryName = lines.get(COUNTRY_NAME_LINE_INDEX);
                        Country country = new Country();
                        country.setName(countryName);
                        cities.add(new City(id, name, country, null, null));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return cities;
    }

    @Override
    public void addCity(final City city) {
        if (getCityById(city.getId()) != null) {
            System.out.println("City with ID " + city.getId() + " already exists.");
            return;
        }

        try {
            // Create the city file
            String filePath = fileManager.getFilePath(city);
            File file = new File(filePath);
            if (file.getParentFile().mkdirs()) {
                System.out.println("Directories created for file: " + file.getParentFile().getPath());
            }
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getPath());
            }

            // Write city details to the file
            List<String> lines = Arrays.asList(
                    "",
                    Integer.toString(city.getId()),
                    city.getName(),
                    city.getCountry().getName()
            );
            fileManager.writeLinesToFile(file, lines, city);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public City getCityById(final int id) {
        File[] files = fileManager.getFileFromCurrentMonth(new City());
        if (files != null) {
            for (File file : files) {
                try {
                    List<String> lines = fileManager.readLinesFromFile(file);
                    if (lines.size() >= COUNTRY_NAME_LINE_INDEX + 1 && Integer.parseInt(lines.get(CITY_ID_LINE_INDEX)) == id) {
                        String name = lines.get(CITY_NAME_LINE_INDEX);
                        String countryName = lines.get(COUNTRY_NAME_LINE_INDEX);
                        Country country = new Country();
                        country.setName(countryName);
                        return new City(id, name, country, null, null);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public City updateCity(final int id, final City city) {
        City existingCity = getCityById(id);
        if (existingCity != null) {
            try {
                File[] files = fileManager.getFileFromCurrentMonth(new City());
                if (files != null) {
                    for (File file : files) {
                        List<String> lines = fileManager.readLinesFromFile(file);
                        if (lines.size() >= COUNTRY_NAME_LINE_INDEX + 1 && Integer.parseInt(lines.get(CITY_ID_LINE_INDEX)) == id) {
                            lines.set(CITY_NAME_LINE_INDEX, city.getName());
                            lines.set(COUNTRY_NAME_LINE_INDEX, city.getCountry().getName());
                            fileManager.writeLinesToFile(file, lines, city);
                            return city;
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public boolean deleteCity(final int id) {
        File[] files = fileManager.getFileFromCurrentMonth(new City());
        if (files != null) {
            for (File file : files) {
                List<String> lines;
                try {
                    lines = fileManager.readLinesFromFile(file);
                    if (lines.size() >= COUNTRY_NAME_LINE_INDEX + 1 && Integer.parseInt(lines.get(CITY_ID_LINE_INDEX)) == id) {
                        if (file.delete()) {
                            System.out.println("File deleted: " + file.getPath());
                            return true;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
}
