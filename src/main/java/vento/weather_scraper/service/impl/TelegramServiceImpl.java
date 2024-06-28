package vento.weather_scraper.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vento.weather_scraper.config.TelegramConfig;
import vento.weather_scraper.service.TelegramService;
import vento.weather_scraper.utils.HttpUtils;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * Service implementation for interacting with the Telegram API.
 */
@Service
public class TelegramServiceImpl implements TelegramService {
    @Autowired
    private TelegramConfig config;

    /**
     * Sends a message to a Telegram chat using the configured bot.
     *
     * @param message The message to be sent to Telegram.
     */
    public void sendMessage(String message) {
        try {
            HttpUtils.callAPI(buildSendMessageQueryURL(URLEncoder.encode(message, StandardCharsets.UTF_8.name())));
        } catch (Exception ignored) {
        }
    }

    /**
     * Builds the URL used for the HTTP request to send the message.
     *
     * @param text The URL-encoded text to be sent as part of the query.
     * @return The complete URL for making the API call to Telegram.
     */
    private String buildSendMessageQueryURL(String text) {
        return String.format("https://api.telegram.org/bot%s/sendMessage?chat_id=%d&text=%s&parse_mode=markdown",
                config.getToken(),
                config.getChatId(),
                text);
    }
}
