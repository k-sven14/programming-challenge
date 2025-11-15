package de.bcxp.challenge.api;

import java.util.List;

/**
 * A generic interface for reading structured data from a file.
 *
 * <p>In the context of the assignment, different datasets (e.g.,
 * weather records, country statistics) must be loaded from external
 * sources before they can be analyzed. This interface defines a clear
 * and reusable contract for all data-loading implementations.</p>
 *
 * @param <T> the type of objects produced from the file contents
 */
public interface DataReader<T> {

    /**
     * Reads and parses data from the given file path.
     *
     * <p>Implementations should ensure robustness by validating input,
     * handling malformed lines, and reporting errors appropriately.</p>
     *
     * @param filePath the path to the file to read
     * @return a list of parsed objects; never {@code null}
     */
    List<T> readData(String filePath);
}

