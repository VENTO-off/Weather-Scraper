package vento.weather_scraper.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * Represents a weather record from VisualCrossing API.
 */
@Data
public class VisualCrossingRecord implements CsvConvertible {
    @SerializedName("datetime")
    private String datetime;
    @SerializedName("datetimeEpoch")
    private long datetimeEpoch;
    @SerializedName("temp")
    private double temp;
    @SerializedName("feelslike")
    private double feelsLike;
    @SerializedName("humidity")
    private double humidity;
    @SerializedName("dew")
    private double dew;
    @SerializedName("precip")
    private double precip;
    @SerializedName("precipprob")
    private double precipProb;
    @SerializedName("snow")
    private double snow;
    @SerializedName("snowdepth")
    private double snowDepth;
    @SerializedName("windgust")
    private double windGust;
    @SerializedName("windspeed")
    private double windSpeed;
    @SerializedName("winddir")
    private int windDir;
    @SerializedName("pressure")
    private int pressure;
    @SerializedName("cloudcover")
    private double cloudCover;
    @SerializedName("solarradiation")
    private int solarRadiation;
    @SerializedName("solarenergy")
    private double solarEnergy;
    @SerializedName("uvindex")
    private int uvIndex;
    @SerializedName("conditions")
    private String conditions;
    @SerializedName("icon")
    private String icon;
    @SerializedName("source")
    private String source;
    @SerializedName("sunrise")
    private String sunrise;
    @SerializedName("sunriseEpoch")
    private long sunriseEpoch;
    @SerializedName("sunset")
    private String sunset;
    @SerializedName("sunsetEpoch")
    private long sunsetEpoch;
    @SerializedName("moonphase")
    private double moonPhase;
}
