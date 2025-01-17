package vento.weather_scraper.handler.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vento.weather_scraper.config.OpenWeatherMapConfig;
import vento.weather_scraper.model.CsvConvertible;
import vento.weather_scraper.model.OpenWeatherMapSolarRecord;
import vento.weather_scraper.utils.FileUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Implements data scraping specifically for the OpenWeatherMap Solar Irradiance API.
 */
@Component
public class OpenWeatherMapSolarScraper extends WeatherScraperImpl {
    private final OpenWeatherMapConfig config;

    private LocalDate lastFetchDate = LocalDate.now();

    /**
     * Initializes the OpenWeatherMapSolarScraper with the necessary configuration.
     */
    public OpenWeatherMapSolarScraper(@Autowired OpenWeatherMapConfig config) {
        this.config = config;
        setFetchDelay(config.getDelay());
    }

    /**
     * Executes the process of fetching, decoding, and saving weather data when a new day starts.
     */
    @Override
    public void scrapWeather() {
        if (LocalDate.now().isAfter(lastFetchDate) && LocalDateTime.now().toLocalTime().isAfter(LocalTime.of(0, 10))) {
            super.scrapWeather();
            // Update lastFetchDate after successful save only.
        }
    }

    /**
     * Builds the URL used for fetching weather data from the OpenWeatherMap Solar Irradiance API.
     *
     * @return A string representing the complete URL for the API request.
     */
    @Override
    public String buildQueryURL() {
        return String.format(
                "https://api.openweathermap.org/energy/1.0/solar/interval_data?lat=%s&lon=%s&date=%s&interval=15m&appid=%s",
                config.getLatitude(),
                config.getLongitude(),
                lastFetchDate.toString(),
                config.getToken()
        );
    }

    /**
     * Decodes the raw JSON data fetched from the OpenWeatherMap Solar Irradiance API into a structured CsvConvertible format.
     *
     * @param rawData The raw JSON data fetched from the API.
     * @return A list of CsvConvertible objects containing the structured weather data.
     */
    @Override
    public List<CsvConvertible> decodeData(String rawData) {
        final JsonObject jsonObject = getGson().fromJson(rawData, JsonObject.class);
        final JsonObject irradiance = jsonObject.get("irradiance").getAsJsonObject();
        final JsonArray intervals = irradiance.get("intervals").getAsJsonArray();

        final List<CsvConvertible> records = new ArrayList<>();
        for (JsonElement intervalElement : intervals) {
            final JsonObject interval = intervalElement.getAsJsonObject();
            records.add(OpenWeatherMapSolarRecord.builder()
                    .timestamp(lastFetchDate.toString())
                    .startTime(interval.get("start").getAsString())
                    .endTime(interval.get("end").getAsString())
                    .clearSkyGHI(interval.getAsJsonObject("clear_sky").get("ghi").getAsDouble())
                    .clearSkyDNI(interval.getAsJsonObject("clear_sky").get("dni").getAsDouble())
                    .clearSkyDHI(interval.getAsJsonObject("clear_sky").get("dhi").getAsDouble())
                    .cloudySkyGHI(interval.getAsJsonObject("cloudy_sky").get("ghi").getAsDouble())
                    .cloudySkyDNI(interval.getAsJsonObject("cloudy_sky").get("dni").getAsDouble())
                    .cloudySkyDHI(interval.getAsJsonObject("cloudy_sky").get("dhi").getAsDouble())
                    .build());
        }

        return records;
    }

    /**
     * Saves the decoded data to a CSV file using the provided CsvConvertible record.
     *
     * @param csvRecords The list of CsvConvertible objects to be saved.
     * @throws Exception if there is an error during file writing.
     */
    @Override
    public void saveData(List<CsvConvertible> csvRecords) throws Exception {
        FileUtils.writeCSV(getApiName(), getFormatter().format(lastFetchDate), csvRecords);
        lastFetchDate = LocalDate.now();    // Update lastFetchDate after successful save
    }
}
