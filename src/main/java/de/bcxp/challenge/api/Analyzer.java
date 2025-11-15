package de.bcxp.challenge.api;

import java.util.List;
import java.util.Optional;

/**
 * A generic analysis interface that defines a common contract for
 * different types of data evaluations.
 *
 * <p>Within the assignment, various datasets (e.g., weather data,
 * country statistics) must be analyzed according to a specific rule.
 * This interface provides a clear and extensible architecture by
 * separating the analysis logic from the data model.</p>
 *
 * @param <T> The type of objects to analyze.
 */
public interface Analyzer<T> {

    /**
     * Performs an analysis on the given list of data items.
     *
     * <p>The method returns an {@link Optional} to ensure robustness.
     * If the input is {@code null}, empty, or cannot be evaluated,
     * the method returns {@code Optional.empty()} instead of throwing
     * errors.</p>
     *
     * @param data the data to analyze, may be {@code null}.
     * @return an {@link Optional} containing the analysis result, or
     *         {@code Optional.empty()} if no result can be determined.
     */
    Optional<T> analyze(List<T> data);
}
