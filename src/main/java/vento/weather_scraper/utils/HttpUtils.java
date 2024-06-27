package vento.weather_scraper.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import vento.weather_scraper.exception.UrlNotReachableException;

import java.nio.charset.StandardCharsets;

/**
 * Utility class for making HTTP requests.
 */
public class HttpUtils {

    /**
     * Executes an HTTP GET request to the specified URL and returns the response content as a string.
     *
     * @param apiURL The URL to which the GET request is sent.
     * @return The response body as a string.
     * @throws UrlNotReachableException If the URL is not reachable, or an error occurs during the request.
     */
    public static String callAPI(String apiURL) throws UrlNotReachableException {
        try (final CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            final HttpGet getRequest = new HttpGet(apiURL);
            final HttpResponse response = httpClient.execute(getRequest);
            final HttpEntity entity = response.getEntity();
            int statusCode = response.getStatusLine().getStatusCode();

            // Check if the HTTP request was successful
            if (statusCode >= 200 && statusCode < 300) {
                return EntityUtils.toString(entity, StandardCharsets.UTF_8);
            } else {
                throw new UrlNotReachableException(apiURL, statusCode);
            }
        } catch (Exception e) {
            throw new UrlNotReachableException(apiURL);
        }
    }
}
