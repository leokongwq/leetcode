package com.leokongwq.geektime.ac;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;

/**
 * @author : jiexiu
 * @date : 2020-06-27 16:14
 * <p>
 * 单模式串匹配算法，是在一个模式串和一个主串之间进行匹配，也就是说，在一个主串中查找一个模式串。
 * 多模式串匹配算法，就是在多个模式串和一个主串之间做匹配，也就是说，在一个主串中查找多个模式串
 * 典型的多模式串匹配算法应用场景就是：敏感词过滤。每一个敏感词都是一个模式串，用户输入的内容就是主串
 * <p>
 * 多模式串匹配算法，只需要扫描一遍主串，就能在主串中一次性查找多个模式串是否存在，从而大大提高匹配效率
 * <p>
 * 1. 对敏感词字典进行预处理，构建成 Trie 树结构。这个预处理的操作只需要做一次，如果敏感词字典动态更新了，比如删除、添加了一个敏感词，
 * 那我们只需要动态更新一下 Trie 树就可以了
 * 2. 当用户输入一个文本内容后，我们把用户输入的内容作为主串，从第一个字符（假设是字符 C）开始，在 Trie 树中匹配。当匹配到 Trie 树的叶子节点，
 * 或者中途遇到不匹配字符的时候，我们将主串的开始匹配位置后移一位，也就是从字符 C 的下一个字符开始，重新在 Trie 树中匹配。
 * <p>
 * 基于 Trie 树的这种处理方法，有点类似单模式串匹配的 BF 算法。我们知道，单模式串匹配算法中，KMP 算法对 BF 算法进行改进，
 * 引入了 next 数组，让匹配失败时，尽可能将模式串往后多滑动几位。借鉴单模式串的优化改进方法，能否对多模式串 Trie 树进行改进，进一步提高 Trie 树的效率呢？
 * 这就要用到 AC 自动机算法了
 * <p>
 * AC 自动机算法，全称是 Aho-Corasick 算法。其实，Trie 树跟 AC 自动机之间的关系，就像单串匹配中朴素的串匹配算法，跟 KMP 算法之间的关系一样，
 * 只不过前者针对的是多模式串而已。所以，AC 自动机实际上就是在 Trie 树之上，加了类似 KMP 的 next 数组，只不过此处的 next 数组是构建在树上罢了
 * <p>
 * 所以，AC 自动机的构建，包含两个操作：
 * 1. 将多个模式串构建成 Trie 树；
 * 2. 在 Trie 树上构建失败指针（相当于 KMP 中的失效函数 next 数组）。
 * <p>
 * AC 总的匹配的时间复杂度就是 O(n*len)。因为敏感词并不会很长，而且这个时间复杂度只是一个非常宽泛的上限，实际情况下，可能近似于 O(n)
 * <p>
 * 思考题:
 * 一、单模式串匹配：
 * 1. BF： 简单场景，主串和模式串都不太长, O(m*n)
 * 2. KP：字符集范围不要太大且模式串不要太长， 否则hash值可能冲突，O(n)
 * 3. naive-BM：模式串最好不要太长（因为预处理较重），比如IDE编辑器里的查找场景； 预处理O(m*m), 匹配O(n)， 实现较复杂，需要较多额外空间.
 * 4. KMP：适合所有场景，整体实现起来也比BM简单，O(n+m)，仅需一个next数组的O(n)额外空间；但统计意义下似乎BM更快，原因不明.
 * 5. 另外查资料的时候还看到一种比BM/KMP更快，且实现+理解起来都更容易的的Sunday算法，有兴趣的可以看这里:
 * http://www.inf.fh-flensburg.de/lang/algorithmen/pattern/sundayen.htm
 * https://www.jianshu.com/p/2e6eb7386cd3
 * <p>
 * 二、多模式串匹配：
 * 1. naive-Trie: 适合多模式串公共前缀较多的匹配(O(n*k)) 或者 根据公共前缀进行查找(O(k))的场景，比如搜索框的自动补全提示.
 * 2. AC自动机: 适合大量文本中多模式串的精确匹配查找, 可以到O(n).
 *
 * https://www.cnblogs.com/sclbgw7/p/9260756.html
 **/
public class ACAutoMata {

	public class ACNode {
		private String data;
		private Map<String, ACNode> children;
		private Boolean isEndingChar;
		private Integer length;
		private ACNode fail;

		public ACNode(String data) {
			this.data = data;
			this.children = new HashMap<>();
			this.isEndingChar = false;
			this.length = 0;
			this.fail = null;
		}
	}

	private ACNode root;

	public ACAutoMata() {
		//root的fail指针为null
		this.root = new ACNode("/");
	}

	private void insert(String pattern) {
		ACNode node = this.root;
		int len = pattern.length();
		for (int i = 0; i < len; i++) {
			String c = pattern.charAt(i) + "";
			if (Objects.isNull(node.children.get(c))) {
				node.children.put(c, new ACNode(c));
			}
			node = node.children.get(c);
		}

		node.isEndingChar = true;
		node.length = pattern.length();
	}

	private void buildFailurePointer() {
		ACNode root = this.root;
		//树的层级遍历，常规操作，通过一个队列实现。
		LinkedList<ACNode> queue = new LinkedList<>();
		queue.add(root);

		while (!queue.isEmpty()) {
			ACNode p = queue.pop();

			for (ACNode pc : p.children.values()) {
				if (Objects.isNull(pc)) {
					continue;
				}
				//root的fail指针是null，null不可能有和pc匹配的字符，所以pc.fail赋值为root
				if (p == root) {
					pc.fail = root;
				} else {
					//查找q的子节点是否有和pc匹配的字符
					ACNode q = p.fail;
					while (Objects.nonNull(q)) {
						ACNode qc = q.children.get(pc.data);
						if (Objects.nonNull(qc)) {
							pc.fail = qc;
							break;
						}
						//往前查找
						q = q.fail;
					}
					//没有找到， q == null, 表示查找到root的fail指针了
					if (Objects.isNull(q)) {
						pc.fail = root;
					}
				}
				//树的层级遍历，常规操作，通过一个队列实现。
				queue.add(pc);
			}
		}
	}

	//当匹配到 Trie 树的叶子节点，或者中途遇到不匹配字符的时候，我们将主串的开始匹配位置后移一位，也就是从字符 C 的下一个字符开始，重新在 Trie 树中匹配。
	private Boolean match(String text) {
		ACNode root = this.root;
		ACNode p = root;

		int n = text.length();
		for (int i = 0; i < n; i++) {
			String c = text.charAt(i) + "";
			while (Objects.isNull(p.children.get(c)) && p != root) {
				p = p.fail;
			}

			//不匹配，从主串的第二个字符开始
			p = p.children.get(c);
			if (Objects.isNull(p)) {
				p = root;
			}

			// 监控主串的最长可匹配后缀子串是否和模式串前缀前缀匹配，不匹配的话，逐个缩短主串的可匹配后缀子串，和模式串进行比较
			ACNode tmp = p;
			while (tmp != root) {
				//匹配到叶子节点，说明模式串完全匹配了，输出匹配的结果
				if (tmp.isEndingChar) {
					System.out.println("Start from " + (i - p.length + 1));
					// return true;
				}
				// 用次长可匹配后缀和模式串进行匹配
				tmp = tmp.fail;
			}
		}

		return false;
	}

	public static boolean match(String text, String[] patterns) {
		ACAutoMata automata = new ACAutoMata();
		for (String pattern : patterns) {
			automata.insert(pattern);
		}

		automata.buildFailurePointer();
		return automata.match(text);
	}

	public static void main(String[] args) {
		System.out.println(ACAutoMata.match("abc", new String[]{"abd"}));
	}
}
