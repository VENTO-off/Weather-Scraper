package vento.weather_scraper.model;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Interface defining the ability of an object to be converted into a CSV format.
 */
public interface CsvConvertible {

    /**
     * Converts this object into a CsvRecord by reflecting over its fields.
     *
     * @return CsvRecord containing the values of the object's fields.
     * @throws RuntimeException if any field is inaccessible.
     */
    default CsvRecord toCsvRecord() {
        final List<Object> values = new ArrayList<>();
        final Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);  // Make sure to access private fields
            try {
                values.add(field.get(this));
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Error accessing field value", e);
            }
        }

        return new CsvRecord(values.toArray());
    }

    /**
     * Generates an array of headers for a CSV file based on the object's field names.
     *
     * @return An array of strings representing the field names, to be used as headers in a CSV file.
     */
    default String[] getCsvHeaders() {
        Field[] fields = this.getClass().getDeclaredFields();
        String[] headers = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            headers[i] = fields[i].getName();
        }

        return headers;
    }
}
