package com.meiliinc.mls.designPatterns.observer;

/**
 * Created with IntelliJ IDEA.
 * User: jiexiu
 * Date: 16/11/25
 * Time: 上午10:55
 * Email:jiexiu@mogujie.com
 */
public interface Subject {

    /**
     * 添加观察者
     * @param observer
     * @return
     */
    public boolean addObserver(Observer observer);

    /**
     * 删除指定的观察者
     * @param observer
     * @return
     */
    public boolean removeObserver(Observer observer);

    /**
     * 通知所有的观察者
     */
    public void notifyObservers();
}
