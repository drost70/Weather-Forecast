package lpnu.ua.iot.coursework.services;

import lpnu.ua.iot.coursework.manager.FileManager;
import lpnu.ua.iot.coursework.models.Weather;
import lpnu.ua.iot.coursework.storage.WeatherStorage;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class WeatherService {
    private final WeatherStorage weatherStorage;
    private final FileManager fileManager;

    public WeatherService(final WeatherStorage weatherStorage, final FileManager fileManager) {
        this.weatherStorage = weatherStorage;
        this.fileManager = fileManager;
    }

    public void addWeather(final Weather weather) {
        weatherStorage.addWeather(weather);
    }

    public Weather getWeatherById(final int id) {
        return weatherStorage.getWeatherById(id);
    }

    public List<Weather> getAllWeathers() {
        return weatherStorage.getAllWeathers();
    }

    public Weather updateWeather(final int id, final Weather weather) {
        return weatherStorage.updateWeather(id, weather);
    }

    public boolean deleteWeather(final int id) {
        return weatherStorage.deleteWeather(id);
    }

    public boolean fileExists(final String fileName) {
        return fileManager.fileExists(fileName);
    }

    public List<String> readLinesFromFile(final File file) throws IOException {
        return fileManager.readLinesFromFile(file);
    }

    public void writeLinesToFile(final File file, final List<String> lines, final Object entity) throws IOException {
        fileManager.writeLinesToFile(file, lines, entity);
    }

    public String getFilePath(final Object entity) {
        return fileManager.getFilePath(entity);
    }
}
