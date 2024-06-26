package vento.weather_scraper.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import vento.weather_scraper.exception.CsvException;
import vento.weather_scraper.model.CsvConvertible;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * File utils.
 */
public class FileUtils {

    public static void writeCSV(String directory, String fileName, CsvConvertible csvRecord) throws CsvException, IOException {
        final Path path = Paths.get(directory, fileName + ".csv");
        Files.createDirectories(path.getParent());

        boolean isNewFile = Files.notExists(path);

        try (
                final BufferedWriter writer = Files.newBufferedWriter(path, java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.APPEND);
                final CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);
        ) {
            if (isNewFile) {
                csvPrinter.printRecord((Object[]) csvRecord.getCsvHeaders());
            }
            csvPrinter.printRecord(csvRecord.toCsvRecord().getFields());
            csvPrinter.flush();
        } catch (IOException e) {
            throw new CsvException(fileName);
        }
    }
}
