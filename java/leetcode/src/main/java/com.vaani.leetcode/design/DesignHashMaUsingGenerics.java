package com.vaani.leetcode.design;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DesignHashMaUsingGenerics {
    static class Pair<U, V> {
        public U first;
        public V second;

        public Pair(U first, V second) {
            this.first = first;
            this.second = second;
        }
    }


    static class Bucket {
        private List<Pair<Integer, Integer>> bucket;

        public Bucket() {
            this.bucket = new LinkedList<>();
        }

        public Integer get(Integer key) {
            for (Pair<Integer, Integer> pair : this.bucket) {
                if (pair.first.equals(key))
                    return pair.second;
            }
            return -1;
        }

        public void update(Integer key, Integer value) {
            boolean found = false;
            for (Pair<Integer, Integer> pair : this.bucket) {
                if (pair.first.equals(key)) {
                    pair.second = value;
                    found = true;
                }
            }
            if (!found)
                this.bucket.add(new Pair<Integer, Integer>(key, value));
        }

        public void remove(Integer key) {
            for (Pair<Integer, Integer> pair : this.bucket) {
                if (pair.first.equals(key)) {
                    this.bucket.remove(pair);
                    break;
                }
            }
        }
    }

    static class MyHashMap {
        private int keySpace;
        private List<Bucket> hashTable;

        /** Initialize your data structure here. */
        public MyHashMap() {
            this.keySpace = 2069;
            this.hashTable = new ArrayList<>();
            for (int i = 0; i < this.keySpace; ++i) {
                this.hashTable.add(new Bucket());
            }
        }

        /** value will always be non-negative. */
        public void put(int key, int value) {
            int hashKey = key % this.keySpace;
            this.hashTable.get(hashKey).update(key, value);
        }

        /**
         * Returns the value to which the specified key is mapped, or -1 if this map contains no mapping
         * for the key
         */
        public int get(int key) {
            int hash_key = key % this.keySpace;
            return this.hashTable.get(hash_key).get(key);
        }

        /** Removes the mapping of the specified value key if this map contains a mapping for the key */
        public void remove(int key) {
            int hash_key = key % this.keySpace;
            this.hashTable.get(hash_key).remove(key);
        }
    }
}
