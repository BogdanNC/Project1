package runtime;

import annual.Operator;
import com.fasterxml.jackson.databind.ObjectMapper;
import fileio.InputLoader;
import players.Children;
import players.Database;
import printer.AnnualChildren;
import printer.Printer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public final class Runtime {
    /**
     * for checking style
     */
    private Runtime() {
    }
    /**
     * here i read from the file, increment rounds and send them
     * to be printed
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
        operator.yellowElfMagic();
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
            operator.yellowElfMagic();
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
