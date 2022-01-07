package players;

import java.util.ArrayList;

public abstract class Children {
    private final String lastName, firstName;
    private final String city;
    private Long age;
    private final Long id;
    private Double niceScore;
    private ArrayList<String> giftPreferences;

    /**
     * constructor
     * @param id
     * @param lastName
     * @param firstName
     * @param city
     * @param age
     * @param niceScore
     * @param giftPreferences
     */
    public Children(final Long id, final String lastName, final String firstName,
                    final String city, final Long age,
                    final Double niceScore, final ArrayList<String> giftPreferences) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.city = city;
        this.age = age;
        this.niceScore = niceScore;
        this.giftPreferences = giftPreferences;
    }

    /**
     * gets id
     * @return
     */
    public Long getId() {
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
    public Long getAge() {
        return age;
    }

    /**
     * sets age as years go by
     * @param age
     */
    public void setAge(final Long age) {
        this.age = age;
    }
    /**
     * gets nice score
     * @return
     */
    public Double getNiceScore() {
        return niceScore;
    }

    /**
     * sets nice score if needed
     * @param niceScore
     */
    public void setNiceScore(final Double niceScore) {
        this.niceScore = niceScore;
    }

    /**
     * gets gift preferances
     * @return
     */
    public ArrayList<String> getGiftPreferences() {
        return giftPreferences;
    }

    /**
     * modifies gift preferences
     * @param giftPreferences
     */
    public void setGiftPreferences(final ArrayList<String> giftPreferences) {
        this.giftPreferences = giftPreferences;
    }
}
