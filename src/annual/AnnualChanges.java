package annual;

import gifts.Gift;
import players.Children;
import players.ChildrenUpdates;

import java.util.ArrayList;

public final class AnnualChanges {
    private Double newBudget;
    private ArrayList<Children> newChildren;
    private ArrayList<Gift> newGifts;
    private ArrayList<ChildrenUpdates> newChildUpdates;

    public AnnualChanges(final Double newBudget, final ArrayList<Children> newChildren,
                         final ArrayList<Gift> newGifts,
                         final ArrayList<ChildrenUpdates> newChildUpdates) {
        this.newBudget = newBudget;
        this.newChildren = newChildren;
        this.newGifts = newGifts;
        this.newChildUpdates = newChildUpdates;
    }

    public Double getNewBudget() {
        return newBudget;
    }

    public void setNewBudget(final Double newBudget) {
        this.newBudget = newBudget;
    }

    public ArrayList<Children> getNewChildren() {
        return newChildren;
    }

    public void setNewChildren(final ArrayList<Children> newChildren) {
        this.newChildren = newChildren;
    }

    public ArrayList<Gift> getNewGifts() {
        return newGifts;
    }

    public void setNewGifts(final ArrayList<Gift> newGifts) {
        this.newGifts = newGifts;
    }

    public ArrayList<ChildrenUpdates> getNewChildUpdates() {
        return newChildUpdates;
    }

    public void setNewChildUpdates(final ArrayList<ChildrenUpdates> newChildUpdates) {
        this.newChildUpdates = newChildUpdates;
    }
}
