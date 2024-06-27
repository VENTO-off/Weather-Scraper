package vento.weather_scraper.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
@Getter
public class ApplicationConfig {
    @Value("${scheduler.delay}")
    private Long schedulerDelay;

    @Value("${telegram.token}")
    private String telegramToken;

    @Value("${telegram.chat_id}")
    private Long telegramChatId;

    @Value("${visual_crossing.token}")
    private String visualCrossingToken;

    @Value("${visual_crossing.coords}")
    private String visualCrossingCoords;
}
