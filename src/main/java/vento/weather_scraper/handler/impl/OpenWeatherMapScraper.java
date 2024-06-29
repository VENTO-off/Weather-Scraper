package vento.weather_scraper.handler.impl;

import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vento.weather_scraper.config.OpenWeatherMapConfig;
import vento.weather_scraper.model.CsvConvertible;
import vento.weather_scraper.model.OpenWeatherMapRecord;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;

/**
 * Implements data scraping specifically for the OpenWeatherMap API.
 */
@Component
public class OpenWeatherMapScraper extends WeatherScraperImpl {
    @Autowired
    private OpenWeatherMapConfig config;

    /**
     * Initializes the OpenWeatherMapScraper by starting the scheduler with the configured delay.
     */
    @PostConstruct
    public void init() {
        startScheduler(config.getDelay());
    }

    /**
     * Builds the URL used for fetching weather data from the OpenWeatherMap API.
     *
     * @return A string representing the complete URL for the API request.
     */
    @Override
    public String buildQueryURL() {
        return String.format(
                "https://api.openweathermap.org/data/3.0/onecall?lat=%s&lon=%s&exclude=minutely,hourly,daily,alerts&units=metric&appid=%s",
                config.getLatitude(),
                config.getLongitude(),
                config.getToken()
        );
    }

    /**
     * Decodes the raw JSON data fetched from the OpenWeatherMap API into a structured CsvConvertible format.
     *
     * @param rawData The raw JSON data fetched from the API.
     * @return A list of CsvConvertible objects containing the structured weather data.
     */
    @Override
    public List<CsvConvertible> decodeData(String rawData) {
        final JsonObject jsonObject = getGson().fromJson(rawData, JsonObject.class);
        final JsonObject current = jsonObject.get("current").getAsJsonObject();
        final OpenWeatherMapRecord record = getGson().fromJson(current, OpenWeatherMapRecord.class);

        return Collections.singletonList(record);
    }
}
