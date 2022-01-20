package annual;

import common.Constants;
import gifts.Gift;
import players.Children;
import players.ChildrenUpdates;
import players.Database;
import players.Kid;
import players.Teen;
import strategy.CityNiceScore;
import strategy.IdStrategy;
import strategy.ImplementStrategy;
import strategy.NiceScore;
import visitor.ElfCalculator;
import visitor.SantasCalculator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public final class Operator {
    /**
     * calculates the average score of a child
     */
    public void calculateAverageScore() {
        Database database = Database.getDatabase();
        SantasCalculator santaVisit = new SantasCalculator();
        for (Children child: database.getInitialChildren()) {
            child.accept(santaVisit);
        }
    }

    /**
     * calculates alocated budget of a child
     */
    public void calculateAlocatedBudget() {
        Double budgetUnit, averageScoreSum = 0.0;
        Database database = Database.getDatabase();
        for (Children child: database.getInitialChildren()) {
            averageScoreSum = averageScoreSum + child.getAverageScore();
        }
        ElfCalculator elfVisitor = new ElfCalculator();
        budgetUnit = database.getInitialBudget() / averageScoreSum;
        for (Children child: database.getInitialChildren()) {
            child.setAssignedBudget(child.getAverageScore() * budgetUnit);
            child.acceptElf(elfVisitor);
        }
    }

    /**
     * sorts by id the childrens
     */
    public void sortById() {
        Database database = Database.getDatabase();
        ArrayList<Children> childList;
        childList = database.getInitialChildren();
        childList.sort(Comparator.comparing(Children :: getId));
        database.setInitialChildren(childList);
    }
    /**
     * calculates gifts
     */
    public void alocateGifts() {
        Double budget;
        int ok;
        Database database = Database.getDatabase();
        for (Children child: database.getInitialChildren()) { //i get the kids from the database
            budget = 0.0;
            //i create the recived gifts list for the current children
            ArrayList<Gift> alocatedGifts = new ArrayList<>();
            for (String preference: child.getGiftsPreferences()) {
                // i check if the child didn't spent all his bugdet
                if (budget < child.getAssignedBudget()) {
                    ok = 0;
                    Gift aux = null;
                    for (Gift gift: database.getInitialGifts()) { // i parse santa's gift list
                        //check to see if there is a prefered gift
                        if (gift.getCategory().equals(preference)) {
                            if (gift.getQuantity() > 0) {   //check if the gift is still avalabile
                                if (ok == 0) {
                                    aux = gift; // memorize the first gift
                                    ok = 1;
                                } else {    //if i find more
                                    if (aux.getPrice() > gift.getPrice()) {
                                        aux = gift; //memorize the cheapest gift
                                    }
                                }
                            }
                        }
                    }
                    if (aux != null) { //if a gift is selected
                        if (budget + aux.getPrice() <= child.getAssignedBudget()) {
                            //as aux is a reference to the database, i modify the quantity directly
                            aux.setQuantity(aux.getQuantity() - 1);
                            alocatedGifts.add(aux); //i add it in the recived gift list
                            budget = budget + aux.getPrice(); // i modify the budget
                        }
                    }
                }
            }
            child.setReceivedGifts(alocatedGifts);
        }
    }

    /**
     * uploads the budget form the database
     * @param noYear
     */
    public void realocateBudget(final int noYear) {
        Database database = Database.getDatabase(); //i get the database reference
        AnnualChanges annualChange;
        // gets's the update from a specific year
        annualChange = database.getAnnualChanges().get(noYear);
        database.setInitialBudget(annualChange.getNewBudget()); // updates the budget
    }

    /**
     * increses all children ages with 1
     */
    public void incrementChildrensAge() {
        Database database = Database.getDatabase(); //i get the database instance
        ArrayList<Children> childrenList = database.getInitialChildren();
        ArrayList<Children> updatedChildrenList = new ArrayList<>(); //i create a new list
        for (Children child: childrenList) {

            child.setAge(child.getAge() + 1); // i add 1 to all the children's age
            // if they get to five they pass form baby to kid
            if (child.getAge().intValue() == Constants.FIVE) {
                updatedChildrenList.add(new Kid(child.getId(), child.getLastName(),
                        child.getFirstName(), child.getCity(),
                        child.getAge(), child.getNiceScoreHistory(),
                        child.getGiftsPreferences(), child.getNiceScoreBonus(), child.getElf()));
            } else if (child.getAge().intValue() == Constants.TWELVE) { // at 12 from kid to teen
                updatedChildrenList.add(new Teen(child.getId(), child.getLastName(),
                        child.getFirstName(), child.getCity(),
                        child.getAge(), child.getNiceScoreHistory(),
                        child.getGiftsPreferences(), child.getNiceScoreBonus(), child.getElf()));
            } else if (child.getAge().intValue() > Constants.EIGHTEEN) {
                continue; // and at over 18 i delete them from the list
                //nothing to see here
            } else {
                updatedChildrenList.add(child);
                // they are still sorted by id as this age incrementation
                // does not affect the order of the kids
            }
        }
        // i put them back in the database
        database.setInitialChildren(updatedChildrenList);
    }

    /**
     * adds new gifts in the database
     * @param noYear
     */
    public void addGifts(final int noYear) {
        Database database = Database.getDatabase();
        AnnualChanges annualChange;
        ArrayList<Gift> oldGifts = database.getInitialGifts();
        annualChange = database.getAnnualChanges().get(noYear);
        for (Gift gift: annualChange.getNewGifts()) {
            oldGifts.add(gift);
        }
        database.setInitialGifts(oldGifts);
    }

    /**
     * adds all the new children in the database
     * @param noYear
     */
    public void addNewChildren(final int noYear) {
        Database database = Database.getDatabase();
        AnnualChanges annualChange;
        annualChange = database.getAnnualChanges().get(noYear);
        ArrayList<Children> childList = database.getInitialChildren();
        for (Children child: annualChange.getNewChildren()) {
            childList.add(child);
        }
        database.setInitialChildren(childList);
    }

    /**
     * updates the childrens
     * @param noYear
     */
    public void updateChildrens(final int noYear) {
        Database database = Database.getDatabase();
        AnnualChanges annualChange;
        annualChange = database.getAnnualChanges().get(noYear);
        for (ChildrenUpdates update: annualChange.getNewChildUpdates()) {
            for (Children child : database.getInitialChildren()) {
                if (child.getId() == update.getId()) {
                    ArrayList<String> newPreferences = new ArrayList<>();
                    if (update.getNiceScore() != null) {
                        child.getNiceScoreHistory().add(update.getNiceScore());
                    }
                    for (String updatePref: update.getGiftPreferences()) {
                        newPreferences.add(updatePref);
                    }
                    newPreferences.addAll(child.getGiftsPreferences());
                    newPreferences = (ArrayList<String>) newPreferences.stream()
                            .distinct().collect(Collectors.toList());
                    child.setGiftsPreferences(newPreferences);
                    child.setElf(update.getElf());
                }
            }
        }
    }

    /**
     * here i update the strategy in the database
     * year by year
     * @param noYear
     */
    public void setNewStrategy(final int noYear) {
        Database database = Database.getDatabase();
        AnnualChanges annualChange;
        annualChange = database.getAnnualChanges().get(noYear);
        database.setCurrentStrategy(annualChange.getStrategy());
    }

    /**
     * i use Strategy pattern for distributing gifts
     */
    public void useStrategy() {
        Database database = Database.getDatabase();
        ArrayList<Children> children = database.getInitialChildren();
        ArrayList<Children> childrenWithGifts;
        String strategy = database.getCurrentStrategy();
        ImplementStrategy implement;
        switch (strategy) {
            case "id":
                implement = new ImplementStrategy(new IdStrategy());
                childrenWithGifts = implement.applyStrategy(children);
                break;
            case "niceScore" :
                implement = new ImplementStrategy(new NiceScore());
                childrenWithGifts = implement.applyStrategy(children);
                break;
            case "niceScoreCity" :
                implement = new ImplementStrategy(new CityNiceScore());
                childrenWithGifts = implement.applyStrategy(children);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + strategy);
        }
        database.setInitialChildren(childrenWithGifts);
    }

    /**
     * in this function i return a list containging just one
     * gift, or a empty list. The element is the cheapest form the
     * selected preference
     * @param preference
     * @return
     */
    public ArrayList<Gift> giveGift(final String preference) {
        Database database = Database.getDatabase();
        ArrayList<Gift> alocatedGifts = new ArrayList<>();
        Gift aux = null;
        int ok = 0;
        for (Gift gift: database.getInitialGifts()) { // i parse santa's gift list
            if (gift.getCategory().equals(preference)) { //check to see if there is a prefered gift
                if (ok == 0) {
                    aux = gift; // memorize the first gift
                    ok = 1;
                } else {    //if i find more
                    if (aux.getPrice() > gift.getPrice()) {
                        aux = gift; //memorize the cheapest gift
                    }
                }
            }
        }
        if (aux != null) { //if a gift is selected
            if (aux.getQuantity() > 0) { //if a gift is still avalable
                aux.setQuantity(aux.getQuantity() - 1); //same as before
                alocatedGifts.add(aux); //i add it in the recived gift list
            }
        }
        return alocatedGifts;
    }

    /**
     * i implement the yellow elf functionality
     */
    public void yellowElfMagic() {
        Database database = Database.getDatabase();
        ArrayList<Children> childrenList = database.getInitialChildren();
        for (Children child : childrenList) {
            if (child.getElf().equals("yellow")) {
                if (child.getReceivedGifts().isEmpty()) {
                    String preference;
                    preference = child.getGiftsPreferences().get(0);
                    child.setReceivedGifts(giveGift(preference));
                }
            }
        }
    }
    /**
     * adds all the data from specific year to the database
     * @param noYear
     */
    public void incrementRound(final int noYear) {

        realocateBudget(noYear);
        setNewStrategy(noYear);
        incrementChildrensAge();
        addGifts(noYear);
        addNewChildren(noYear);
        updateChildrens(noYear);
    }
}
