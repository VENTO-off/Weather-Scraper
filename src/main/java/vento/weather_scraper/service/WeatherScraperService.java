package vento.weather_scraper.service;

/**
 * Interface for services that handle fetching weather data from different APIs.
 */
public interface WeatherScraperService {
    /**
     * Fetches weather data from all configured APIs.
     */
    void fetchWeatherAPIs();
}
