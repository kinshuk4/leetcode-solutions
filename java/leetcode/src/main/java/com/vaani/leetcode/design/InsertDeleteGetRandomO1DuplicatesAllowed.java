package com.vaani.leetcode.design;
/**
 * https://leetcode.com/problems/insert-delete-getrandom-o1-duplicates-allowed/
 * 381. Insert Delete GetRandom O(1) - Duplicates allowed
 * Hard
 * Design a data structure that supports all following operations in average O(1) time.
 *
 * <p>Note: Duplicate elements are allowed. insert(val): Inserts an item val to the collection.
 * remove(val): Removes an item val from the collection if present. getRandom: Returns a random
 * element from current collection of elements. The probability of each element being returned is
 * linearly related to the number of same value the collection contains. Example:
 *
 * <p>// Init an empty collection. RandomizedCollection collection = new RandomizedCollection();
 *
 * <p>// Inserts 1 to the collection. Returns true as the collection did not contain 1.
 * collection.insert(1);
 *
 * <p>// Inserts another 1 to the collection. Returns false as the collection contained 1.
 * Collection now contains [1,1]. collection.insert(1);
 *
 * <p>// Inserts 2 to the collection, returns true. Collection now contains [1,1,2].
 * collection.insert(2);
 *
 * <p>// getRandom should return 1 with the probability 2/3, and returns 2 with the probability 1/3.
 * collection.getRandom();
 *
 * <p>// Removes 1 from the collection, returns true. Collection now contains [1,2].
 * collection.remove(1);
 *
 * <p>// getRandom should return 1 and 2 both equally likely. collection.getRandom();
 */

import java.util.*;

public class InsertDeleteGetRandomO1DuplicatesAllowed {

	/**
	 * Your RandomizedCollection object will be instantiated and called as such:
	 * RandomizedCollection obj = new RandomizedCollection();
	 * boolean param_1 = obj.insert(val);
	 * boolean param_2 = obj.remove(val);
	 * int param_3 = obj.getRandom();
	 */

	public static void main(String[] args) throws Exception {
		RandomizedCollectionUsingMapAndList collection = new RandomizedCollectionUsingMapAndList();
		System.out.println(collection.insert(1));
		System.out.println(collection.insert(1));
		System.out.println(collection.insert(2));
		System.out.println(collection.getRandom());
		System.out.println(collection.remove(2));
		System.out.println(collection.getRandom());
		System.out.println(collection.remove(1));
		System.out.println(collection.getRandom());
		System.out.println(collection.remove(1));
	}

	/*
	 * <p>Solution O(1) for each operation. Maintain a hashmap of value -> {set of indices}; Set of
	 * indices are indices of array containing the value. Insert: Insert a element in end of array and
	 * add the index of array as the set of values in hashmap. Remove: If the hashmap contains value
	 * remove a random element from the set and replace the element at that index with the last element
	 * from array and remove the last element from the array. Since we are removing the last element
	 * from array this operation requires only O(1) time getRandom(): Generate a random number between 0
	 * and size of array and return the element at that position.
	 */
	static class RandomizedCollectionUsingMapAndList {

		private final Map<Integer, Set<Integer>> valueMap;
		private final List<Integer> list;
		private final Random random;

		/**
		 * Initialize your data structure here.
		 */
		public RandomizedCollectionUsingMapAndList() {
			valueMap = new HashMap<>();
			list = new ArrayList<>();
			random = new Random();
		}

		/**
		 * Inserts a value to the collection. Returns true if the collection did not already contain the
		 * specified element.
		 */
		public boolean insert(int val) {
			boolean status = valueMap.containsKey(val);
			valueMap.putIfAbsent(val, new HashSet<>());
			Set<Integer> set = valueMap.get(val);
			list.add(val);
			set.add(list.size() - 1);
			return !status;
		}

		/**
		 * Removes a value from the collection. Returns true if the collection contained the specified
		 * element.
		 */
		public boolean remove(int val) {
			if (valueMap.containsKey(val)) {
				Set<Integer> set = valueMap.get(val);
				int valIndex = set.iterator().next();
				set.remove(valIndex);
				if (set.isEmpty()) {
					valueMap.remove(val);
				}
				if (valIndex == list.size() - 1) { // if this is the last index then simply remove it
					list.remove(list.size() - 1);
				} else {
					int lastEle = list.get(list.size() - 1);
					valueMap.get(lastEle).remove(list.size() - 1);
					valueMap.get(lastEle).add(valIndex);
					list.set(valIndex, lastEle);
					list.remove(list.size() - 1);
				}
				return true;
			} else return false;
		}

		/**
		 * Get a random element from the collection.
		 */
		public int getRandom() {
			return list.get(random.nextInt(list.size()));
		}

	}

	/*
	 *
	 * <p>Solution O(1) for each operation. Maintain a hashmap of value -> {set of indices}; Set of
	 * indices are indices of array containing the value. Insert: Insert a element in end of array and
	 * add the index of array as the set of values in hashmap. Remove: If the hashmap contains value
	 * remove a random element from the set and replace the element at that index with the last element
	 * from array and remove the last element from the array. Since we are removing the last element
	 * from array this operation requires only O(1) time getRandom(): Generate a random number between 0
	 * and size of array and return the element at that position.

	 */
	static class RandomizedCollectionUsingTwoMaps {

		private final Map<Integer, Set<Integer>> valueMap;
		private final Map<Integer, Integer> indexMap;
		private final Random random;

		/**
		 * Initialize your data structure here.
		 */
		public RandomizedCollectionUsingTwoMaps() {
			valueMap = new HashMap<>();
			indexMap = new HashMap<>();
			random = new Random();
		}

		/**
		 * Inserts a value to the collection. Returns true if the collection did not already contain the
		 * specified element.
		 */
		public boolean insert(int val) {
			int size2 = indexMap.size();
			indexMap.put(size2 + 1, val);

			if (valueMap.containsKey(val)) {
				valueMap.get(val).add(size2 + 1);
				return false;
			} else {
				HashSet<Integer> set = new HashSet<>();
				set.add(size2 + 1);
				valueMap.put(val, set);
				return true;
			}
		}

		/**
		 * Removes a value from the collection. Returns true if the collection contained the specified
		 * element.
		 */
		public boolean remove(int val) {
			if (valueMap.containsKey(val)) {
				Set<Integer> set = valueMap.get(val);
				int removeIdx = set.iterator().next();


				//remove from set of map1
				set.remove(removeIdx);

				if (set.size() == 0) {
					valueMap.remove(val);
				}

				if (removeIdx == indexMap.size()) {
					indexMap.remove(removeIdx);
					return true;
				}

				int size2 = indexMap.size();
				int key = indexMap.get(size2);

				Set<Integer> setChange = valueMap.get(key);
				setChange.remove(size2);
				setChange.add(removeIdx);


				indexMap.remove(size2);
				indexMap.remove(removeIdx);

				indexMap.put(removeIdx, key);

				return true;
			}

			return false;
		}

		/**
		 * Get a random element from the collection.
		 */
		public int getRandom() {
			if (valueMap.size() == 0)
				return -1;

			if (indexMap.size() == 1) {
				return indexMap.get(1);
			}

			return indexMap.get(random.nextInt(indexMap.size()) + 1); // nextInt() returns a random number in [0, n).
		}
	}


}
