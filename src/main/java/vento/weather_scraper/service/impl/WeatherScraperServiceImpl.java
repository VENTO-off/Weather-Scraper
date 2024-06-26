package vento.weather_scraper.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
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
    @Qualifier("visualCrossingToken")
    private String visualCrossingToken;
    @Autowired
    @Qualifier("visualCrossingCoords")
    private String visualCrossingCoords;

    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private final List<WeatherApi> apiHandlers = new ArrayList<>();

    @PostConstruct
    private void init() {
        apiHandlers.add(new VisualCrossingApi("visual_crossing", visualCrossingToken, visualCrossingCoords));

        scheduler.scheduleWithFixedDelay(this::fetchWeatherAPIs, 0, 15, TimeUnit.MINUTES);
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
