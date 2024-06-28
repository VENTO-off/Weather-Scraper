package vento.weather_scraper.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Represents a weather record from OpenWeatherMap API.
 */
@Data
public class OpenWeatherMapRecord implements CsvConvertible {
    private String timestamp = LocalDateTime.now().toString();

    @SerializedName("dt")
    private long dt;
    @SerializedName("sunrise")
    private long sunrise;
    @SerializedName("sunset")
    private long sunset;
    @SerializedName("temp")
    private double temp;
    @SerializedName("feels_like")
    private double feelsLike;
    @SerializedName("pressure")
    private int pressure;
    @SerializedName("humidity")
    private int humidity;
    @SerializedName("dew_point")
    private double dewPoint;
    @SerializedName("uvi")
    private double uvIndex;
    @SerializedName("clouds")
    private int clouds;
    @SerializedName("visibility")
    private int visibility;
    @SerializedName("wind_speed")
    private double windSpeed;
    @SerializedName("wind_deg")
    private int windDegree;
    @SerializedName("wind_gust")
    private double windGust;
}
