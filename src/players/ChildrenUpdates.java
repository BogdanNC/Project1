package players;

import java.util.ArrayList;

public final class ChildrenUpdates {
    private Long id;
    private Double niceScore;
    private ArrayList<String> giftPreferences;

    public ChildrenUpdates(final long id, final Double niceScore,
                           final ArrayList<String> giftPreferences) {
        this.id = id;
        this.niceScore = niceScore;
        this.giftPreferences = giftPreferences;
    }

    public long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Double getNiceScore() {
        return niceScore;
    }

    public void setNiceScore(final Double niceScore) {
        this.niceScore = niceScore;
    }

    public ArrayList<String> getGiftPreferences() {
        return giftPreferences;
    }

    public void setGiftPreferences(final ArrayList<String> giftPreferences) {
        this.giftPreferences = giftPreferences;
    }
}
