package players;

import gifts.Gift;
import visitor.ChildVisitor;

import java.util.ArrayList;

public class Children {
    private final Integer id;
    private final String lastName, firstName;
    private final String city;
    private Integer age;
    private ArrayList<String> giftsPreferences;
    private Double averageScore;
    private ArrayList<Double> niceScoreHistory;
    private Double assignedBudget;
    private ArrayList<Gift> receivedGifts;

    /**
     * this is a copy constructor
     * @param child
     */
    public Children(final Children child) {
        this.id = child.id;
        this.lastName = child.lastName;
        this.firstName = child.firstName;
        this.city = child.city;
        this.age = child.age;
        this.assignedBudget = child.assignedBudget;
        this.averageScore = child.averageScore;
        this.giftsPreferences = new ArrayList<>(child.giftsPreferences);
        this.niceScoreHistory = new ArrayList<>(child.niceScoreHistory);
        this.receivedGifts = new ArrayList<>(child.receivedGifts);
    }
    /**
     * constructor
     * @param id
     * @param lastName
     * @param firstName
     * @param city
     * @param age
     * @param niceScoreHistory
     * @param giftsPreferences
     */
    public Children(final Integer id, final String lastName, final String firstName,
                    final String city, final Integer age,
                    final ArrayList<Double> niceScoreHistory,
                    final ArrayList<String> giftsPreferences) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.city = city;
        this.age = age;
        this.niceScoreHistory = niceScoreHistory;
        this.giftsPreferences = giftsPreferences;
        this.averageScore = 0.0;
        this.assignedBudget = 0.0;
    }

    /**
     * gets id
     * @return
     */
    public Integer getId() {
        return id;
    }

    /**
     * gets last name
     * @return
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * gets first name
     * @return
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * gets city
     * @return
     */
    public String getCity() {
        return city;
    }

    /**
     * gets age
     * @return
     */
    public Integer getAge() {
        return age;
    }

    /**
     * sets age as years go by
     * @param age
     */
    public void setAge(final Integer age) {
        this.age = age;
    }
    /**
     * gets nice score
     * @return
     */
    public ArrayList<Double> getNiceScoreHistory() {
        return niceScoreHistory;
    }

    /**
     * sets nice score if needed
     * @param niceScoreHistory
     */
    public void setNiceScoreHistory(final ArrayList<Double> niceScoreHistory) {
        this.niceScoreHistory = niceScoreHistory;
    }

    /**
     * gets gift preferances
     * @return
     */
    public ArrayList<String> getGiftsPreferences() {
        return giftsPreferences;
    }

    /**
     * modifies gift preferences
     * @param giftsPreferences
     */
    public void setGiftsPreferences(final ArrayList<String> giftsPreferences) {
        this.giftsPreferences = giftsPreferences;
    }

    /**
     * i used visitor pattern to calculate individual scores separately
     * and to have clean clear classes
     * @param v
     */
    public void accept(final ChildVisitor v) {
        v.visit(this);
    }

    /**
     * it's a getter
     * @return
     */
    public Double getAverageScore() {
        return averageScore;
    }

    /**
     * it's a setter
     * @param averageScore
     */
    public void setAverageScore(final Double averageScore) {
        this.averageScore = averageScore;
    }

    /**
     * it's a getter
     * @return
     */
    public Double getAssignedBudget() {
        return assignedBudget;
    }

    /**
     * look! a setter
     * @param assignedBudget
     */
    public void setAssignedBudget(final Double assignedBudget) {
        this.assignedBudget = assignedBudget;
    }

    /**
     * you won't guess it
     * @return
     */
    public ArrayList<Gift> getReceivedGifts() {
        return receivedGifts;
    }

    /**
     * I want the checkstyle points !!
     * @param receivedGifts
     */
    public void setReceivedGifts(final ArrayList<Gift> receivedGifts) {
        this.receivedGifts = receivedGifts;
    }
}
