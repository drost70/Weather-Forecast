package lpnu.ua.iot.coursework.manager;

import lpnu.ua.iot.coursework.models.City;
import lpnu.ua.iot.coursework.models.Country;
import lpnu.ua.iot.coursework.models.Weather;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileManagerTest {
    private final FileManager fileManager = new FileManager();

    @Test
    public void testWriteAndReadLinesToFile() {
        String fileName = "test.csv";
        List<String> linesToWrite = new ArrayList<>();
        linesToWrite.add("Line 1");
        linesToWrite.add("Line 2");
        linesToWrite.add("Line 3");

        File file = new File(fileName);
        try {
            Weather weather = new Weather();
            fileManager.writeLinesToFile(file, linesToWrite, weather);

            List<String> readLines = fileManager.readLinesFromFile(file);
            List<String> expectedLines = new ArrayList<>(linesToWrite);
            expectedLines.add(0, Weather.class.getSimpleName());
            Assertions.assertEquals(expectedLines, readLines, "Read lines should match the written lines");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (file.exists()) {
                file.delete();
            }
        }
    }



    @Test
    public void testGetFilePath() {
        City city = new City();
        city.setId(1);
        city.setName("City 1");
        Country country = new Country();
        country.setId(1);
        country.setName("Country 1");
        city.setCountry(country);

        String expectedFilePath = "src\\main\\resources\\cities\\2023\\6\\City-2023-06-16.csv";
        String filePath = fileManager.getFilePath(city);
        Assertions.assertEquals(expectedFilePath, filePath, "File path should be constructed correctly");
    }

    @Test
    public void testWriteAndReadCityToFile() {
        City city = new City();
        city.setId(1);
        city.setName("City 1");
        Country country = new Country();
        country.setId(1);
        country.setName("Country 1");
        city.setCountry(country);

        String filePath = fileManager.getFilePath(city);

        File file = new File(filePath);
        try {
            List<String> linesToWrite = new ArrayList<>();
            linesToWrite.add(city.getId() + "," + city.getName() + "," + city.getCountry().getName());

            fileManager.writeLinesToFile(file, linesToWrite, city);

            List<String> readLines = fileManager.readLinesFromFile(file);

            String[] lineParts = readLines.get(0).split(",");
            int readCityId = Integer.parseInt(lineParts[0]);
            String readCityName = lineParts[1];
            String readCountryName = lineParts[2];

            City readCity = new City();
            readCity.setId(readCityId);
            readCity.setName(readCityName);
            Country readCountry = new Country();
            readCountry.setName(readCountryName);
            readCity.setCountry(readCountry);

            Assertions.assertEquals(city, readCity, "Read city should match the written city");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (file.exists()) {
                file.delete();
            }
        }
    }

}
