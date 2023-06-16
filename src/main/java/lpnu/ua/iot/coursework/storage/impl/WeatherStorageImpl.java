package lpnu.ua.iot.coursework.storage.impl;

import lpnu.ua.iot.coursework.manager.FileManager;
import lpnu.ua.iot.coursework.models.Weather;
import lpnu.ua.iot.coursework.storage.WeatherStorage;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class WeatherStorageImpl implements WeatherStorage {
    private static final int FILE_LINES_COUNT = 5;

    private final FileManager fileManager;

    public WeatherStorageImpl(final FileManager fileManager) {
        this.fileManager = fileManager;
    }

    @Override
    public void addWeather(final Weather weather) {
        if (getWeatherById(weather.getId()) != null) {
            System.out.println("Weather with ID " + weather.getId() + " already exists.");
            return;
        }

        try {
            // Create the weather file
            String filePath = fileManager.getFilePath(weather);
            File file = new File(filePath);
            if (file.getParentFile().mkdirs()) {
                System.out.println("Directories created for file: " + file.getParentFile().getPath());
            }
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getPath());
            }

            // Write weather details to the file
            List<String> lines = new ArrayList<>();
            lines.add(Integer.toString(weather.getId()));
            lines.add(weather.getCondition());
            lines.add(Double.toString(weather.getTemperature()));
            lines.add(weather.getDate());
            lines.add(weather.getWeatherReport());
            fileManager.writeLinesToFile(file, lines, weather);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Weather getWeatherById(final int id) {
        File[] files = fileManager.getFileFromCurrentMonth(new Weather());
        if (files != null) {
            for (File file : files) {
                try {
                    List<String> lines = fileManager.readLinesFromFile(file);
                    if (lines.size() >= FILE_LINES_COUNT) {
                        int fileId;
                        try {
                            fileId = Integer.parseInt(lines.get(0).trim());
                        } catch (NumberFormatException e) {
                            // Invalid id value, skip this file
                            continue;
                        }
                        if (fileId == id) {
                            String condition = lines.get(1);
                            double temperature;
                            try {
                                temperature = Double.parseDouble(lines.get(2));
                            } catch (NumberFormatException e) {
                                // Invalid temperature value, skip this file
                                continue;
                            }
                            String date = lines.get(3);
                            String weatherReport = lines.get(4);
                            return new Weather(id, condition, temperature, date, weatherReport);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public List<Weather> getAllWeathers() {
        List<Weather> allWeathers = new ArrayList<>();
        File[] files = fileManager.getFileFromCurrentMonth(new Weather());
        if (files != null) {
            for (File file : files) {
                try {
                    List<String> lines = fileManager.readLinesFromFile(file);
                    if (lines.size() >= FILE_LINES_COUNT) {
                        int id;
                        try {
                            id = Integer.parseInt(lines.get(0).trim());
                        } catch (NumberFormatException e) {
                            // Invalid id value, skip this file
                            continue;
                        }
                        String condition = lines.get(1);
                        double temperature;
                        try {
                            temperature = Double.parseDouble(lines.get(2).trim());
                        } catch (NumberFormatException e) {
                            // Invalid temperature value, skip this file
                            continue;
                        }
                        String date = lines.get(3);
                        String weatherReport = lines.get(4);
                        Weather weather = new Weather(id, condition, temperature, date, weatherReport);
                        allWeathers.add(weather);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return allWeathers;
    }

    @Override
    public Weather updateWeather(final int id, final Weather weather) {
        // Find the weather file by ID
        File[] files = fileManager.getFileFromCurrentMonth(new Weather());
        if (files != null) {
            for (File file : files) {
                try {
                    List<String> lines = fileManager.readLinesFromFile(file);
                    if (lines.size() >= FILE_LINES_COUNT) {
                        int fileId;
                        try {
                            fileId = Integer.parseInt(lines.get(0).trim());
                        } catch (NumberFormatException e) {
                            // Invalid id value, skip this file
                            continue;
                        }
                        if (fileId == id) {
                            // Update the weather details in the file
                            lines.set(1, weather.getCondition());
                            lines.set(2, Double.toString(weather.getTemperature()));
                            lines.set(3, weather.getDate());
                            lines.set(4, weather.getWeatherReport());
                            fileManager.writeLinesToFile(file, lines, weather);
                            return weather;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null; // Weather with the given ID not found
    }

    @Override
    public boolean deleteWeather(final int id) {
        // Find the weather file by ID
        File[] files = fileManager.getFileFromCurrentMonth(new Weather());
        if (files != null) {
            for (File file : files) {
                try {
                    List<String> lines = fileManager.readLinesFromFile(file);
                    if (lines.size() >= FILE_LINES_COUNT) {
                        int fileId;
                        try {
                            fileId = Integer.parseInt(lines.get(0).trim());
                        } catch (NumberFormatException e) {
                            // Invalid id value, skip this file
                            continue;
                        }
                        if (fileId == id) {
                            // Delete the weather file
                            if (file.delete()) {
                                System.out.println("File deleted: " + file.getPath());
                                return true;
                            } else {
                                System.out.println("Failed to delete file: " + file.getPath());
                                return false;
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false; // Weather with the given ID not found
    }
}
