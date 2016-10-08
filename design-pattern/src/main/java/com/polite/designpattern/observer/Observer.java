package com.polite.designpattern.observer;

/**
 * @author polite
 * @date 2016-10-08 .
 */
public interface Observer<S extends Observable<S,O,A>,
                          O extends Observer<S,O,A>,
                          A > {
    void update(S s,A a);
}
