package com.polite.designpattern.singleton;

/**
 * Created by polite on 8/6/2016.
 */
public class DoubleCheckSingleton {
    private static DoubleCheckSingleton instance;

    private DoubleCheckSingleton(){}

    public static DoubleCheckSingleton getInstance(){
        if (instance == null) { // check
            synchronized (DoubleCheckSingleton.class) {
                if (instance == null) { // double check
                    instance = new DoubleCheckSingleton();
                }
            }
        }

        return instance;
    }
}
