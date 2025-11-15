package de.bcxp.challenge.reader;

import de.bcxp.challenge.api.DataReader;
import de.bcxp.challenge.exception.DataReadException;
import de.bcxp.challenge.model.Country;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * CSV reader for loading country data from the provided dataset.
 *
 * <p>This implementation focuses on the fields required for the population
 * density analysis (name, population, area). It is resilient against malformed
 * lines and performs locale adjustments on numeric values.</p>
 */
public class CsvCountryReader implements DataReader<Country> {

    /**
     * Reads country data from a semicolon-separated CSV file.
     *
     * <p>Invalid or non-numeric lines are skipped, while I/O failures are
     * wrapped in a {@link DataReadException} to provide a unified error
     * handling strategy.</p>
     *
     * @param filePath the path to the CSV file
     * @return a list of parsed {@link Country} objects
     */
    @Override
    public List<Country> readData(String filePath) {
        List<Country> countries = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            // Skip header line and validate non-empty file
            String line = reader.readLine();
            if (line == null) {
                throw new DataReadException("Empty file: " + filePath);
            }

            // Process each data row
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length < 5) continue;

                try {
                    String name = parts[0].trim();
                    String populationStr = parts[3].trim();
                    String areaStr = parts[4].trim();

                    // Normalize locale-specific numbers ("4.036.355,00" → "4036355.00")
                    populationStr = populationStr.replace(".", "").replace(",", ".");
                    areaStr = areaStr.replace(".", "").replace(",", ".");

                    double population = Double.parseDouble(populationStr);
                    double area = Double.parseDouble(areaStr);

                    countries.add(new Country(name, population, area));

                } catch (NumberFormatException e) {
                    // Skip malformed numerical entries
                    System.err.println("Skipping invalid line: " + line);
                }
            }

        } catch (IOException e) {
            throw new DataReadException("Error reading file: " + filePath, e);
        }

        return countries;
    }
}