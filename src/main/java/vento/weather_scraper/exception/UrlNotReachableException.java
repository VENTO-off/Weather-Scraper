package vento.weather_scraper.exception;

/**
 * Custom exception class for handling errors when a URL is not reachable.
 */
public class UrlNotReachableException extends Exception {

    /**
     * Constructs a new UrlNotReachableException with a formatted message that includes the problematic URL.
     *
     * @param url The URL that could not be reached, triggering this exception.
     */
    public UrlNotReachableException(String url) {
        super(String.format("An error has occurred while connecting to URL: %s", url));
    }

    /**
     * Constructs a new UrlNotReachableException with a formatted message including the specific HTTP status code.
     *
     * @param url        The URL that returned a non-successful status code.
     * @param statusCode The HTTP status code returned by the URL.
     */
    public UrlNotReachableException(String url, int statusCode) {
        super(String.format("URL %s responded with status code: %d", url, statusCode));
    }
}
