# Weather-Scraper

**Weather Scraper** is a Java-based application built with Spring Boot. Its primary purpose is to fetch current weather
conditions from different Weather APIs and save the data in CSV format.

## Features

- Fetch weather data from multiple APIs
- Convert weather data to CSV format
- Log messages to the console and send alerts via Telegram

## Technologies used

- Java
- Spring Boot
- Apache HTTP Client
- Google Gson
- Apache Commons CSV
- Lombok

## Getting started

### Configuration

Update `application properties`:

```properties
#
# Telegram settings
#
telegram.token=YOUR_TELEGRAM_BOT_TOKEN
telegram.chat-id=YOUR_TELEGRAM_CHAT_ID
#
# Visual Crossing settings
#
visual-crossing.delay=SCHEDULER_DELAY
visual-crossing.token=YOUR_VISUAL_CROSSING_API_TOKEN
visual-crossing.coords=YOUR_VISUAL_CROSSING_COORDINATES
#
# OpenWeatherMap
#
open-weather-map.delay=SCHEDULER_DELAY
open-weather-map.token=YOUR_OPEN_WEATHER_MAP_API_TOKEN
open-weather-map.latitude=YOUR_OPEN_WEATHER_MAP_LATITUDE
open-weather-map.longitude=YOUR_OPEN_WEATHER_MAP_LONGITUDE

```

### Adding a new Weather API

1. Create a configuration class inside `config` directory.
2. Extend `WeatherScraperImpl` to create a new class for the API.
3. Define the methods `@PostConstruct`, `buildQueryURL`, `fetchData`, and `decodeData`.
