package vento.weather_scraper.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * CSV record object.
 */
@Getter
@AllArgsConstructor
public class CsvRecord {
    private Object[] fields;
}
