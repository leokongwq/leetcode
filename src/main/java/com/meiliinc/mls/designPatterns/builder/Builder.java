package com.meiliinc.mls.designPatterns.builder;

/**
 * Created with IntelliJ IDEA.
 * User: jiexiu
 * Date: 16/12/5
 * Time: 下午5:51
 * Email:jiexiu@mogujie.com
 */
public interface Builder {

    public void buildPart1(String part1);

    public void buildPart2(String part2);

    public Product getResult();
}
