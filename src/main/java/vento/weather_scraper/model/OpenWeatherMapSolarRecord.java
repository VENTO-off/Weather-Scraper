package vento.weather_scraper.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Represents a weather record from OpenWeatherMap Solar Irradiance API.
 */
@Data
@Builder
public class OpenWeatherMapSolarRecord implements CsvConvertible {
    private String timestamp = LocalDateTime.now().toString();

    private String startTime;

    private String endTime;

    private double clearSkyGHI;

    private double clearSkyDNI;

    private double clearSkyDHI;

    private double cloudySkyGHI;

    private double cloudySkyDNI;

    private double cloudySkyDHI;
}
