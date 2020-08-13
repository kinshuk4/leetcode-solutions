package com.vaani.leetcode.design;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/iterator-for-combination/
 * 1286. Iterator for Combination
 * Medium
 * <p>
 * Design an Iterator class, which has:
 * <p>
 * A constructor that takes a string characters of sorted distinct lowercase English letters and a number combinationLength as arguments.
 * A function next() that returns the next combination of length combinationLength in lexicographical order.
 * A function hasNext() that returns True if and only if there exists a next combination.
 * <p>
 * <p>
 * <p>
 * Example:
 * <p>
 * CombinationIterator iterator = new CombinationIterator("abc", 2); // creates the iterator.
 * <p>
 * iterator.next(); // returns "ab"
 * iterator.hasNext(); // returns true
 * iterator.next(); // returns "ac"
 * iterator.hasNext(); // returns true
 * iterator.next(); // returns "bc"
 * iterator.hasNext(); // returns false
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= combinationLength <= characters.length <= 15
 * There will be at most 10^4 function calls per test.
 * It's guaranteed that all calls of the function next are valid.
 */
public class IteratorForCombination {
	public static void main(String[] args) {
		UsingPQ.CombinationIterator iterator = new UsingPQ.CombinationIterator("chp", 1); // creates the iterator.
		System.out.println(iterator.next()); // returns "ab"
		System.out.println(iterator.hasNext()); // returns true
		System.out.println(iterator.next()); // returns "ac"
		System.out.println(iterator.hasNext()); // returns true
		System.out.println(iterator.next()); // returns "bc"
		System.out.println(iterator.hasNext()); // returns false
	}

	static class UsingPQ {
		static class CombinationIterator {
			PriorityQueue<String> minPQ = new PriorityQueue<>();

			public CombinationIterator(String s, int k) {
				generateSub(s, k, 0, new StringBuilder());
			}

			private void generateSub(String s, int len, int start, StringBuilder result) {
				if (len == 0) {
					minPQ.add(result.toString());
					return;
				}
				for (int i = start; i <= s.length() - len; i++) {
					result.append(s.charAt(i));
					generateSub(s, len - 1, i + 1, result);
					result.deleteCharAt(result.length() - 1);
				}
			}

			public String next() {
				return minPQ.poll();
			}

			public boolean hasNext() {
				return !minPQ.isEmpty();
			}
		}
	}

	static class UsingList {
		static class CombinationIterator {

			private List<Character> list = new LinkedList<>();
			private int len;
			private String cur;
			private String last;

			public CombinationIterator(String characters, int combinationLength) {
				for (int i = 0; i < characters.length(); i++) {
					this.list.add(characters.charAt(i));
				}

				this.len = combinationLength;
				this.last = "";
				for (int i = characters.length() - 1; i >= characters.length() - len; i--) {
					this.last = list.get(i) + last;
				}
			}

			public String next() {
				if (!hasNext()) {
					return null;
				}
				if (cur == null) {
					cur = "";
					for (int i = 0; i < len; i++) {
						cur += list.get(i);
					}
					return cur;
				}
				int p = list.size() - 1;
				for (int i = len - 1; i >= 0; i--) {
					int index = list.indexOf(cur.charAt(i));
					if (index == p) {
						p--;
						continue;
					} else {
						// abcdef: abcf -> abde
						cur = cur.substring(0, i);
						for (int j = i; j < len; j++) {
							cur += list.get(index + 1);
							index++;
						}
						return cur;
					}
				}
				return null;
			}

			public boolean hasNext() {
				if (cur != null && cur.equals(last))
					return false;
				return true;
			}
		}
	}


}
