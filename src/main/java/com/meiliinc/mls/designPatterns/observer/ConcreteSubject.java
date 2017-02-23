package com.meiliinc.mls.designPatterns.observer;

import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: jiexiu
 * Date: 16/11/25
 * Time: 上午10:58
 * Email:jiexiu@mogujie.com
 */
public class ConcreteSubject implements Subject {

    private boolean changed;

    private List<Observer> observers;

    public ConcreteSubject(){
        this.observers = new LinkedList<Observer>();
    }

    @Override
    public boolean addObserver(Observer observer) {
        return observers.add(observer);
    }

    @Override
    public boolean removeObserver(Observer observer) {
        return observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers){
            observer.update(null);
        }
        changed = false;
    }

    public void setChange(){
        changed = true;
        this.notifyObservers();
    }
}
