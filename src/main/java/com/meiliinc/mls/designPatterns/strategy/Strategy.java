package com.meiliinc.mls.designPatterns.strategy;

/**
 * Created with IntelliJ IDEA.
 * User: jiexiu
 * Date: 16/11/23
 * Time: 上午11:26
 * Email:jiexiu@mogujie.com
 */

/**
 * 抽象的策略类,进行策略定义
 */
public interface Strategy {
    /**
     * 执行策略的方法
     */
    public void doStrategy();
}

/**
 * 具体的策略类,策略的实现
 */
class ConcreteStrategy implements Strategy {

    /**
     * 执行策略的方法
     */
    @Override
    public void doStrategy(){

    }
}

/**
 * 策略使用上下文
 */
class Context {

    /**
     * 上下文中使用的策略声明(必须是抽象的策略, 方便运行时替换)
     */
    private Strategy strategy;

    public void play(){
        strategy.doStrategy();
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }
}
