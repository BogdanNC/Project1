package visitor;

import players.Baby;
import players.Children;
import players.Kid;
import players.Teen;

public interface ChildVisitor {
    public void visit(Children children);
    public void visit(Kid kidChildren);
    public void visit(Teen teenChildren);
    public void visit(Baby babyChildren);
}
