package strategy;

import players.Children;

import java.util.ArrayList;
import java.util.Comparator;

public class IdStrategy implements GiftingStretegy{

    @Override
    public ArrayList<Children> calculatingStrategy(ArrayList<Children> childList) {
        childList.sort(Comparator.comparing(Children :: getId));
        return childList;
    }
}
