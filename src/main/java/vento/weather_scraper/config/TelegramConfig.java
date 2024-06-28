package vento.weather_scraper.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class to manage Telegram API.
 */
@Configuration
@ConfigurationProperties(prefix = "telegram")
@Data
public class TelegramConfig {

    /**
     * Telegram bot token for API access. Configured in application properties.
     */
    private String token;

    /**
     * Chat ID for sending messages via Telegram. Configured in application properties.
     */
    private Long chatId;
}
