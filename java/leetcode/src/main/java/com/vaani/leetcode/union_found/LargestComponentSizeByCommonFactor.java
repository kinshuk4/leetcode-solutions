package com.vaani.leetcode.union_found;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/largest-component-size-by-common-factor/
 * 952. Largest Component Size by Common Factor
 * Hard
 * <p>
 * 323
 * <p>
 * 52
 * <p>
 * Add to List
 * <p>
 * Share
 * Given a non-empty array of unique positive integers A, consider the following graph:
 * <p>
 * There are A.length nodes, labelled A[0] to A[A.length - 1];
 * There is an edge between A[i] and A[j] if and only if A[i] and A[j] share a common factor greater than 1.
 * Return the size of the largest connected component in the graph.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: [4,6,15,35]
 * Output: 4
 * <p>
 * Example 2:
 * <p>
 * Input: [20,50,9,63]
 * Output: 2
 * <p>
 * Example 3:
 * <p>
 * Input: [2,3,6,7,4,12,21,39]
 * Output: 8
 * <p>
 * Note:
 * <p>
 * 1 <= A.length <= 20000
 * 1 <= A[i] <= 100000
 */
public class LargestComponentSizeByCommonFactor {
    public static void main(String[] args) {
        int[] a = {2, 3, 6, 7, 4, 12, 21, 39};
        LargestComponentSizeByCommonFactor underTest = new LargestComponentSizeByCommonFactor();
        System.out.println(underTest.largestComponentSize(a));
    }

    static class WeightedDisjointSetWithMaxComponentSize {
        int[] array;
        int[] size;
        int max;

        public WeightedDisjointSetWithMaxComponentSize(int n) {
            array = new int[n];
            size = new int[n];
            max = 1;
            for (int i = 0; i < n; i++) {
                array[i] = i;
                size[i] = 1;
            }
        }

        public int find(int i) {
            return getRoot(i);
        }

        //Get root is nothing but find...just adding a new method to show we are getting root.
        private int getRoot(int i) {
            //chase parent of current element until it reaches root
            while (array[i] != i) {
                i = array[i];
            }
            return i;
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                array[rootX] = rootY;
                size[rootY] += size[rootX];
                max = Math.max(max, size[rootY]);
            }
        }
    }


    public int largestComponentSize(int[] A) {
        int n = A.length;
        Map<Integer, Integer> map = new HashMap<>();// Factor to Node index map
        WeightedDisjointSetWithMaxComponentSize uf = new WeightedDisjointSetWithMaxComponentSize(n);

        for (int i = 0; i < n; i++) {
            int num = A[i];
            for (int j = 2; j * j <= num; j++) {
                if (num % j == 0) {
                    if (!map.containsKey(j)) {//this means that no index has claimed the factor yet
                        map.put(j, i);
                    } else {//this means that one index already claimed, so union that one with current
                        uf.union(i, map.get(j));
                    }
                    if (!map.containsKey(num / j)) {
                        map.put(num / j, i);
                    } else {
                        uf.union(i, map.get(num / j));
                    }
                }
            }
            if (!map.containsKey(num)) {//a could be factor too. Don't miss this
                map.put(num, i);
            } else {
                uf.union(i, map.get(num));
            }
        }
        return uf.max;

    }
}
