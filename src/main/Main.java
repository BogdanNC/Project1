package main;

import annual.Operator;
import checker.Checker;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.Constants;
import fileio.InputLoader;
import org.json.simple.parser.ParseException;
import players.Children;
import players.Database;
import printer.AnnualChildren;
import printer.Printer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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
                //if( fileName.equals("14.json")) {
                    String filepath = Constants.OUTPUT_PATH + fileName;
                    File out = new File(filepath);
                    boolean isCreated = out.createNewFile();
                    if (isCreated) {
                        File testFile = new File("tests/" + file.getName());
                        action(testFile.getPath(), filepath);
                    }
               // }
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
        database.setCurrentStrategy("id");

        Operator operator = new Operator();
        operator.calculateAverageScore();
        operator.calculateAlocatedBudget();
        operator.useStrategy();
        operator.alocateGifts();
        operator.sortById();
        ArrayList<Children> toPrintChildren = new ArrayList<>();
        AnnualChildren annualChildren = new AnnualChildren();
        ArrayList<AnnualChildren> forPrint = new ArrayList<>();

        for (Children child : database.getInitialChildren()) {
            toPrintChildren.add(new Children(child));
        }
        annualChildren.setChildren(toPrintChildren);
        forPrint.add(annualChildren);
        for (int i = 0; i < database.getNumberOfYears(); i++) {
            AnnualChildren newAnnualChildren = new AnnualChildren();
            ArrayList<Children> newToPrintChildren = new ArrayList<>();
            operator.incrementRound(i);
            operator.calculateAverageScore();
            operator.calculateAlocatedBudget();
            operator.useStrategy();
            operator.alocateGifts();
            operator.sortById();
            for (Children child : database.getInitialChildren()) {
                newToPrintChildren.add(new Children(child));
            }
            newAnnualChildren.setChildren(newToPrintChildren);
            forPrint.add(newAnnualChildren);
        }

        Printer printer = new Printer();
        printer.setAnnualChildren(forPrint);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writerWithDefaultPrettyPrinter()
                .writeValue(new File(filePath2), printer);
    }
}
