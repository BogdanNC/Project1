package players;

import visitor.ChildVisitor;

import java.util.ArrayList;

public final class Kid extends Children {

    public Kid(final Integer id, final String lastName, final String firstName,
               final String city, final Integer age,
               final ArrayList<Double> niceScore, final ArrayList<String> giftPreferences) {
        super(id, lastName, firstName, city, age, niceScore, giftPreferences);
    }
    @Override
    public void accept(final ChildVisitor v) {
        v.visit(this);
    }
}
