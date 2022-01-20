package players;

import annual.AnnualChanges;
import gifts.Gift;

import java.util.ArrayList;

public final class Database {

    private static Database database = null;

    private Long numberOfYears;
    private Double initialBudget;
    private ArrayList<Children> initialChildren = new ArrayList<>();
    private ArrayList<Gift> initialGifts = new ArrayList<>();
    private ArrayList<AnnualChanges> annualChanges = new ArrayList<>();
    private String currentStrategy = "id";

    private Database() { }

    /**
     * singleton lazy implementation of the database
     * @return
     */
    public static Database getDatabase() {
        if (database == null) {
            database = new Database();
        }
        return database;
    }

    public Long getNumberOfYears() {
        return numberOfYears;
    }

    public void setNumberOfYears(final Long numberOfYears) {
        this.numberOfYears = numberOfYears;
    }

    public Double getInitialBudget() {
        return initialBudget;
    }

    public void setInitialBudget(final Double initialBudget) {
        this.initialBudget = initialBudget;
    }

    public ArrayList<Children> getInitialChildren() {
        return initialChildren;
    }

    public void setInitialChildren(final ArrayList<Children> initialChildren) {
        this.initialChildren = initialChildren;
    }

    public ArrayList<Gift> getInitialGifts() {
        return initialGifts;
    }

    public void setInitialGifts(final ArrayList<Gift> initialGifts) {
        this.initialGifts = initialGifts;
    }

    public ArrayList<AnnualChanges> getAnnualChanges() {
        return annualChanges;
    }

    public void setAnnualChanges(final ArrayList<AnnualChanges> annualChanges) {
        this.annualChanges = annualChanges;
    }

    public String getCurrentStrategy() {
        return currentStrategy;
    }

    public void setCurrentStrategy(String currentStrategy) {
        this.currentStrategy = currentStrategy;
    }
}
