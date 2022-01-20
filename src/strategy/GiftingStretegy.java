package strategy;

import players.Children;

import java.util.ArrayList;

public interface GiftingStretegy {
    ArrayList<Children> calculatingStrategy(ArrayList<Children> childList);
}
