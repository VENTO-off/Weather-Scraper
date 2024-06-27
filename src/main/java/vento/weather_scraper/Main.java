package vento.weather_scraper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The Main class acts as the launch point for the Weather Scraper application.
 */
@SpringBootApplication(scanBasePackages = "vento.weather_scraper")
public class Main {

    /**
     * Main method to launch the Spring Boot application.
     *
     * @param args The command line arguments passed to the application.
     */
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
