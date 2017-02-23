package com.meiliinc.mls.jdk8;

import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: jiexiu
 * Date: 16/12/23
 * Time: 下午2:36
 * Email:jiexiu@mogujie.com
 */
public class LamdaTest {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3);
        list.forEach(num -> System.out.println(num));

        Arrays.asList(4,5,6).forEach((Integer num) -> {
            System.out.println("num:" + num);
        });

        String separator = ",";
        Arrays.asList( "a", "b", "d" ).forEach((String e ) -> { System.out.print( e + separator ); } );

        Arrays.asList( "a", "b", "d" ).sort((a, b) -> a.compareTo(b));
    }
}
