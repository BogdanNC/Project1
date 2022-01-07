package players;

import java.util.ArrayList;

public class Baby extends Children {

    public Baby(final Long id, final String lastName, final String firstName,
                final String city, final Long age,
                final Double niceScore, final ArrayList<String> giftPreferences) {
        super(id, lastName, firstName, city, age, niceScore, giftPreferences);
    }
}
