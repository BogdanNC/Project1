package annual;

import common.Constants;
import gifts.Gift;
import players.*;
import visitor.SantasCalculator;

import java.util.ArrayList;
import java.util.stream.Collectors;

public final class Operator {
    public void calculateAverageScore() {
        Database database = Database.getDatabase();
        SantasCalculator elfVisit = new SantasCalculator();
        for (Children child: database.getInitialChildren()) {
            child.accept(elfVisit);
        }
    }
    public void calculateAlocatedBudget() {
        Double budgetUnit, averageScoreSum = 0.0;
        Database database = Database.getDatabase();
        for (Children child: database.getInitialChildren()) {
            averageScoreSum = averageScoreSum + child.getAverageScore();
        }
        budgetUnit = database.getInitialBudget() / averageScoreSum;
        for (Children child: database.getInitialChildren()) {
            child.setAssignedBudget(child.getAverageScore() * budgetUnit);
        }
    }
    public void alocateGifts() {
        Double budget;
        int ok;
        Database database = Database.getDatabase();
        for (Children child: database.getInitialChildren()) {
            budget = 0.0;
            ArrayList<Gift> alocatedGifts = new ArrayList<>();
            for (String preference: child.getGiftsPreferences()) {
                if (budget < child.getAssignedBudget()) {
                    ok = 0;
                    Gift aux = null;
                    for (Gift gift: database.getInitialGifts()) {
                        if (gift.getCategory().equals(preference)) {
                            if (ok == 0) {
                                aux = gift; // iau primul cadou din categorie daca gasesc
                                ok = 1;
                            } else {
                                if (aux.getPrice() > gift.getPrice()){
                                    aux = gift; //iau cel mai ieftin cadou din categorie
                                }
                            }
                        }
                    }
                    if (aux != null){
                        if (budget + aux.getPrice() <= child.getAssignedBudget()) {
                            alocatedGifts.add(aux);
                            budget = budget + aux.getPrice();
                        }
                    }
                }
            }
            child.setReceivedGifts(alocatedGifts);
        }
    }
    public void realocateBudget(int noYear) {
        Database database = Database.getDatabase();
        AnnualChanges annualChange;
        annualChange = database.getAnnualChanges().get(noYear);
        database.setInitialBudget(annualChange.getNewBudget());
    }
    public void incrementChildrensAge() {
        Database database = Database.getDatabase();
        ArrayList<Children> childrenList = database.getInitialChildren();
        ArrayList<Children> updatedChildrenList = new ArrayList<>();
        for (Children child: childrenList) {

            child.setAge(child.getAge() + 1);

            if (child.getAge().intValue() == Constants.FIVE) {
                updatedChildrenList.add(new Kid(child.getId(), child.getLastName(), child.getFirstName(),
                        child.getCity(), child.getAge(), child.getNiceScoreHistory(),
                        child.getGiftsPreferences()));
            } else if (child.getAge().intValue() == Constants.TWELVE) {
                updatedChildrenList.add(new Teen(child.getId(), child.getLastName(), child.getFirstName(),
                        child.getCity(), child.getAge(), child.getNiceScoreHistory(),
                        child.getGiftsPreferences()));
            } else if (child.getAge().intValue() > Constants.EIGHTEEN) {
                //nothing to see here
            } else {
                updatedChildrenList.add(child);
            }
        }
        database.setInitialChildren(updatedChildrenList);
    }
    public void addGifts(int noYear){
        Database database = Database.getDatabase();
        AnnualChanges annualChange;
        ArrayList<Gift> oldGifts = database.getInitialGifts();
        annualChange = database.getAnnualChanges().get(noYear);
        for (Gift gift: annualChange.getNewGifts()) {
            oldGifts.add(gift);
        }
        database.setInitialGifts(oldGifts);
    }
    public void addNewChildren(int noYear) {
        Database database = Database.getDatabase();
        AnnualChanges annualChange;
        annualChange = database.getAnnualChanges().get(noYear);
        ArrayList<Children> childList = database.getInitialChildren();
        for (Children child: annualChange.getNewChildren()){
            childList.add(child);
        }
        database.setInitialChildren(childList);
    }
    public void updateChildrens(int noYear) {
        int ok, index;
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
                }
            }
        }
    }
    public void incrementRound(int noYear){
        realocateBudget(noYear);
        incrementChildrensAge();
        addGifts(noYear);
        addNewChildren(noYear);
        updateChildrens(noYear);
    }

}
