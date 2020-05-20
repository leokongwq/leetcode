package com.leokongwq.geektime.recursive;

/**
 * @author jiexiu
 * created 2020/5/20 - 23:40
 */
public class FindRowNum {

    /**
     * 电影院问排数
     * @param x 表示位置行数
     * @return 返回 x 表示行数
     */
    static int rowNum(int x) {
        if (x == 1) {
            return 1;
        }
        return rowNum(x - 1) + 1;
    }

    public static void main(String[] args) {
        System.out.println(rowNum(5));
    }
}
