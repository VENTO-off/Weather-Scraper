package vento.weather_scraper.handler.impl;

import com.google.gson.JsonObject;
import vento.weather_scraper.model.CsvConvertible;
import vento.weather_scraper.model.VisualCrossingRecord;

import java.time.LocalDate;

public class VisualCrossingScraper extends WeatherScraperImpl {
    private final String visualCrossingToken;
    private final String visualCrossingCoords;

    /**
     * Visual Crossing constructor.
     */
    public VisualCrossingScraper(String apiName, String visualCrossingToken, String visualCrossingCoords) {
        super(apiName);
        this.visualCrossingToken = visualCrossingToken;
        this.visualCrossingCoords = visualCrossingCoords;
    }

    /**
     * Build a query for Visual Crossing API.
     */
    @Override
    public String buildQueryURL() {
        return String.format("https://weather.visualcrossing.com/" +
                "VisualCrossingWebServices/rest/services/timeline/" +
                "%s?unitGroup=metric&include=current&key=%s&contentType=json", this.visualCrossingCoords, this.visualCrossingToken);
    }

    /**
     * Decode Visual Crossing JSON data.
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
