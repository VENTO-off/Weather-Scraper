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
import java.util.List;

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
     * @param csvRecords A list of objects that can be converted to CSV format.
     * @throws CsvException If there is an error during writing to the CSV file.
     * @throws IOException  If an I/O error occurs when opening or creating the file.
     */
    public static void writeCSV(String directory, String fileName, List<CsvConvertible> csvRecords) throws CsvException, IOException {
        if (csvRecords.isEmpty()) {
            return;
        }

        final Path path = Paths.get(dataDirectory, directory.toLowerCase().replaceAll("\\s", "_"), fileName + ".csv");
        Files.createDirectories(path.getParent());

        boolean isNewFile = Files.notExists(path);
        try (
                final BufferedWriter writer = Files.newBufferedWriter(path, java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.APPEND);
                final CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);
        ) {
            if (isNewFile) {
                csvPrinter.printRecord((Object[]) csvRecords.get(0).getCsvHeaders());
            }
            for (CsvConvertible csv : csvRecords) {
                csvPrinter.printRecord(csv.toCsvRecord().getFields());
            }
            csvPrinter.flush();
        } catch (IOException e) {
            throw new CsvException(fileName);
        }
    }
}
