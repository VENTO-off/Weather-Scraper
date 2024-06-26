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
 * HTTP utils.
 */
public class HttpUtils {

    public static String callAPI(String apiURL) throws UrlNotReachableException {
        try (final CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            final HttpGet getRequest = new HttpGet(apiURL);
            final HttpResponse response = httpClient.execute(getRequest);
            final HttpEntity entity = response.getEntity();

            return EntityUtils.toString(entity, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new UrlNotReachableException(apiURL);
        }
    }
}
