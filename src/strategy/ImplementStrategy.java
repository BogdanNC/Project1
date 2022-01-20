package strategy;

import players.Children;

import java.util.ArrayList;

public final class ImplementStrategy {
    private GiftingStretegy stretegy;

    public ImplementStrategy(final GiftingStretegy stretegy) {
        this.stretegy = stretegy;
    }

    /**
     * this is the function that clarifies at runtime which
     * strategy to implement
     * @param children
     * @return
     */
    public ArrayList<Children> applyStrategy(final ArrayList<Children> children) {
        return stretegy.calculatingStrategy(children);
    }
}
