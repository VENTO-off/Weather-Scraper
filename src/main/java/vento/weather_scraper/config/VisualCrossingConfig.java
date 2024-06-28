package vento.weather_scraper.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class to manage VisualCrossing API.
 */
@Configuration
@ConfigurationProperties(prefix = "visual-crossing")
@Data
public class VisualCrossingConfig {

    /**
     * Delay for scheduler in minutes. Configured in application properties.
     */
    private Long delay;

    /**
     * Token for accessing the VisualCrossing API. Configured in application properties.
     */
    private String token;

    /**
     * Coordinates for which weather data is fetched, used with the VisualCrossing API. Configured in application properties.
     */
    private String coords;
}
