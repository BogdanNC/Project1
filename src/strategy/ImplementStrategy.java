package strategy;

import players.Children;

import java.util.ArrayList;

public class ImplementStrategy {
    private GiftingStretegy stretegy;

    public ImplementStrategy(GiftingStretegy stretegy) {
        this.stretegy = stretegy;
    }

    public ArrayList<Children> applyStrategy(ArrayList<Children> children) {
        return stretegy.calculatingStrategy(children);
    }
}
