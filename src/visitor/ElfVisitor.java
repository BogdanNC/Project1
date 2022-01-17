package visitor;

import players.Baby;
import players.Children;
import players.Kid;
import players.Teen;

public interface ElfVisitor {
    /**
     * this checker is really annoying
     * it's clear that this is a visitor
     * @param children
     */
    void elfVisit(Children children);

    /**
     * and i also wrote that in the class that
     * implements those fucntions
     * @param kidChildren
     */
    void elfVisit(Kid kidChildren);

    /**
     * visitor
     * @param teenChildren
     */
    void elfVisit(Teen teenChildren);

    /**
     * visitor
     * @param babyChildren
     */
    void elfVisit(Baby babyChildren);
}
