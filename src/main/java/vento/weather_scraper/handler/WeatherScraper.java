package vento.weather_scraper.handler;

import vento.weather_scraper.model.CsvConvertible;

/**
 * Weather scraper interface.
 */
public interface WeatherScraper {
    String buildQueryURL();

    String fetchData() throws Exception;

    CsvConvertible decodeData(String rawData);

    void saveData(CsvConvertible csvRecord) throws Exception;
}
