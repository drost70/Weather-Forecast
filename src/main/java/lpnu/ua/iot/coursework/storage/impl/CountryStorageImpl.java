package lpnu.ua.iot.coursework.storage.impl;

import lpnu.ua.iot.coursework.manager.FileManager;
import lpnu.ua.iot.coursework.models.Country;
import lpnu.ua.iot.coursework.storage.CountryStorage;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CountryStorageImpl implements CountryStorage {
    private final FileManager fileManager;
    private static final int COUNTRY_ID_LINE_INDEX = 0;
    private static final int COUNTRY_NAME_LINE_INDEX = 1;

    public CountryStorageImpl(final FileManager fileManager) {
        this.fileManager = fileManager;
    }

    public CountryStorageImpl() {
        this(new FileManager());
    }

    @Override
    public void addCountry(final Country country) {
        if (getCountryById(country.getId()) != null) {
            System.out.println("Country with ID " + country.getId() + " already exists.");
            return;
        }

        try {
            // Create the country file
            String filePath = fileManager.getFilePath(country);
            File file = new File(filePath);
            if (file.getParentFile().mkdirs()) {
                if (file.createNewFile()) {
                    // Write country details to the file
                    List<String> lines = new ArrayList<>();
                    lines.add(Integer.toString(country.getId()));
                    lines.add(country.getName());
                    fileManager.writeLinesToFile(file, lines, country);
                } else {
                    System.out.println("Failed to create country file: " + filePath);
                }
            } else {
                System.out.println("Failed to create directories for country file: " + filePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Country getCountryById(final int id) {
        File[] files = fileManager.getFileFromCurrentMonth(new Country());
        if (files != null) {
            for (File file : files) {
                try {
                    List<String> lines = fileManager.readLinesFromFile(file);
                    if (lines.size() >= COUNTRY_NAME_LINE_INDEX + 1) {
                        String fileIdString = lines.get(COUNTRY_ID_LINE_INDEX).trim();
                        if (!fileIdString.isEmpty()) {
                            int fileId = Integer.parseInt(fileIdString);
                            if (fileId == id) {
                                String name = lines.get(COUNTRY_NAME_LINE_INDEX);
                                return new Country(id, name);
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (NumberFormatException e) {
                    // Handle invalid number format
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public List<Country> getAllCountries() {
        File[] files = fileManager.getFileFromCurrentMonth(new Country());
        if (files != null) {
            return Arrays.stream(files)
                    .flatMap(file -> {
                        try {
                            List<String> lines = fileManager.readLinesFromFile(file);
                            int id = Integer.parseInt(lines.get(COUNTRY_ID_LINE_INDEX).trim());
                            String name = lines.get(COUNTRY_NAME_LINE_INDEX);
                            return Arrays.stream(new Country[]{new Country(id, name)});
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return null;
                    })
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    public Country updateCountry(final int id, final Country country) {
        File[] files = fileManager.getFileFromCurrentMonth(new Country());
        if (files != null) {
            for (File file : files) {
                try {
                    List<String> lines = fileManager.readLinesFromFile(file);
                    if (lines.size() >= COUNTRY_NAME_LINE_INDEX + 1 && Integer.parseInt(lines.get(COUNTRY_ID_LINE_INDEX)) == id) {
                        lines.set(COUNTRY_NAME_LINE_INDEX, country.getName());
                        fileManager.writeLinesToFile(file, lines, country);
                        return new Country(id, country.getName());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public boolean deleteCountry(final int id) {
        File[] files = fileManager.getFileFromCurrentMonth(new Country());
        if (files != null) {
            for (File file : files) {
                try {
                    List<String> lines = fileManager.readLinesFromFile(file);
                    if (lines.size() >= COUNTRY_NAME_LINE_INDEX + 1 && Integer.parseInt(lines.get(COUNTRY_ID_LINE_INDEX)) == id) {
                        if (file.delete()) {
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
