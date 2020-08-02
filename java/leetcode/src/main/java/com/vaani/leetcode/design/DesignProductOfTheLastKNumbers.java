package com.vaani.leetcode.design;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/product-of-the-last-k-numbers/
 * 1352. Product of the Last K Numbers
 * Medium
 * <p>
 * Implement the class ProductOfNumbers that supports two methods:
 * <p>
 * 1. add(int num)
 * <p>
 * Adds the number num to the back of the current list of numbers.
 * <p>
 * 2. getProduct(int k)
 * <p>
 * Returns the product of the last k numbers in the current list.
 * You can assume that always the current list has at least k numbers.
 * <p>
 * At any time, the product of any contiguous sequence of numbers will fit into a single 32-bit integer without overflowing.
 * <p>
 * <p>
 * <p>
 * Example:
 * <p>
 * Input
 * ["ProductOfNumbers","add","add","add","add","add","getProduct","getProduct","getProduct","add","getProduct"]
 * [[],[3],[0],[2],[5],[4],[2],[3],[4],[8],[2]]
 * <p>
 * Output
 * [null,null,null,null,null,null,20,40,0,null,32]
 * <p>
 * Explanation
 * ProductOfNumbers productOfNumbers = new ProductOfNumbers();
 * productOfNumbers.add(3);        // [3]
 * productOfNumbers.add(0);        // [3,0]
 * productOfNumbers.add(2);        // [3,0,2]
 * productOfNumbers.add(5);        // [3,0,2,5]
 * productOfNumbers.add(4);        // [3,0,2,5,4]
 * productOfNumbers.getProduct(2); // return 20. The product of the last 2 numbers is 5 * 4 = 20
 * productOfNumbers.getProduct(3); // return 40. The product of the last 3 numbers is 2 * 5 * 4 = 40
 * productOfNumbers.getProduct(4); // return 0. The product of the last 4 numbers is 0 * 2 * 5 * 4 = 0
 * productOfNumbers.add(8);        // [3,0,2,5,4,8]
 * productOfNumbers.getProduct(2); // return 32. The product of the last 2 numbers is 4 * 8 = 32
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * There will be at most 40000 operations considering both add and getProduct.
 * 0 <= num <= 100
 * 1 <= k <= 40000
 */
public class DesignProductOfTheLastKNumbers {

    static class ProductOfNumbers {

        // Take a long list to avoid overflow - but no overflow with int also
        private List<Integer> prefixList;

        public ProductOfNumbers() {
            // Note I am not doing List.of(1) as it return immutable list and results in UnsupportedOperationException uoe
            prefixList = new ArrayList<>() {{
                add(1);
            }};

        }

        public void add(int num) {
            if (num == 0) {
                prefixList = new ArrayList<>() {{
                    add(1);
                }};
            } else {
                prefixList.add(prefixList.get(prefixList.size() - 1) * num);
            }
        }

        public int getProduct(int k) {
            int n = prefixList.size();
            return k < n ? prefixList.get(n - 1) / prefixList.get(n - 1 - k) : 0;
        }
    }
}
