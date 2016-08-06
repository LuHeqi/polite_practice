package com.polite.designpattern.singleton;

/**
 * Created by polite on 8/6/2016.
 */
public class Singleton {

    private static Singleton ourInstance = new Singleton();

    private Singleton() {
    }

    public static Singleton getInstance() {
        return ourInstance;
    }


}
