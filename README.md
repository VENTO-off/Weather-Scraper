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

Update application properties::

```properties
scheduler.delay=5
telegram.token=YOUR_TELEGRAM_BOT_TOKEN
telegram.chat_id=YOUR_TELEGRAM_CHAT_ID
visual_crossing.token=YOUR_VISUAL_CROSSING_API_TOKEN
visual_crossing.coords=YOUR_VISUAL_CROSSING_COORDINATES
```
