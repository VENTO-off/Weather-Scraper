package vento.weather_scraper.model;

/**
 * CSV Convertible interface.
 */
public interface CsvConvertible {
    CsvRecord toCsvRecord();

    String[] getCsvHeaders();
}
