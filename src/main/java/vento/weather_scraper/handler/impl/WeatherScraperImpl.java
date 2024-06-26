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
 * Weather scraper abstract implementation.
 */
public abstract class WeatherScraperImpl implements WeatherApi, WeatherScraper {
    private final String apiName;
    protected final DateTimeFormatter formatter;
    protected final Gson gson;

    /**
     * Constructor.
     */
    public WeatherScraperImpl(String apiName) {
        this.apiName = apiName;
        this.formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.gson = new Gson();
    }

    /**
     * Fetch data using API, decode data, save data.
     */
    @Override
    public void scrapWeather() throws Exception {
        final String fetchedData = this.fetchData();
        final CsvConvertible decodedData = this.decodeData(fetchedData);
        this.saveData(decodedData);
    }

    /**
     * Return API service name.
     */
    @Override
    public String getApiName() {
        return this.apiName;
    }

    /**
     * Fetch data using API.
     */
    @Override
    public String fetchData() throws Exception {
        return HttpUtils.callAPI(buildQueryURL());
    }

    /**
     * Save data to .csv file.
     */
    @Override
    public void saveData(CsvConvertible csvRecord) throws Exception {
        FileUtils.writeCSV(apiName, formatter.format(LocalDate.now()), csvRecord);
    }
}
