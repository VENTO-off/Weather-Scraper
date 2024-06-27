package vento.weather_scraper.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Class representing a single CSV record.
 */
@Getter
@AllArgsConstructor
public class CsvRecord {
    /**
     * Array of objects that represents the fields of the CSV record.
     */
    private Object[] fields;
}
