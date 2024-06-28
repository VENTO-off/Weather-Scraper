package vento.weather_scraper.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class to manage OpenWeatherMap API.
 */
@Configuration
@ConfigurationProperties(prefix = "open-weather-map")
@Data
public class OpenWeatherMapConfig {

    /**
     * Delay for scheduler in minutes. Configured in application properties.
     */
    private Long delay;

    /**
     * Token for accessing the OpenWeatherMap API. Configured in application properties.
     */
    private String token;

    /**
     * Latitude coordinate for which weather data is fetched, used with the OpenWeatherMap API. Configured in application properties.
     */
    private String latitude;

    /**
     * Longitude coordinate for which weather data is fetched, used with the OpenWeatherMap API. Configured in application properties.
     */
    private String longitude;
}
