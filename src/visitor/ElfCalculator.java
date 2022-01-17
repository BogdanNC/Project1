package visitor;

import common.Constants;
import players.Baby;
import players.Children;
import players.Kid;
import players.Teen;

public class ElfCalculator implements ElfVisitor {

    /**
     * its's a visitor for children
     * @param children
     */
    @Override
    public void elfVisit(final Children children) {
        Double budget = children.getAssignedBudget();
        String elfType = children.getElf();
        if (elfType.equals("black")) {
            budget = budget - budget
                    * Constants.MAXIMUM_ERROR_CHECKSTYLE / Constants.ONE_HUNDRED;
            children.setAssignedBudget(budget);
        }
        if (elfType.equals("pink")) {
            budget = budget + budget
                    * Constants.MAXIMUM_ERROR_CHECKSTYLE / Constants.ONE_HUNDRED;
            children.setAssignedBudget(budget);
        }
    }

    /**
     * its's a visitor for kid
     * @param kidChildren
     */
    @Override
    public void elfVisit(final Kid kidChildren) {
        Double budget = kidChildren.getAssignedBudget();
        String elfType = kidChildren.getElf();
        if (elfType.equals("black")) {
            budget = budget - budget
                    * Constants.MAXIMUM_ERROR_CHECKSTYLE / Constants.ONE_HUNDRED;
            kidChildren.setAssignedBudget(budget);
        }
        if (elfType.equals("pink")) {
            budget = budget + budget
                    * Constants.MAXIMUM_ERROR_CHECKSTYLE / Constants.ONE_HUNDRED;
            kidChildren.setAssignedBudget(budget);
        }
    }

    /**
     * its's a visitor for teen
     * @param teenChildren
     */
    @Override
    public void elfVisit(final Teen teenChildren) {
        Double budget = teenChildren.getAssignedBudget();
        String elfType = teenChildren.getElf();
        if (elfType.equals("black")) {
            budget = budget - budget
                    * Constants.MAXIMUM_ERROR_CHECKSTYLE / Constants.ONE_HUNDRED;
            teenChildren.setAssignedBudget(budget);
        }
        if (elfType.equals("pink")) {
            budget = budget + budget
                    * Constants.MAXIMUM_ERROR_CHECKSTYLE / Constants.ONE_HUNDRED;
            teenChildren.setAssignedBudget(budget);
        }
    }

    /**
     * its's a visitor for baby
     * @param babyChildren
     */
    @Override
    public void elfVisit(final Baby babyChildren) {
        Double budget = babyChildren.getAssignedBudget();
        String elfType = babyChildren.getElf();
        if (elfType.equals("black")) {
            budget = budget - budget
                    * Constants.MAXIMUM_ERROR_CHECKSTYLE / Constants.ONE_HUNDRED;
            babyChildren.setAssignedBudget(budget);
        }
        if (elfType.equals("pink")) {
            budget = budget + budget
                    * Constants.MAXIMUM_ERROR_CHECKSTYLE / Constants.ONE_HUNDRED;
            babyChildren.setAssignedBudget(budget);
        }
    }
}
