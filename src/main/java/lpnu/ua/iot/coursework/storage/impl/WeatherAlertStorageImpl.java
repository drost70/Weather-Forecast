package lpnu.ua.iot.coursework.storage.impl;

import lpnu.ua.iot.coursework.models.WeatherAlert;
import lpnu.ua.iot.coursework.storage.WeatherAlertStorage;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Component
public class WeatherAlertStorageImpl implements WeatherAlertStorage {
    private static final String FILE_PATH = "src/main/resources/weather_alerts.csv";
    private static final int FILE_LINES_COUNT = 5;

    @Override
    public void addWeatherAlert(final WeatherAlert weatherAlert) {
        List<WeatherAlert> weatherAlerts = getAllWeatherAlerts();
        weatherAlerts.add(weatherAlert);
        saveWeatherAlerts(weatherAlerts);
    }

    @Override
    public WeatherAlert getWeatherAlertById(final int id) {
        List<WeatherAlert> weatherAlerts = getAllWeatherAlerts();
        for (WeatherAlert weatherAlert : weatherAlerts) {
            if (weatherAlert.getId() == id) {
                return weatherAlert;
            }
        }
        return null;
    }

    @Override
    public List<WeatherAlert> getAllWeatherAlerts() {
        List<WeatherAlert> weatherAlerts = new ArrayList<>();
        try {
            File file = new File(FILE_PATH);
            if (file.exists()) {
                List<String> lines = readLinesFromFile(file);
                for (String line : lines) {
                    String[] parts = line.split(",");
                    if (parts.length >= FILE_LINES_COUNT) {
                        int id;
                        try {
                            id = Integer.parseInt(parts[0]);
                        } catch (NumberFormatException e) {
                            // Invalid id value, skip this line
                            continue;
                        }
                        String type = parts[1];
                        String severityLevel = parts[2];
                        String date = parts[3];
                        String description = parts[4];
                        WeatherAlert weatherAlert = new WeatherAlert(id, type, severityLevel, date, description);
                        weatherAlerts.add(weatherAlert);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return weatherAlerts;
    }

    @Override
    public WeatherAlert updateWeatherAlert(final int id, final WeatherAlert weatherAlert) {
        List<WeatherAlert> weatherAlerts = getAllWeatherAlerts();
        for (int i = 0; i < weatherAlerts.size(); i++) {
            if (weatherAlerts.get(i).getId() == id) {
                weatherAlerts.set(i, weatherAlert);
                saveWeatherAlerts(weatherAlerts);
                return weatherAlert;
            }
        }
        return null;
    }

    @Override
    public boolean deleteWeatherAlert(final int id) {
        List<WeatherAlert> weatherAlerts = getAllWeatherAlerts();
        for (int i = 0; i < weatherAlerts.size(); i++) {
            if (weatherAlerts.get(i).getId() == id) {
                weatherAlerts.remove(i);
                saveWeatherAlerts(weatherAlerts);
                return true;
            }
        }
        return false;
    }

    private void saveWeatherAlerts(final List<WeatherAlert> weatherAlerts) {
        try {
            File file = new File(FILE_PATH);
            List<String> lines = new ArrayList<>();
            for (WeatherAlert weatherAlert : weatherAlerts) {
                String line = String.format("%d,%s,%s,%s,%s", weatherAlert.getId(), weatherAlert.getType(),
                        weatherAlert.getSeverityLevel(), weatherAlert.getDate(), weatherAlert.getDescription());
                lines.add(line);
            }
            writeLinesToFile(file, lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String> readLinesFromFile(final File file) throws IOException {
        List<String> readLines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(file),
                StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                readLines.add(line);
            }
        }
        return readLines;
    }

    private void writeLinesToFile(final File file, final List<String> lines) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(file),
                StandardCharsets.UTF_8))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        }
    }
}
