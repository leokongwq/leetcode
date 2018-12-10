package com.leokongwq.designPatterns.builder;

/**
 * Created with IntelliJ IDEA.
 * User: jiexiu
 * Date: 16/12/5
 * Time: 下午5:55
 * Email:leokongwq@gmail.com
 */
public class ConcreteBuilder implements Builder {

    private Product product = new Product();

    @Override
    public void buildPart1(String part1) {
        product.setPart1(part1);
    }

    @Override
    public void buildPart2(String part2) {
        product.setPart2(part2);
    }

    @Override
    public Product getResult() {
        return product;
    }
}
