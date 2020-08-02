package com.vaani.leetcode.design;
/**
 * https://leetcode.com/problems/insert-delete-getrandom-o1/
 * 380. Insert Delete GetRandom O(1)
 * Medium
 * <p>
 * Design a data structure that supports all following operations in average O(1) time.
 * <p>
 * <p>
 * <p>
 * insert(val): Inserts an item val to the set if not already present.
 * remove(val): Removes an item val from the set if present.
 * getRandom: Returns a random element from current set of elements (it's guaranteed that at least one element exists when this method is called). Each element must have the same probability of being returned.
 * <p>
 * <p>
 * <p>
 * Example:
 * <p>
 * // Init an empty set.
 * RandomizedSet randomSet = new RandomizedSet();
 * <p>
 * // Inserts 1 to the set. Returns true as 1 was inserted successfully.
 * randomSet.insert(1);
 * <p>
 * // Returns false as 2 does not exist in the set.
 * randomSet.remove(2);
 * <p>
 * // Inserts 2 to the set, returns true. Set now contains [1,2].
 * randomSet.insert(2);
 * <p>
 * // getRandom should return either 1 or 2 randomly.
 * randomSet.getRandom();
 * <p>
 * // Removes 1 from the set, returns true. Set now contains [2].
 * randomSet.remove(1);
 * <p>
 * // 2 was already in the set, so return false.
 * randomSet.insert(2);
 * <p>
 * // Since 2 is the only number in the set, getRandom always return 2.
 * randomSet.getRandom();
 */

import java.util.*;

public class InsertDeleteGetRandomO1 {

	/**
	 * Your RandomizedSet object will be instantiated and called as such:
	 * RandomizedSet obj = new RandomizedSet(); boolean param_1 =
	 * obj.insert(val); boolean param_2 = obj.remove(val); int param_3 =
	 * obj.getRandom();
	 */

	public static void main(String[] args) throws Exception {
		RandomizedSetUsingMapAndList rSet = new RandomizedSetUsingMapAndList();
		System.out.println(rSet.getRandom());
		System.out.println(rSet.insert(1));
		System.out.println(rSet.insert(2));
		System.out.println(rSet.insert(2));
		System.out.println(rSet.insert(3));
		System.out.println(rSet.remove(2));
		System.out.println(rSet.insert(2));
		System.out.println(rSet.getRandom());
		System.out.println(rSet.insert(234));
		System.out.println(rSet.insert(23));
		System.out.println(rSet.insert(22));
		System.out.println(rSet.getRandom());
		System.out.println(rSet.remove(245));
		System.out.println(rSet.remove(234));
		System.out.println(rSet.getRandom());
	}

	static class RandomizedSetUsingMapAndList {
		private Map<Integer, Integer> map;
		private List<Integer> list;
		private Random random;

		/**
		 * Initialize your data structure here.
		 */
		public RandomizedSetUsingMapAndList() {
			map = new HashMap<>();
			list = new ArrayList<>();
			random = new Random();
		}

		/**
		 * Inserts a value to the set. Returns true if the set did not already contain the specified
		 * element.
		 */
		public boolean insert(int val) {
			if (!map.containsKey(val)) {
				int pos = list.size();
				map.put(val, pos);
				list.add(val);
				return true;
			}
			return false;
		}

		/**
		 * Removes a value from the set. Returns true if the set contained the specified element.
		 */
		public boolean remove(int val) {
			if (map.containsKey(val)) {
				int size = list.size();
				int idxVal = map.get(val);
				if (idxVal < (size - 1)) {
					int last = list.get(size - 1);
					map.put(last, idxVal);
					list.set(idxVal, last);
				}
				map.remove(val);
				list.remove(size - 1);
				return true;
			}
			return false;
		}

		/**
		 * Get a random element from the set.
		 */
		public int getRandom() {
    /*if(list.size() == 0) return 0;
    else if (list.size() == 1) return list.get(0);*/
			return list.get(random.nextInt(list.size() - 1));
		}
	}

	// submitted
	static class RandomizedSetUsingTwoMaps {
		private Map<Integer, Integer> valueMap;
		private Map<Integer, Integer> idxMap;
		private Random random;

		/**
		 * Initialize your data structure here.
		 */
		public RandomizedSetUsingTwoMaps() {
			valueMap = new HashMap<>();
			idxMap = new HashMap<>();
			random = new Random();
		}


		/**
		 * Inserts a value to the set. Returns true if the set did not already contain the specified
		 * element.
		 */
		public boolean insert(int val) {
			if (valueMap.containsKey(val)) {
				return false;
			}

			valueMap.put(val, valueMap.size());
			idxMap.put(idxMap.size(), val);

			return true;
		}

		/**
		 * Removes a value from the set. Returns true if the set contained the specified element.
		 */
		public boolean remove(int val) {
			if (valueMap.containsKey(val)) {
				int idx = valueMap.get(val);
				valueMap.remove(val);
				idxMap.remove(idx);

				// Remove the last element from the list - otherwise there will be a
				// collission in the indexMap as we already have value of size-1
				// in the map
				Integer tailElem = idxMap.get(idxMap.size());
				if (tailElem != null) {
					idxMap.put(idx, tailElem);
					valueMap.put(tailElem, idx);
				}

				return true;
			}

			return false;
		}

		/**
		 * Get a random element from the set.
		 */
		public int getRandom() {
    /*if(list.size() == 0) return 0;
    else if (list.size() == 1) return list.get(0);*/
			if (valueMap.size() == 0) {
				return -1;
			}

			if (valueMap.size() == 1) {
				return idxMap.get(0);
			}

			int idx = random.nextInt(valueMap.size());

			return idxMap.get(idx);
		}
	}


}
