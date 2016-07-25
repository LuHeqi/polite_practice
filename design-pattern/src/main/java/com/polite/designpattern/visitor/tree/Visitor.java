package com.polite.designpattern.visitor.tree;

public interface Visitor<E,R>{
    //  R means Return
        R leaf(E e);
        R branch(R left,R right);
    }