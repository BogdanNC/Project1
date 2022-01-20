package strategy;

import players.Children;

import java.util.ArrayList;

public final class NiceScore implements GiftingStretegy {
    @Override
    public ArrayList<Children> calculatingStrategy(final ArrayList<Children> childList) {
        childList.sort((o1, o2) -> {
            if (o1.getAverageScore().equals(o2.getAverageScore())) {
                return o1.getId().compareTo(o2.getId());
            } else {
                return o2.getAverageScore().compareTo(o1.getAverageScore());
            }
        });
        return childList;
    }
}
