package players;

import java.util.ArrayList;

public final class ChildrenUpdates {
    private Long id;
    private Double niceScore;
    private ArrayList<String> giftPreferences;
    private String elf;

    public ChildrenUpdates(final long id, final Double niceScore,
                           final ArrayList<String> giftPreferences,
                           final String elf) {
        this.id = id;
        this.niceScore = niceScore;
        this.giftPreferences = giftPreferences;
        this.elf = elf;
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

    public String getElf() {
        return elf;
    }
}
