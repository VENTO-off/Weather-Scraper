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
 * Utility class for file operations, specifically for working with CSV files.
 */
public class FileUtils {
    private static final String dataDirectory = "datasets";

    /**
     * Writes data from a CsvConvertible object to a CSV file located in the specified directory and file name.
     * If the file does not exist, it is created with headers.
     *
     * @param directory The directory where the file will be stored.
     * @param fileName  The name of the file without the extension.
     * @param csvRecord An object that can be converted to CSV format.
     * @throws CsvException If there is an error during writing to the CSV file.
     * @throws IOException  If an I/O error occurs when opening or creating the file.
     */
    public static void writeCSV(String directory, String fileName, CsvConvertible csvRecord) throws CsvException, IOException {
        final Path path = Paths.get(dataDirectory, directory.toLowerCase().replaceAll("\\s", "_"), fileName + ".csv");
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
