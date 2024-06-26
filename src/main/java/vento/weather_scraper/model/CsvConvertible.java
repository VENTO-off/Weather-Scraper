package vento.weather_scraper.model;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * CSV Convertible interface.
 */
public interface CsvConvertible {
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
