package vento.weather_scraper.service;

public interface LoggerService {
    void info(String message);

    void error(String message, Throwable e);
}
