package visitor;

import players.Baby;
import players.Children;
import players.Kid;
import players.Teen;

public interface ChildVisitor {
    /**
     * visitor method for children
     * @param children
     */
    void visit(Children children);

    /**
     * visitor method for kid
     * @param kidChildren
     */
    void visit(Kid kidChildren);

    /**
     * visitor method for teen
     * @param teenChildren
     */
    void visit(Teen teenChildren);

    /**
     * visitor method for baby
     * @param babyChildren
     */
    void visit(Baby babyChildren);
}
