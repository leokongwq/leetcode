package com.leokongwq.algorithm.geektime.algothinking.backtrace;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author : jiexiu
 * @date : 2020-07-02 14:45
 **/
public class FullPermuteString {

	public static List<String> permute(String str) {
		if (str == null || str.trim().length() == 1) {
			List<String> item = new ArrayList<>(1);
			item.add(str);
			return item;
		}
		List<String> result = new LinkedList<>();
		premute(0, str.toCharArray(), new StringBuilder(), result);
		return result;
	}

	private static void premute(int i, char[] chars, StringBuilder subStr, List<String> result) {
		if (i == chars.length) {
			result.add(subStr.toString());
			return;
		}
		//尝试每一个字符
		for (int j = 0; j < chars.length; j++) {
			if (subStr.indexOf(String.valueOf(chars[j])) == -1) {
				subStr.append(chars[j]);
				premute(i + 1, chars, subStr, result);
				subStr.deleteCharAt(subStr.length() - 1);
			}
		}
	}

	public static void main(String[] args) {
		List<String> result = FullPermuteString.permute("abc");
		result.forEach(System.out::println);
	}

}
