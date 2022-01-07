package main;

import annual.AnnualChanges;
import checker.Checker;
import common.Constants;
import fileio.InputLoader;
import org.json.simple.parser.ParseException;
import players.Database;

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

            String filepath = Constants.OUTPUT_PATH + file.getName();
            File out = new File(filepath);
            boolean isCreated = out.createNewFile();
            if (isCreated) {
                action(file.getAbsolutePath(), filepath);
            }
        }

        Checker.calculateScore();
    }

    /**
     * here takes place all the action
     * @param filePath1
     * @param filePath2
     * @throws IOException
     */
    public static void action(final String filePath1,
                              final String filePath2) throws IOException {
        InputLoader inputLoader = new InputLoader(filePath1);
        inputLoader.readData();
        Database database = Database.getDatabase();
        /*System.out.println(database.getInitialBudget());
        //System.out.println(database.getNumberOfYears());
        //System.out.println(database.getInitialChildren().size());
        //System.out.println(database.getInitialGifts().size());
        for (AnnualChanges year : database.getAnnualChanges()) {
            System.out.println(year.getNewBudget());
        }*/
    }
}
