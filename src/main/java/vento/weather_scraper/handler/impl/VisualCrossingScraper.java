package vento.weather_scraper.handler.impl;

import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vento.weather_scraper.config.VisualCrossingConfig;
import vento.weather_scraper.model.CsvConvertible;
import vento.weather_scraper.model.VisualCrossingRecord;

import java.util.Collections;
import java.util.List;

/**
 * Implements data scraping specifically for the VisualCrossing API.
 */
@Component
public class VisualCrossingScraper extends WeatherScraperImpl {
    private final VisualCrossingConfig config;

    /**
     * Initializes the VisualCrossingScraper with the necessary configuration.
     */
    public VisualCrossingScraper(@Autowired VisualCrossingConfig config) {
        this.config = config;
        setFetchDelay(config.getDelay());
    }

    /**
     * Builds the URL used for fetching weather data from the VisualCrossing API.
     *
     * @return A string representing the complete URL for the API request.
     */
    @Override
    public String buildQueryURL() {
        return String.format(
                "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/%s?unitGroup=metric&include=current&key=%s&contentType=json",
                config.getCoords(),
                config.getToken()
        );
    }

    /**
     * Decodes the raw JSON data fetched from the VisualCrossing API into a structured CsvConvertible format.
     *
     * @param rawData The raw JSON data fetched from the API.
     * @return A list of CsvConvertible objects containing the structured weather data.
     */
    @Override
    public List<CsvConvertible> decodeData(String rawData) {
        final JsonObject jsonObject = getGson().fromJson(rawData, JsonObject.class);
        final JsonObject currentConditions = jsonObject.get("currentConditions").getAsJsonObject();
        final VisualCrossingRecord record = getGson().fromJson(currentConditions, VisualCrossingRecord.class);

        return Collections.singletonList(record);
    }
}
