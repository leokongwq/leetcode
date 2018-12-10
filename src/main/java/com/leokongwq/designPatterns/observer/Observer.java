package com.leokongwq.designPatterns.observer;

/**
 * Created with IntelliJ IDEA.
 * User: jiexiu
 * Date: 16/11/25
 * Time: 上午10:55
 * Email:leokongwq@gmail.com
 */
public interface Observer {

    /**
     * 接受通知的方法
     * @param event
     */
    public void update(Object event);
}
