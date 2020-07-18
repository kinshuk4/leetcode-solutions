package com.vaani.leetcode.design;

import java.util.*;

/** https://leetcode.com/problems/insert-delete-getrandom-o1-duplicates-allowed/
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
 *
 * <p>Solution O(1) for each operation. Maintain a hashmap of value -> {set of indices}; Set of
 * indices are indices of array containing the value. Insert: Insert a element in end of array and
 * add the index of array as the set of values in hashmap. Remove: If the hashmap contains value
 * remove a random element from the set and replace the element at that index with the last element
 * from array and remove the last element from the array. Since we are removing the last element
 * from array this operation requires only O(1) time getRandom(): Generate a random number between 0
 * and size of array and return the element at that position.
 */
public class RandomizedCollectionUsingMapAndList {

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

    /**
     * Main method
     *
     * @param args
     * @throws Exception
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
}
