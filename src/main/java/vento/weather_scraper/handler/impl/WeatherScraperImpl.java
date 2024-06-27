package vento.weather_scraper.handler.impl;

import com.google.gson.Gson;
import vento.weather_scraper.handler.WeatherApi;
import vento.weather_scraper.handler.WeatherScraper;
import vento.weather_scraper.model.CsvConvertible;
import vento.weather_scraper.utils.FileUtils;
import vento.weather_scraper.utils.HttpUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Abstract base class for implementing weather data scraper services.
 * Provides a common framework for fetching, decoding, and saving weather data from various APIs.
 */
public abstract class WeatherScraperImpl implements WeatherApi, WeatherScraper {
    private final String apiName;
    protected final DateTimeFormatter formatter;
    protected final Gson gson;

    /**
     * Constructs a new WeatherScraperImpl with the specified API name.
     *
     * @param apiName the name of the API service this scraper is associated with.
     */
    public WeatherScraperImpl(String apiName) {
        this.apiName = apiName;
        this.formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.gson = new Gson();
    }

    /**
     * Executes the process of fetching, decoding, and saving weather data.
     *
     * @throws Exception if an error occurs during the scraping process.
     */
    @Override
    public void scrapWeather() throws Exception {
        final String fetchedData = this.fetchData();
        final CsvConvertible decodedData = this.decodeData(fetchedData);
        this.saveData(decodedData);
    }

    /**
     * Returns the name of the API service.
     *
     * @return the name of the API.
     */
    @Override
    public String getApiName() {
        return this.apiName;
    }

    /**
     * Fetches data from the API.
     *
     * @return The fetched data as a string.
     * @throws Exception if there is an error in fetching the data, encapsulating both network issues and HTTP errors.
     */
    @Override
    public String fetchData() throws Exception {
        return HttpUtils.callAPI(buildQueryURL());
    }

    /**
     * Saves the decoded data to a CSV file using the provided CsvConvertible record.
     *
     * @param csvRecord the CsvConvertible data to be saved.
     * @throws Exception if there is an error during file writing.
     */
    @Override
    public void saveData(CsvConvertible csvRecord) throws Exception {
        FileUtils.writeCSV(apiName, formatter.format(LocalDate.now()), csvRecord);
    }
}
