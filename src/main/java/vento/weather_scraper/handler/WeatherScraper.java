package vento.weather_scraper.handler;

import vento.weather_scraper.model.CsvConvertible;

/**
 * Interface for handling the fetching, decoding, and saving of weather data from an API.
 */
public interface WeatherScraper {

    void startScheduler(long delay);

    /**
     * Builds the query URL for fetching weather data.
     *
     * @return The fully constructed URL as a String.
     */
    String buildQueryURL();

    /**
     * Fetches the raw data from the weather API using the URL provided by buildQueryURL().
     *
     * @return The raw data as a string.
     * @throws Exception to handle any issues that occur during the HTTP request.
     */
    String fetchData() throws Exception;

    /**
     * Decodes the raw weather data fetched from the API into a CsvConvertible format.
     *
     * @param rawData The raw data string obtained from fetchData().
     * @return A CsvConvertible object representing the structured data.
     */
    CsvConvertible decodeData(String rawData);

    /**
     * Saves the decoded data into a CSV format.
     *
     * @param csvRecord The CsvConvertible data to be saved.
     * @throws Exception to handle issues related to file access or writing operations.
     */
    void saveData(CsvConvertible csvRecord) throws Exception;
}
