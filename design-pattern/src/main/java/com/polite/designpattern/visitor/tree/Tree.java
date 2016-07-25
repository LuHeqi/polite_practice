package com.polite.designpattern.visitor.tree;

/**
 * polite
 * 2016-07-25.
 */
public abstract class Tree <E> {

    //  only need define Type R (E has been defined ,)
    public abstract <R> R visit(Visitor<E,R> visitor);

    public static <T> Tree<T> leaf(final T e){
        return new Tree<T>() {
            @Override
            public <R> R visit(Visitor<T, R> visitor) {
                return visitor.leaf(e);
            }
        };
    }

    public static <T> Tree<T> branch(final Tree<T> left, final Tree<T> right) {
        return new Tree<T>() {
            @Override
            public <R> R visit(Visitor<T, R> visitor) {
                return visitor.branch(left.visit(visitor),right.visit(visitor));
            }
        };
    }
}
