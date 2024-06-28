package vento.weather_scraper.handler;

import org.springframework.util.StringUtils;

/**
 * Interface for the operational control of a weather data scraping process.
 */
public interface WeatherApi {
    /**
     * Executes the weather data scraping process.
     * This method handles the entire process of fetching, decoding, and saving weather data.
     *
     * @throws Exception to cover all exceptions that might occur during the scraping process, from network to storage.
     */
    void scrapWeather() throws Exception;

    /**
     * Retrieves the name of the API service.
     *
     * @return The name of the API service.
     */
    default String getApiName() {
        return StringUtils.capitalize(getClass().getSimpleName()
                .replaceAll("([A-Z])", " $1")
                .replaceAll("\\s+\\S+$", "")
                .toLowerCase().trim()
        );
    }
}
