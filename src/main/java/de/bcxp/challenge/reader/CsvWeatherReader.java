package de.bcxp.challenge.reader;

import de.bcxp.challenge.api.DataReader;
import de.bcxp.challenge.exception.DataReadException;
import de.bcxp.challenge.model.WeatherDay;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * CSV reader for loading daily weather records used in the
 * temperature spread analysis.
 *
 * <p>This implementation reads comma-separated values containing
 * the day number, maximum temperature, and minimum temperature.
 * Malformed rows are skipped, ensuring robust data loading.</p>
 */
public class CsvWeatherReader implements DataReader<WeatherDay> {

    /**
     * Reads weather data from a CSV file.
     *
     * <p>The method skips the header line, validates numeric values,
     * and wraps I/O errors in a {@link DataReadException} to provide
     * consistent error handling across the application.</p>
     *
     * @param filePath the path to the CSV file
     * @return a list of parsed {@link WeatherDay} objects
     */
    @Override
    public List<WeatherDay> readData(String filePath) {
        List<WeatherDay> days = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            // Read and validate header
            String line = reader.readLine();
            if (line == null) {
                throw new DataReadException("Empty file: " + filePath);
            }

            // Read each subsequent data row
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 3) continue;

                try {
                    int day = Integer.parseInt(parts[0].trim());
                    double max = Double.parseDouble(parts[1].trim());
                    double min = Double.parseDouble(parts[2].trim());

                    days.add(new WeatherDay(day, max, min));

                } catch (NumberFormatException e) {
                    // Skip invalid temperature or day values
                    System.err.println("Skipping invalid line: " + line);
                }
            }

        } catch (IOException e) {
            throw new DataReadException("Error reading file: " + filePath, e);
        }

        return days;
    }
}