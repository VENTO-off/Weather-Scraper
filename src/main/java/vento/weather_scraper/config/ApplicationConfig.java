package vento.weather_scraper.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class to manage application properties.
 */
@Configuration
@Getter
public class ApplicationConfig {

    /**
     * Delay for scheduler in milliseconds. Configured in application properties.
     */
    @Value("${scheduler.delay}")
    private Long schedulerDelay;

    /**
     * Telegram bot token for API access. Configured in application properties.
     */
    @Value("${telegram.token}")
    private String telegramToken;

    /**
     * Chat ID for sending messages via Telegram. Configured in application properties.
     */
    @Value("${telegram.chat_id}")
    private Long telegramChatId;

    /**
     * Token for accessing the Visual Crossing Weather API. Configured in application properties.
     */
    @Value("${visual_crossing.token}")
    private String visualCrossingToken;

    /**
     * Coordinates for which weather data is fetched, used with the Visual Crossing API. Configured in application properties.
     */
    @Value("${visual_crossing.coords}")
    private String visualCrossingCoords;
}
