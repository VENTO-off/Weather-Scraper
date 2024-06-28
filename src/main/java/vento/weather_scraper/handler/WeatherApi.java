package vento.weather_scraper.handler;

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
        final String apiName = getClass().getSimpleName().replaceAll("([A-Z])", "_$1").toLowerCase();
        if (apiName.startsWith("_")) {
            return apiName.substring(1);
        }

        return apiName;
    }
}
