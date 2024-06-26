package vento.weather_scraper.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Visual Crossing data object.
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

    @Override
    public CsvRecord toCsvRecord() {
        final List<Object> values = new ArrayList<>();
        final Field[] fields = VisualCrossingRecord.class.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            try {
                values.add(field.get(this));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return new CsvRecord(values.toArray());
    }

    @Override
    public String[] getCsvHeaders() {
        final Field[] fields = VisualCrossingRecord.class.getDeclaredFields();
        final String[] headers = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
            headers[i] = fields[i].getName();
        }

        return headers;
    }
}
