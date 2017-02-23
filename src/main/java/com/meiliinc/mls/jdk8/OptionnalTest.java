package com.meiliinc.mls.jdk8;

import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 * User: jiexiu
 * Date: 16/12/23
 * Time: 下午10:40
 * Email:jiexiu@mogujie.com
 */
public class OptionnalTest {
    public static void main(String[] args) {
        String name = null;
        //Optional<Integer> optional = Optional.of(name);
        Optional<String> optional = Optional.ofNullable(name);
        if (optional.isPresent()){
            System.out.println(optional.get());
        }else {
            System.out.println("optional's values is null");
        }
        //optional = Optional.of("hello");
        System.out.println(optional.orElseGet(() -> "world"));
        //System.out.println(optional.orElse("world"));

        optional = Optional.of("hello");
        optional = optional.map(str -> str.toUpperCase());
        System.out.println(optional.get());

    }
}
