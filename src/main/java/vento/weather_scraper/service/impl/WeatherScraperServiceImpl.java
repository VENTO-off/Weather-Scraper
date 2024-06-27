package vento.weather_scraper.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vento.weather_scraper.config.ApplicationConfig;
import vento.weather_scraper.handler.WeatherApi;
import vento.weather_scraper.handler.impl.VisualCrossingApi;
import vento.weather_scraper.service.WeatherScraperService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Weather scraper API service.
 */
@Component
public class WeatherScraperServiceImpl implements WeatherScraperService {
    @Autowired
    private ApplicationConfig config;

    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private final List<WeatherApi> apiHandlers = new ArrayList<>();

    @PostConstruct
    private void init() {
        apiHandlers.add(new VisualCrossingApi("visual_crossing", config.getVisualCrossingToken(), config.getVisualCrossingCoords()));

        scheduler.scheduleAtFixedRate(this::fetchWeatherAPIs, 0, config.getSchedulerDelay(), TimeUnit.MINUTES);
    }

    @Override
    public void fetchWeatherAPIs() {
        for (WeatherApi handler : apiHandlers) {
            try {
                handler.scrapWeather();
            } catch (Exception e) {
                System.out.println("An error has occurred while scraping data from \"" + handler.getApiName() + "\".");
                e.printStackTrace();
            }
        }
    }

    @PreDestroy
    private void shutdown() {
        scheduler.shutdown();
    }
}
