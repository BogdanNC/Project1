package players;

import visitor.ChildVisitor;

import java.util.ArrayList;

public class Teen extends Children {

    public Teen(final Long id, final String lastName, final String firstName,
                final String city, final Long age,
                final ArrayList<Double> niceScore, final ArrayList<String> giftPreferences) {
        super(id, lastName, firstName, city, age, niceScore, giftPreferences);
    }
    @Override
    public void accept(ChildVisitor v) {
        v.visit(this);
    }
}
