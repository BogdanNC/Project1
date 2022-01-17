package players;

import visitor.ChildVisitor;
import visitor.ElfVisitor;

import java.util.ArrayList;

public final class Baby extends Children {

    public Baby(final Integer id, final String lastName, final String firstName,
                final String city, final Integer age,
                final ArrayList<Double> niceScore, final ArrayList<String> giftPreferences,
                final Double niceScoreBonus, final String elf) {
        super(id, lastName, firstName, city, age, niceScore, giftPreferences, niceScoreBonus, elf);
    }
    @Override
    public void accept(final ChildVisitor v) {
        v.visit(this);
    }
    @Override
    public void acceptElf(final ElfVisitor v) {
        v.elfVisit(this);
    }
}
