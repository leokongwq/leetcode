package com.leokongwq.algorithm.geektime.trie;

/**
 * @author : jiexiu
 * @date : 2020-06-27 15:27
 *
 * Trie 树，也叫“字典树”。顾名思义，它是一个树形结构。它是一种专门处理字符串匹配的数据结构，用来解决在一组字符串集合中快速查找某个字符串的问题。
 * Trie 树的本质，就是利用字符串之间的公共前缀，将重复的前缀合并在一起，避免重复存储一组字符串的相同前缀子串。
 * 根节点不包含任何信息。每个节点表示一个字符串中的字符
 *
 * trie 树是一个多叉树；每个节点存储一个字符和子节点的数组。 数组的下标和字符具有一一映射关系。
 *
 * 假设我们的字符串中只有从 a 到 z 这 26 个小写字母，我们在数组中下标为 0 的位置，存储指向子节点 a 的指针，下标为 1 的位置存储指向子节点 b 的指针，
 * 以此类推，下标为 25 的位置，存储的是指向的子节点 z 的指针。如果某个字符的子节点不存在，我们就在对应的下标的位置存储 null。
 *
 * 如果要在一组字符串中，频繁地查询某些字符串，用 Trie 树会非常高效。
 * 构建 Trie 树的过程，需要扫描所有的字符串，时间复杂度是 O(n)（n 表示所有字符串的长度和）。但是一旦构建成功之后，后续的查询操作会非常高效。
 *
 * 每次查询时，如果要查询的字符串长度是 k，那我们只需要比对大约 k 个节点，就能完成查询操作。跟原本那组字符串的长度和个数没有任何关系。
 * 所以说，构建好 Trie 树后，在其中查找字符串的时间复杂度是 O(k)，k 表示要查找的字符串的长度
 *
 * Trie 树尽管有可能很浪费内存，但是查询确实非常高效。
 * 为了节省子节点数组占用的内存，可以将该数组替换为：有序数组、跳表、散列表、红黑树等。
 *
 * 实际上，Trie 树的变体有很多，都可以在一定程度上解决内存消耗的问题。比如，缩点优化，就是对只有一个子节点的节点，而且此节点不是一个串的结束节点，
 * 可以将此节点与子节点合并。这样可以节省空间，但却增加了编码难度。
 *
 * Trie 树 和 散列表和红黑树比较
 *
 * 1. 字符串中包含的字符集不能太大。如果字符集太大，那存储空间可能就会浪费很多。即便可以优化，但也要付出牺牲查询、插入效率的代价
 * 2. 要求字符串的前缀重合比较多，不然空间消耗会变大很多。
 * 3. 如果要用 Trie 树解决问题，那我们就要自己从零开始实现一个 Trie 树，还要保证没有 bug，这个在工程上是将简单问题复杂化，除非必须，一般不建议这样做
 * 4. 我们知道，通过指针串起来的数据块是不连续的，而 Trie 树中用到了指针，所以，对缓存并不友好，性能上会打个折扣
 *
 * 综合这几点，针对在一组字符串中查找字符串的问题，我们在工程中，更倾向于用散列表或者红黑树。因为这两种数据结构，我们都不需要自己去实现，直接利用编程语言中提供的现成类库就行了。
 *
 * 实际上，Trie 树只是不适合精确匹配查找，精确匹配这种问题更适合用散列表或者红黑树来解决。
 * Trie 树比较适合的是查找前缀匹配的字符串，也就是类似搜索引擎关键词提示的场景
 *
 **/
public class TrieTree {

	private TrieNode root;

	public TrieTree() {
		this.root = new TrieNode('/');
	}

	public void insert(String str) {
		if (str == null || str.trim().length() == 0) {
			return;
		}
		char[] chars = str.toCharArray();

		TrieNode p = root;
		for (char ch : chars) {
			int index = ch - 'a';
			if (p.children[index] == null) {
				p.children[index] = new TrieNode(ch);
			}
			p = p.children[index];
		}
		p.isEndingChar = true;
	}

	public boolean find(String str) {
		if (str == null || str.trim().length() == 0) {
			return false;
		}
		char[] chars = str.toCharArray();

		TrieNode p = root;
		for (char ch : chars) {
			int index = ch - 'a';
			if (p.children[index] == null) {
				return false;
			} else {
				p = p.children[index];
			}
		}
		return p.isEndingChar;
	}

	class TrieNode {
		private char data;
		TrieNode[] children;
		private boolean isEndingChar;

		public TrieNode(char data) {
			this.data = data;
			this.children = new TrieNode[26];
		}
	}

	public static void main(String[] args) {
		TrieTree trieTree = new TrieTree();
		trieTree.insert("hi");
		trieTree.insert("hello");
		trieTree.insert("her");

		System.out.println(trieTree.find("h"));
		System.out.println(trieTree.find("hi"));
		System.out.println(trieTree.find("her"));
	}
}
