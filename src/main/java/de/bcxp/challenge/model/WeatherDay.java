package de.bcxp.challenge.model;

/**
 * Represents the weather data of a single day, containing only the
 * information required for temperature spread analysis.
 *
 * <p>This lightweight model holds the day index and the corresponding
 * maximum and minimum temperatures.</p>
 */
public class WeatherDay {
    private final int day;
    private final double maxTemp;
    private final double minTemp;

    /**
     * Creates a new weather record for a specific day.
     *
     * @param day     the day number
     * @param maxTemp the maximum temperature of the day
     * @param minTemp the minimum temperature of the day
     */
    public WeatherDay(int day, double maxTemp, double minTemp) {
        this.day = day;
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
    }

    public int getDay() {
        return day;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    public double getMinTemp() {
        return minTemp;
    }

    /**
     * Computes the absolute temperature difference (spread) between
     * the maximum and minimum temperature of the day.
     *
     * @return the temperature spread
     */
    public double getTemperatureDifference() {
        return Math.abs(maxTemp - minTemp);
    }

    @Override
    public String toString() {
        return "Day " + day + " [MxT=" + maxTemp + ", MnT=" + minTemp + "]";
    }
}
