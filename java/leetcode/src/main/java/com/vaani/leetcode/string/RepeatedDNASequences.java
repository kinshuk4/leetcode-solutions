package com.vaani.leetcode.string;

import java.util.*;

/**
 * https://leetcode.com/problems/repeated-dna-sequences/
 * All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG".
 * When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.
 * Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.
 * For example,
 * Given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",
 * Return: ["AAAAACCCCC", "CCCCCAAAAA"].
 */
// basically return 10 characters substring which occur more than onces
public class RepeatedDNASequences {
	public List<String> findRepeatedDnaSequences(String s) {
		List<String> result = new LinkedList<>();
		if (s.length() < 10) {
			return result;
		}
		HashSet<Long> seen = new HashSet<>();
		HashSet<Long> repeatedSeen = new HashSet<>();


		for (int i = 0; i <= s.length() - 10; i++) {
			String currString = s.substring(i, i + 10);
			long value = findHashNumber(currString);
			if (repeatedSeen.contains(value)) {
				continue;
			}
			if (seen.contains(value)) {
				repeatedSeen.add(value);
				result.add(currString);
			} else {
				seen.add(value);
			}
		}
		return result;
	}

	public long findHashNumber(String s) {
		long res = s.charAt(0);
		long p = 5;
		for (int i = 1; i < s.length(); i++) {
			res += s.charAt(i) * p;
			p *= 5;
		}
		return res;
	}

	// using hashmap - slightly faster
	public List<String> findRepeatedDnaSequences2(String s) {
		List<String> result = new LinkedList<>();
		if (s.length() < 10) {
			return result;
		}

		// stores string and frequency
		Map<String, Integer> seen = new HashMap<>();


		for (int i = 0; i <= s.length() - 10; i++) {
			String currString = s.substring(i, i + 10);
			seen.put(currString, seen.getOrDefault(currString, 0) + 1);

			if(seen.get(currString) == 2){
				result.add(currString);
			}
		}
		return result;
	}
}
