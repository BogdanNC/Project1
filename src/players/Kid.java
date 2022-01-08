package players;

import visitor.ChildVisitor;

import java.util.ArrayList;

public class Kid extends Children {

    public Kid(final Long id, final String lastName, final String firstName,
               final String city, final Long age,
               final ArrayList<Double> niceScore, final ArrayList<String> giftPreferences) {
        super(id, lastName, firstName, city, age, niceScore, giftPreferences);
    }
    @Override
    public void accept(ChildVisitor v) {
        v.visit(this);
    }
}
