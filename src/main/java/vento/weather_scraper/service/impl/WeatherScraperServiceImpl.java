package vento.weather_scraper.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vento.weather_scraper.config.VisualCrossingConfig;
import vento.weather_scraper.handler.WeatherApi;
import vento.weather_scraper.handler.impl.VisualCrossingScraper;
import vento.weather_scraper.service.LoggerService;
import vento.weather_scraper.service.WeatherScraperService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Service implementation for scraping weather data from various APIs.
 */
@Component
public class WeatherScraperServiceImpl implements WeatherScraperService {
    @Autowired
    private LoggerService logger;
    @Autowired
    private VisualCrossingConfig config;

    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private final List<WeatherApi> apiHandlers = new ArrayList<>();

    /**
     * Initializes the service by setting up API handlers and scheduling regular weather data fetch tasks.
     */
    @PostConstruct
    private void init() {
        apiHandlers.add(new VisualCrossingScraper("Visual crossing", config.getToken(), config.getCoords()));
        scheduler.scheduleAtFixedRate(this::fetchWeatherAPIs, 0, config.getDelay(), TimeUnit.MINUTES);
        logger.info("Weather scraper was started.");
    }

    /**
     * Fetches weather data from all configured APIs.
     */
    @Override
    public void fetchWeatherAPIs() {
        for (WeatherApi handler : apiHandlers) {
            try {
                handler.scrapWeather();
            } catch (Exception e) {
                logger.error("Error fetching data from \"" + handler.getApiName() + "\".", e);
            }
        }
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
        logger.info("Weather scraper was stopped.");
    }
}
