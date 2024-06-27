package vento.weather_scraper.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vento.weather_scraper.service.LoggerService;
import vento.weather_scraper.service.TelegramService;

@Service
public class LoggerServiceImpl implements LoggerService {
    @Autowired
    private TelegramService telegram;

    @Override
    public void info(String message) {
        final String consoleMessage = "[INFO]" + " " + message;
        System.out.println(consoleMessage);

        final String telegramMessage = "\uD83D\uDCD7" + " *Info* " + "\n\n" + formatMessageWithMarkdown(message);
        telegram.sendTelegramMessage(telegramMessage);
    }

    @Override
    public void error(String message, Throwable e) {
        final String consoleMessage = "[ERROR]" + " " + message;
        System.err.println(consoleMessage);
        e.printStackTrace(System.out);

        final String telegramMessage = "\uD83D\uDCD5" + " *Error* " + "\n\n" + formatMessageWithMarkdown(message);
        telegram.sendTelegramMessage(telegramMessage);
    }

    private String formatMessageWithMarkdown(String message) {
        return message
                .replace("_", " ")
                .replace("\"", "`");
    }
}
