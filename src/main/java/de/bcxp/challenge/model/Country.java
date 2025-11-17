package de.bcxp.challenge.model;

/**
 * Represents a country with the minimum attributes required for
 * population density analysis.
 *
 * <p>This model is intentionally lightweight and focused on the data
 * relevant for the assignment: name, population, and area.</p>
 */
public class Country {
    private final String name;
    private final double population;
    private final double area;

    /**
     * Creates a new country instance.
     *
     * @param name        the country's name
     * @param population  total population of the country (must be >= 0)
     * @param area        land area in square kilometers (must be >= 0)
     */
    public Country(String name, double population, double area) {
        if (population < 0 || area < 0) {
            throw new IllegalArgumentException("Population and area must be non-negative.");
        }
        this.name = name;
        this.population = population;
        this.area = area;
    }

    public double getPopulation() {
        return population;
    }

    public String getName() {
        return name;
    }

    public double getArea() {
        return area;
    }

    /**
     * Computes the population density (population per km²).
     *
     * <p>Returns 0 for invalid or zero-area values to ensure robustness
     * and prevent division-by-zero errors.</p>
     *
     * @return population density, or 0 if area is invalid
     */
    public double getPopulationDensity() {
        if (area <= 0) return 0;
        return population / area;
    }

    @Override
    public String toString() {
        return name + " (Population: " + population + ", Area: " + area + ")";
    }
}
