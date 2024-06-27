package vento.weather_scraper.service;

/**
 * Interface for a service that provides functionality to send messages through Telegram.
 */
public interface TelegramService {
    /**
     * Sends a message to a Telegram chat using the configured bot.
     *
     * @param message The message to be sent to Telegram.
     */
    void sendMessage(String message);
}
