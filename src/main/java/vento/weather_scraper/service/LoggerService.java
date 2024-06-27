package vento.weather_scraper.service;

/**
 * Interface for a logging service that provides methods for logging information and errors.
 */
public interface LoggerService {
    /**
     * Logs informational messages both to the console and via Telegram with specific formatting.
     *
     * @param message The message to be logged and sent.
     */
    void info(String message);

    /**
     * Logs error messages and stack traces both to the console and via Telegram with specific formatting.
     * Additionally, this method prints the stack trace to the standard error stream.
     *
     * @param message The error message to be logged and sent.
     * @param e       The exception associated with the error.
     */
    void error(String message, Throwable e);
}
