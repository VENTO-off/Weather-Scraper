package vento.weather_scraper.handler.impl;

import com.google.gson.JsonObject;
import vento.weather_scraper.model.CsvConvertible;
import vento.weather_scraper.model.VisualCrossingRecord;

import java.time.LocalDate;

/**
 * Implements data scraping specifically for the Visual Crossing Weather API.
 */
public class VisualCrossingScraper extends WeatherScraperImpl {
    private final String visualCrossingToken;
    private final String visualCrossingCoords;

    /**
     * Constructs a VisualCrossingScraper with the necessary authentication and location details.
     *
     * @param apiName The name of the API, used for logging and identification purposes.
     * @param visualCrossingToken The API token required for accessing Visual Crossing services.
     * @param visualCrossingCoords The geographical coordinates used for fetching weather data.
     */
    public VisualCrossingScraper(String apiName, String visualCrossingToken, String visualCrossingCoords) {
        super(apiName);
        this.visualCrossingToken = visualCrossingToken;
        this.visualCrossingCoords = visualCrossingCoords;
    }

    /**
     * Builds the URL used for fetching weather data from the Visual Crossing API.
     *
     * @return A string representing the complete URL for the API request.
     */
    @Override
    public String buildQueryURL() {
        return String.format("https://weather.visualcrossing.com/" +
                "VisualCrossingWebServices/rest/services/timeline/" +
                "%s?unitGroup=metric&include=current&key=%s&contentType=json", this.visualCrossingCoords, this.visualCrossingToken);
    }

    /**
     * Decodes the raw JSON data fetched from the Visual Crossing API into a structured CsvConvertible format.
     *
     * @param rawData The raw JSON data fetched from the API.
     * @return A CsvConvertible object containing the structured weather data.
     */
    @Override
    public CsvConvertible decodeData(String rawData) {
        final JsonObject jsonObject = super.gson.fromJson(rawData, JsonObject.class);
        final JsonObject currentConditions = jsonObject.get("currentConditions").getAsJsonObject();
        final VisualCrossingRecord decodedData = super.gson.fromJson(currentConditions, VisualCrossingRecord.class);
        decodedData.setDatetime(super.formatter.format(LocalDate.now()).concat(" ").concat(decodedData.getDatetime()));

        return decodedData;
    }
}
