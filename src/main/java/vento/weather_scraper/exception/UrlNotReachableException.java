package vento.weather_scraper.exception;

public class UrlNotReachableException extends Exception {
    public UrlNotReachableException(String url) {
        super(String.format("An error has occurred while connecting to URL: %s", url));
    }
}
