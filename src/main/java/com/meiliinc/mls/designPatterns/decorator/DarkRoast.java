package com.meiliinc.mls.designPatterns.decorator;

/**
 * Created with IntelliJ IDEA.
 * User: jiexiu
 * Date: 16/11/25
 * Time: 下午10:31
 * Email:jiexiu@mogujie.com
 */
public class DarkRoast implements Beverage {

    @Override
    public double cost() {
        return 1.5;
    }
}
