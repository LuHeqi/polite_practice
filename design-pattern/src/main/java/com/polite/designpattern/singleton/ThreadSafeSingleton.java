package com.polite.designpattern.singleton;

/**
 * Created by polite on 8/6/2016.
 */
public class ThreadSafeSingleton {
    private static ThreadSafeSingleton instance;

    private ThreadSafeSingleton(){}

    public static synchronized  ThreadSafeSingleton getInstance(){
        if (instance == null) {
            instance = new ThreadSafeSingleton();
        }
        return instance;
    }
}
