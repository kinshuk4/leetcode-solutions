package com.vaani.leetcode.string;

import java.util.Set;

import static com.vaani.dsa.ds.utils.generic.ArrayUtils.swap;

/**
 * https://leetcode.com/problems/reverse-vowels-of-a-string/
 * Write a function that takes a string as input and reverse only the vowels of a string.
 * <p>
 * Example 1:
 * <p>
 * Input: "hello"
 * Output: "holle"
 * <p>
 * Example 2:
 * <p>
 * Input: "leetcode"
 * Output: "leotcede"
 * <p>
 * Note:
 * The vowels does not include the letter "y".
 */
public class ReverseVowelsOfString {

	public static void main(String[] args) {
		System.out.println(reverseVowels(" "));
	}

	@SuppressWarnings("Duplicates")
	public static String reverseVowels1(String s) {
		if (s == null) {
			return "";
		}

		Set<Character> vowels = Set.of('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U');
		char[] chars = s.toCharArray();
		// two pointers
		int i = 0, j = s.length() - 1;
		while (i <= j) {
			while (i < s.length() - 1 && !vowels.contains(chars[i])) {
				i++;
			}
			while (j >= 0 && !vowels.contains(chars[i])) {
				j--;
			}

			// checking for case like hlll
			if (i < s.length() && j >= 0) {
				swap(chars, i++, j--);
			}
		}
		return new String(chars);
	}


	// more cleaner
	@SuppressWarnings("Duplicates")
	public static String reverseVowels(String s) {
		if (s == null) {
			return "";
		}

		Set<Character> vowels = Set.of('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U');
		char[] chars = s.toCharArray();
		// two pointers
		int i = 0, j = s.length() - 1;
		while (i < j) {
			while (i < j && !vowels.contains(chars[i])) {
				i++;
			}
			while (i < j && !vowels.contains(chars[j])) {
				j--;
			}
			swap(chars, i++, j--);

		}
		return new String(chars);
	}


}
