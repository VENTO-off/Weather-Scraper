package vento.weather_scraper.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vento.weather_scraper.service.LoggerService;
import vento.weather_scraper.service.TelegramService;

/**
 * Service implementation for logging messages.
 */
@Service
public class LoggerServiceImpl implements LoggerService {
    @Autowired
    private TelegramService telegram;

    /**
     * Logs informational messages both to the console and via Telegram with specific formatting.
     *
     * @param message The message to be logged and sent.
     */
    @Override
    public void info(String message) {
        final String consoleMessage = "[INFO]" + " " + message;
        System.out.println(consoleMessage);

        final String telegramMessage = "\uD83D\uDCD7" + " *Info* " + "\n\n" + formatMessageWithMarkdown(message);
        telegram.sendTelegramMessage(telegramMessage);
    }

    /**
     * Logs error messages and stack traces both to the console and via Telegram with specific formatting.
     * Additionally, this method prints the stack trace to the standard error stream.
     *
     * @param message The error message to be logged and sent.
     * @param e       The exception associated with the error.
     */
    @Override
    public void error(String message, Throwable e) {
        final String consoleMessage = "[ERROR]" + " " + message;
        System.err.println(consoleMessage);
        e.printStackTrace(System.out);

        final String telegramMessage = "\uD83D\uDCD5" + " *Error* " + "\n\n" + formatMessageWithMarkdown(message);
        telegram.sendTelegramMessage(telegramMessage);
    }

    /**
     * Formats messages to be compatible with Markdown syntax used by Telegram.
     *
     * @param message The original message.
     * @return The formatted message with Markdown-friendly characters.
     */
    private String formatMessageWithMarkdown(String message) {
        return message
                .replace("_", " ")
                .replace("\"", "`");
    }
}
