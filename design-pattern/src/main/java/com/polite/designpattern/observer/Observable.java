package com.polite.designpattern.observer;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**Observable (Subject)
 * @author polite
 * @date 2016-10-08 .
 */
public class Observable<S extends Observable<S,O,A>,
                        O extends Observer<S,O,A>,
                        A >{
    private List<O> obs;
    private boolean changed = false;

    public Observable() {
        obs = new CopyOnWriteArrayList<>();
    }

    /**
     * Adds an observer to the set of observers for this object, provided
     * that it is not the same as some observer already in the set.
     * @param o
     */
    public  void addObserver(O o) {
        if (o == null)
            throw new NullPointerException();
        if (!obs.contains(o)) {
            obs.add(o);
        }
    }

    /**
     * Deletes an observer from the set of observers of this object.
     * @param o
     */
    public void deleteObserver(O o){
        obs.remove(o);
    }

    /**
     * Deletes all observer from the set of observers .
     *
     */
    public  void deleteObservers() {
        obs.clear();
    }

    public void notifyObservers(){
        notifyObservers(null);
    }

    public void notifyObservers(A a){
        Object[] arrLocal;
        synchronized (this){
            if (!changed)
                return;
            arrLocal = obs.toArray();
            clearChanged();
        }
        for (int i = arrLocal.length-1; i>=0; i--){
            ((Observer)arrLocal[i]).update(this, a);
        }

    }



    protected synchronized void setChanged() {
        changed = true;
    }

    protected synchronized void clearChanged() {
        changed = false;
    }

    public synchronized boolean hasChanged() {
        return changed;
    }

    public synchronized int countObservers() {
        return obs.size();
    }
}
