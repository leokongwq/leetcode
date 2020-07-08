package com.leokongwq.algorithm.geektime.heap;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : jiexiu
 * @date : 2020-06-21 19:15
 * 合并多个有序的小文件
 **/
public class MergeSmallSortedFiles {

	private static File mergeSortedFile(File dir) throws Exception {
		if (!dir.exists()) {
			return null;
		}
		if (!dir.isDirectory()) {
			return dir;
		}
		File[] files = dir.listFiles();
		if (files == null) {
			return null;
		}
		if (files.length <= 1) {
			return files.length == 0 ? null : files[0];
		}

		BufferedReader[] bufferedReaders = new BufferedReader[files.length];
		int n = 0;
		Map<String, StreamPair> lineMap = new HashMap<>();
		for (File file : files) {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
			String firstLine = bufferedReader.readLine();
			if (firstLine != null) {
				lineMap.put(firstLine, new StreamPair(n, bufferedReader));
			}
			bufferedReaders[n++] = bufferedReader;
		}
		//构建堆
		String[] heap = new String[files.length + 1];
		int i = 0;
		for (String line : lineMap.keySet()) {
			heap[++i] = line;
		}
		buildHeapV2(heap, files.length);

		//排序
		int k = files.length;
		File result = new File("/Users/jiexiu/a.txt");
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(result));
		while (k > 1) {
			//最小值 写入文件
			swap(heap, 1, k);
			bufferedWriter.write(heap[k] + "\n");
			StreamPair streamPair = lineMap.get(heap[k]);
			String nextLine = streamPair.nextLine();
			if (nextLine == null) {
				nextLine = readLine(bufferedReaders);
			}
			if (nextLine != null) {
				heap[k] = nextLine;
			} else {
				//文件数减少一
				--k;
			}
			heapify(heap, k, 1);
		}
		bufferedWriter.write(heap[k]+ "\n");
		bufferedWriter.close();
		return result;
	}

	private static String readLine(BufferedReader[] bufferedReaders) throws IOException {
		for (BufferedReader bufferedReader : bufferedReaders) {
			String line = bufferedReader.readLine();
			if (line != null) {
				return line;
			}
		}
		return null;
	}

	private static class StreamPair {
		private int index;
		private BufferedReader bufferedReader;

		public StreamPair(int index, BufferedReader bufferedReader) {
			this.index = index;
			this.bufferedReader = bufferedReader;
		}

		public String nextLine() {
			try {
				return bufferedReader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}
	}

	private static void buildHeapV2(String[] arr, int n) {
		// n / 2 是第最后一个非叶子节点
		for (int i = n / 2; i >= 1; --i) {
			heapify(arr, n, i);
		}
	}

	private static void heapify(String[] arr, int n, int i) {
		while (true) {
			int maxPos = i;
			if (2 * i <= n && arr[i].compareTo(arr[2 * i]) > 0) {
				maxPos = 2 * i;
			}
			if (2 * i + 1 <= n && arr[maxPos].compareTo(arr[2 * i + 1]) > 0) {
				maxPos = 2 * i + 1;
			}
			if (maxPos == i) {
				break;
			}
			swap(arr, i, maxPos);
			i = maxPos;
		}
	}

	private static void swap(String[] arr, int i, int maxPos) {
		String temp = arr[i];
		arr[i] = arr[maxPos];
		arr[maxPos] = temp;
	}

	public static void main(String[] args) throws Exception {
		File file = mergeSortedFile(new File("/Users/jiexiu/smallFiles"));
		System.out.println(file);
	}
}
