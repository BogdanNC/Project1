package main;

import checker.Checker;
import common.Constants;
import org.json.simple.parser.ParseException;
import runtime.Runtime;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * Class used to run the code
 */
public final class Main {
    /**
     * here i delete the old files
     * @param directory
     */
    public static void deleteFiles(final File[] directory) {
        if (directory != null) {
            for (File file : directory) {
                if (!file.delete()) {
                    System.out.println("nu s-a sters");
                }
            }
        }
    }

    /**
     * for checkstyle
     */
    private Main() {
        ///constructor for checkstyle
    }
    /**
     * This method is used to call the checker which calculates the score
     * @param args
     *          the arguments used to call the main method
     */
    public static void main(final String[] args) throws IOException, ParseException {
        File directory = new File(Constants.TESTS_PATH);
        Path path = Paths.get(Constants.RESULT_PATH);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
        File outputDirectory = new File(Constants.RESULT_PATH);

        deleteFiles(outputDirectory.listFiles());

        for (File file : Objects.requireNonNull(directory.listFiles())) {
                String fileName = file.getName();
                fileName = fileName.substring(Constants.FANTASTIC_FOUR);
                    String filepath = Constants.OUTPUT_PATH + fileName;
                    File out = new File(filepath);
                    boolean isCreated = out.createNewFile();
                    if (isCreated) {
                        File testFile = new File("tests/" + file.getName());
                        Runtime.action(testFile.getPath(), filepath);
                    }
        }
        Checker.calculateScore();
    }
}
