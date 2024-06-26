package vento.weather_scraper.exception;

public class CsvException extends Exception {
    public CsvException(String fileName) {
        super(String.format("An error has occurred while writing CSV file: %s", fileName));
    }
}
