package strategy;

import players.Children;

import java.util.ArrayList;

public interface GiftingStretegy {
    /**
     * this is a inteface that is implemented for the
     * 3 ways in which the gifts can be distributed
     * @param childList
     * @return
     */
    ArrayList<Children> calculatingStrategy(ArrayList<Children> childList);
}
