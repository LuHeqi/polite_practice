package com.polite.designpattern.singleton;

import java.io.Serializable;

/**
 * ???? need to edit ??
 * Created by polite on 8/6/2016.
 */
public class SerializableSingleton implements Serializable {

    private static final long serialVersionUID = 1L;
    private static SerializableSingleton instance = new SerializableSingleton();

    private SerializableSingleton() {

    }

    public static SerializableSingleton getInstance(){
        return instance;
    }


    private Object readResolve(){
        return instance;
    }

}
