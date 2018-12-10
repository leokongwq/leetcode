package com.leokongwq.designPatterns.builder;

/**
 * Created with IntelliJ IDEA.
 * User: jiexiu
 * Date: 16/12/5
 * Time: 下午5:56
 * Email:leokongwq@gmail.com
 */
public class Director {

    private Builder builder;

    public Director(Builder builder){
        this.builder = builder;
    }

    public Product construct(){
        builder.buildPart1("I am part1");
        builder.buildPart2("I am part2");
        return builder.getResult();
    }

    public static void main(String[] args) {
        Director director = new Director(new ConcreteBuilder());
        System.out.println(director.construct());
    }
}
