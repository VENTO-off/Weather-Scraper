package vento.weather_scraper.handler;

/**
 * Weather API interface.
 */
public interface WeatherApi {
    void scrapWeather() throws Exception;

    String getApiName();
}
