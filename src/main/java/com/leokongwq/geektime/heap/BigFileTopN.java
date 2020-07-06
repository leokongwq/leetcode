package com.leokongwq.geektime.heap;

/**
 * @author : jiexiu
 * @date : 2020-06-21 20:59
 * 求超大文件，topN
 * 思路：
 * 1. 将大文件通过hash算法分解为多个小文件。
 * 2. 求得每个文件的topN
 * 3. 将每个文件topN的结果再进行 topN计算
 * 4. 其中topN可以通过小顶堆来实现
 **/
public class BigFileTopN {

	public static void main(String[] args) {

	}
}
