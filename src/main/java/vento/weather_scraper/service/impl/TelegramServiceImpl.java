package vento.weather_scraper.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vento.weather_scraper.config.ApplicationConfig;
import vento.weather_scraper.service.TelegramService;
import vento.weather_scraper.utils.HttpUtils;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class TelegramServiceImpl implements TelegramService {
    @Autowired
    private ApplicationConfig config;

    public void sendTelegramMessage(String message) {
        try {
            HttpUtils.callAPI(buildQueryURL(URLEncoder.encode(message, StandardCharsets.UTF_8.name())));
        } catch (Exception ignored) {
        }
    }

    private String buildQueryURL(String text) {
        return String.format("https://api.telegram.org/bot%s/sendMessage?chat_id=%d&text=%s&parse_mode=markdown", config.getTelegramToken(), config.getTelegramChatId(), text);
    }
}
