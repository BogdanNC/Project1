package visitor;

import common.Constants;
import players.Baby;
import players.Children;
import players.Kid;
import players.Teen;

import java.util.ArrayList;

public class SantasCalculator implements ChildVisitor {
    @Override
    /**
     * i never get this case
     */
    public void visit(final Children children) {
        //nothing to see here
    }

    /**
     * calculates average score for kid
     * @param kidChildren
     */
    @Override
    public void visit(final Kid kidChildren) {
        ArrayList<Double> niceScore = kidChildren.getNiceScoreHistory();
        Double sum = 0.0;
        for (Double elem: niceScore) {
            sum = sum + elem;
        }
        sum = sum / niceScore.size();
        kidChildren.setAverageScore(sum);
    }

    /**
     * calculates average score for teen
     * @param teenChildren
     */
    @Override
    public void visit(final Teen teenChildren) {
        ArrayList<Double> niceScore = teenChildren.getNiceScoreHistory();
        Double sum = 0.0, index = 1.0, totalOfN = 0.0;
        for (Double elem: niceScore) {
            sum = sum + (elem * index);
            totalOfN = totalOfN + index;
            index = index + 1.0;
        }
        sum = sum / totalOfN;
        teenChildren.setAverageScore(sum);
    }

    /**
     * calculates average score for baby
     * @param babyChildren
     */
    @Override
    public void visit(final Baby babyChildren) {
        babyChildren.setAverageScore(Constants.ANGELS_NOTE);
    }
}
