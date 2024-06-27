package vento.weather_scraper.exception;

/**
 * Custom exception class for handling CSV file-related errors.
 */
public class CsvException extends Exception {

    /**
     * Constructor for CsvException that formats a detailed error message specifying
     * the file name that caused the exception.
     *
     * @param fileName the name of the CSV file involved in the error
     */
    public CsvException(String fileName) {
        super(String.format("An error has occurred while writing CSV file: %s", fileName));
    }
}
