package lpnu.ua.iot.coursework.manager;

import lpnu.ua.iot.coursework.dategetter.DateGetter;
import lpnu.ua.iot.coursework.models.City;
import lpnu.ua.iot.coursework.models.Country;
import lpnu.ua.iot.coursework.models.Weather;
import lpnu.ua.iot.coursework.models.WeatherAlert;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Component
public class FileManager {
    public static final String PATH_TO_CITIES = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "cities" + File.separator;
    public static final String PATH_TO_WEATHER = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "weather" + File.separator;
    public static final String PATH_TO_WEATHERALERT = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "cities" + File.separator;

    public static final String PATH_TO_COUNTRIES = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "countries" + File.separator;

    public boolean fileExists(final String fileName) {
        return Files.exists(Path.of(fileName));
    }

    public String getMonthDirectoryPath(final Object entity) {
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH) + 1;

        return getYearDirectoryPath(entity) + month;
    }

    public File[] getFileFromCurrentMonth(final Object entity) {
        File monthDirectory = new File(getMonthDirectoryPath(entity));

        if (monthDirectory.exists() && monthDirectory.isDirectory()) {
            if (monthDirectory.listFiles() != null) {
                return monthDirectory.listFiles();
            }
        }
        return null;
    }

    public void writeLinesToFile(final File file, final List<String> lines, final Object entity) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(file),
                StandardCharsets.UTF_8))) {
            writer.write(entity.getClass().getSimpleName());
            writer.newLine();
            if (entity instanceof City city) {
                writer.write(Integer.toString(city.getId()));
                writer.newLine();
                writer.write(city.getName());
                writer.newLine();
                writer.write(city.getCountry().getName());
                writer.newLine();
                // Write weatherList and weatherAlertList if needed
            }
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        }
    }

    public List<String> readLinesFromFile(final File file) throws IOException {
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

    public String getFilePath(final Object entity) {
        String monthDirectoryPath = getMonthDirectoryPath(entity) + File.separator;

        return String.format("%s%s-%s.csv", monthDirectoryPath, entity.getClass().getSimpleName(), DateGetter.getCurrentDate());
    }

    private String getYearDirectoryPath(final Object entity) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);

        if (entity instanceof City) {
            return FileManager.PATH_TO_CITIES + year + File.separator;
        } else if (entity instanceof Weather) {
            return FileManager.PATH_TO_WEATHER + year + File.separator;
        } else if (entity instanceof Country) {
            return FileManager.PATH_TO_COUNTRIES + year + File.separator;
        } else if (entity instanceof WeatherAlert) {
            return FileManager.PATH_TO_WEATHER + year + File.separator;
        } else {
            return null;
        }
    }
}
