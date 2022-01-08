package fileio;

import annual.AnnualChanges;
import common.Constants;
import gifts.Gift;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import players.Baby;
import players.Children;
import players.ChildrenUpdates;
import players.Database;
import players.Kid;
import players.Teen;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public final class InputLoader {

    private final String inputPath;

    public InputLoader(final String inputPath) {
        this.inputPath = inputPath;
    }

    public String getInputPath() {
        return inputPath;
    }

    /**
     * Here i read all the elements given for a child and create a new
     * object of the needed type (kid, baby, children)
     * @param jsonChild
     * @param childList
     */
    private void addChildren(final Object jsonChild, final ArrayList<Children> childList) {
        Integer age = ((Long) ((JSONObject) jsonChild).get(Constants.AGE)).intValue();
        Integer id = ((Long) ((JSONObject) jsonChild).get(Constants.ID)).intValue();
        String lastName = (String) ((JSONObject) jsonChild).get(Constants.LAST_NAME);
        String firstName = (String) ((JSONObject) jsonChild).get(Constants.FIRST_NAME);
        String city = (String) ((JSONObject) jsonChild).get(Constants.CITY);
        ArrayList<Double> niceScore = new ArrayList<>();
        niceScore.add(((Long) ((JSONObject) jsonChild)
                .get(Constants.NICE_SCORE)).doubleValue());
        JSONArray preferences = (JSONArray) ((JSONObject) jsonChild)
                .get(Constants.GIFTS_PREFERENCES);
        ArrayList<String> giftPreference = new ArrayList<>();
        for (Object jsonPreference : preferences) {
            giftPreference.add(jsonPreference.toString());
        }
        if (age < Constants.FIVE) {
            childList.add(new Baby(id, lastName, firstName, city, age, niceScore, giftPreference));
        } else if (age < Constants.TWELVE) {
            childList.add(new Kid(id, lastName, firstName, city, age, niceScore, giftPreference));
        } else if (age <= Constants.EIGHTEEN) {
            childList.add(new Teen(id, lastName, firstName, city, age, niceScore, giftPreference));
        }
    }
    private void addGifts(final Object jsonGifts, final ArrayList<Gift> giftsList) {
        Double price = ((Long) ((JSONObject) jsonGifts).get(Constants.PRICE)).doubleValue();
        String category = (String) ((JSONObject) jsonGifts).get(Constants.CATEGORY);
        String productName = (String) ((JSONObject) jsonGifts).get(Constants.PRODUCT_NAME);
        giftsList.add(new Gift(productName, price, category));
    }

    /**
     * here i read the data from the files
     */
    public void readData() {
        JSONParser jsonParser = new JSONParser();
        Database database;
        database = Database.getDatabase();

        try {
            JSONObject jsonObject = (JSONObject) jsonParser
                    .parse(new FileReader(inputPath));
            Long noYears = (Long) jsonObject.get(Constants.NUMBER_OF_YEARS);
            Double sbudget = ((Long) jsonObject.get(Constants.SANTA_BUDGET)).doubleValue();
            database.setInitialBudget(sbudget);
            database.setNumberOfYears(noYears);

            ArrayList<Children> childList = new ArrayList<>();
            ArrayList<Gift> giftsList = new ArrayList<>();

            JSONObject inputdata = (JSONObject) jsonObject.get(Constants.INPUTDATA);
            JSONArray jsonChildren = (JSONArray)
                    inputdata.get(Constants.CHILDREN);
            JSONArray jsonSantaGifts = (JSONArray)
                    inputdata.get(Constants.SANTA_GIFTS_LIST);

            if (jsonChildren != null) {
                for (Object jsonChild : jsonChildren) {
                    addChildren(jsonChild, childList);
                }
                database.setInitialChildren(childList);
            } else {
                System.out.println("NU EXISTA COPII");
            }
            if (jsonSantaGifts != null) {
                for (Object jsonGifts : jsonSantaGifts) {
                    addGifts(jsonGifts, giftsList);
                }
                database.setInitialGifts(giftsList);
            } else {
                System.out.println("MOSU NU ARE CADOURI");
            }

            JSONArray yearlyChanges = (JSONArray) jsonObject.get(Constants.ANNUAL_CHANGES);
            ArrayList<AnnualChanges> annualChanges = new ArrayList<>();

            for (Object yearChange : yearlyChanges) {
                ArrayList<Children> newChidsList = new ArrayList<>();
                ArrayList<Gift> newGiftsList = new ArrayList<>();
                ArrayList<ChildrenUpdates> childUpdates = new ArrayList<>();

                Double newBugdet = ((Long) ((JSONObject) yearChange)
                        .get(Constants.NEW_SANTA_BUDGET)).doubleValue();
                JSONArray jsonNewChildren = (JSONArray) ((JSONObject) yearChange)
                        .get(Constants.NEW_CHILDREN);
                JSONArray jsonNewGifts = (JSONArray) ((JSONObject) yearChange)
                        .get(Constants.NEW_GIFTS);
                JSONArray childrenUpdates = (JSONArray) ((JSONObject) yearChange)
                        .get(Constants.CHILDREN_UPDATES);

                if (jsonNewChildren != null) {
                    for (Object jsonChild : jsonNewChildren) {
                        addChildren(jsonChild, newChidsList);
                    }
                }
                if (jsonNewGifts != null) {
                    for (Object jsonGifts :jsonNewGifts) {
                        addGifts(jsonGifts, newGiftsList);
                    }
                }
                if (childrenUpdates != null) {
                    for (Object jsonUpdate : childrenUpdates) {
                        Long id = (Long) ((JSONObject) jsonUpdate).get(Constants.ID);
                        Double niceScore;
                        if ((Long) ((JSONObject) jsonUpdate).get(Constants.NICE_SCORE) != null) {
                            niceScore = ((Long) ((JSONObject) jsonUpdate)
                                    .get(Constants.NICE_SCORE)).doubleValue();
                        } else {
                            niceScore = null;
                        }
                        JSONArray preferences = (JSONArray) ((JSONObject) jsonUpdate)
                                .get(Constants.GIFTS_PREFERENCES);
                        ArrayList<String> giftPreference = new ArrayList<>();
                        for (Object jsonPreference : preferences) {
                            giftPreference.add(jsonPreference.toString());
                        }
                        childUpdates.add(new ChildrenUpdates(id, niceScore, giftPreference));
                    }
                }
                annualChanges.add(new AnnualChanges(newBugdet, newChidsList,
                        newGiftsList, childUpdates));
            }
            database.setAnnualChanges(annualChanges);

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }
}
