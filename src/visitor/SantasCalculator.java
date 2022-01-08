package visitor;

import players.Baby;
import players.Children;
import players.Kid;
import players.Teen;

import java.util.ArrayList;

public class SantasCalculator implements ChildVisitor {
    @Override
    public void visit(Children children) {
        //nothing to see here
    }

    @Override
    public void visit(Kid kidChildren) {
        ArrayList<Double> niceScore = kidChildren.getNiceScoreHistory();
        Double sum = 0.0;
        for (Double elem: niceScore) {
            sum = sum + elem;
        }
        sum = sum / niceScore.size();
        kidChildren.setAverageScore(sum);
    }

    @Override
    public void visit(Teen teenChildren) {
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

    @Override
    public void visit(Baby babyChildren) {
        babyChildren.setAverageScore(10.0);
    }
}
