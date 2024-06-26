package vento.weather_scraper.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    @Value("${visual_crossing.token}")
    private String visualCrossingToken;
    @Value("${visual_crossing.coords}")
    private String visualCrossingCoords;

    @Bean
    public Gson gson() {
        return new GsonBuilder().create();
    }

    /**
     * Visual Crossing API token.
     */
    @Bean(name = "visualCrossingToken")
    public String visualCrossingToken() {
        return visualCrossingToken;
    }

    /**
     * Visual Crossing coordinates.
     */
    @Bean(name = "visualCrossingCoords")
    public String visualCrossingCoords() {
        return visualCrossingCoords;
    }
}
