package com.polite.designpattern.singleton;

/**
 * Created by polite on 8/6/2016.
 */
public class UnThreadSafeSingleton {
    private static UnThreadSafeSingleton instance;

    private UnThreadSafeSingleton(){}

    public UnThreadSafeSingleton getSingleton(){
        if (instance == null) {
            instance = new UnThreadSafeSingleton();
        }
        return  instance;
    }
}
