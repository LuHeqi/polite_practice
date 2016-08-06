package com.polite.designpattern.singleton;

/**
 * initialization on demand holder
 * Created by polite on 8/6/2016.
 */
public class LazyLoadSingleton {

    private LazyLoadSingleton() {

    }

    public static LazyLoadSingleton getInstance(){
        return Holder.instance;
    }
    private static class Holder{
        private static LazyLoadSingleton instance = new LazyLoadSingleton();

    }

}
