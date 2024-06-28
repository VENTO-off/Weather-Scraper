package vento.weather_scraper.handler.impl;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import vento.weather_scraper.handler.WeatherApi;
import vento.weather_scraper.handler.WeatherScraper;
import vento.weather_scraper.model.CsvConvertible;
import vento.weather_scraper.service.LoggerService;
import vento.weather_scraper.utils.FileUtils;
import vento.weather_scraper.utils.HttpUtils;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Abstract base class for implementing weather data scraper services.
 * Provides a common framework for fetching, decoding, and saving weather data from various APIs.
 */
public abstract class WeatherScraperImpl implements WeatherApi, WeatherScraper {
    @Autowired
    private LoggerService logger;

    @Setter
    private Long schedulerDelay;

    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    @Getter
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Getter
    private final Gson gson = new Gson();

    /**
     * Initializes the service by setting up API handler and scheduling regular weather data fetch tasks.
     */
    @PostConstruct
    private void init() {
        scheduler.scheduleAtFixedRate(this::scrapWeather, 0, schedulerDelay, TimeUnit.MINUTES);
    }

    /**
     * Executes the process of fetching, decoding, and saving weather data.
     */
    @Override
    public void scrapWeather() {
        try {
            final String fetchedData = fetchData();
            final CsvConvertible decodedData = decodeData(fetchedData);
            saveData(decodedData);
        } catch (Exception e) {
            logger.error("Error fetching data from \"" + getApiName() + "\".", e);
        }
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
        FileUtils.writeCSV(getApiName(), formatter.format(LocalDate.now()), csvRecord);
    }

    /**
     * Shuts down the scheduled tasks and cleans up resources before the bean is destroyed.
     */
    @PreDestroy
    private void shutdown() {
        try {
            if (!scheduler.awaitTermination(5, TimeUnit.SECONDS)) {
                scheduler.shutdownNow();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
