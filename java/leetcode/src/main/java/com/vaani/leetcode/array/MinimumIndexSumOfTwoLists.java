package com.vaani.leetcode.array;

import java.util.*;

/**
 * Suppose Andy and Doris want to choose a restaurant
 * for dinner, and they both have a list of favorite restaurants represented by strings.
 *
 * <p>You need to help them find out their common interest with the least list index sum. If there
 * is a choice tie between answers, output all of them with no order requirement. You could assume
 * there always exists an answer.
 *
 * <p>Example 1: Input: ["Shogun", "Tapioca Express", "Burger King", "KFC"] ["Piatti", "The Grill at
 * Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"] Output: ["Shogun"] Explanation: The only
 * restaurant they both like is "Shogun". Example 2: Input: ["Shogun", "Tapioca Express", "Burger
 * King", "KFC"] ["KFC", "Shogun", "Burger King"] Output: ["Shogun"] Explanation: The restaurant
 * they both like and have the least index sum is "Shogun" with index sum 1 (0+1). Note: The length
 * of both lists will be in the range of [1, 1000]. The length of strings in both lists will be in
 * the range of [1, 30]. The index is starting from 0 to the list length minus 1. No duplicates in
 * both lists.
 *
 * <p>Solution:O(N) maintain index of each restaurant in a list using a HashMap, find the min sum of
 * indices and list all the restaurants which match the min sum of indices
 */
public class MinimumIndexSumOfTwoLists {
    public static void main(String[] args) throws Exception {
        String[] A1 = {"Shogun", "Tapioca Express", "Burger King", "KFC"};
        String[] A2 = {"Tapioca Express", "Shogun", "Burger King"};
        String[] ans = new MinimumIndexSumOfTwoLists().findRestaurant(A1, A2);
        for (String s : ans) {
            System.out.println(s);
        }
    }

    public String[] findRestaurant(String[] list1, String[] list2) {
        if(list1.length == 0 || list2.length == 0){
            return new String[0];
        }
        List<String> result = new ArrayList<>();

        Map<String, Integer> map1 = new HashMap<>();
        for (int i = 0; i < list1.length; i++) {
            map1.put(list1[i], i);
        }

        TreeMap<Integer, List<String>> resultMap = new TreeMap<>();
        for (int i = 0; i < list2.length; i++) {
            String s2 = list2[i];
            if(map1.containsKey(s2)){
                int sumKey = i + map1.get(s2);
                List<String> currResult = resultMap.getOrDefault(sumKey, new ArrayList<>());
                currResult.add(s2);
                resultMap.put(sumKey, currResult);
            }

        }

        List<String> resultList = resultMap.firstEntry().getValue();
        return resultList.toArray(new String[0]);
    }

    /*
    Solution O(N) Maintain a hashmap of restaurant_name with index for one of the list. In the
 * first iteration calculate the minimum of sum of indices and in the second iteration add the
 * restaurant name to the list if any sum of indices equals the minimum.
     */
    public String[] findRestaurant2(String[] list1, String[] list2) {
        Map<String, Integer> index = new HashMap<>();
        for (int i = 0; i < list2.length; i++) {
            String s = list2[i];
            index.put(s, i);
        }
        int min = Integer.MAX_VALUE;
        List<String> list = new ArrayList<>();
        for (int i = 0; i < list1.length; i++) {
            if (index.containsKey(list1[i])) {
                if (i + index.get(list1[i]) <= min) {
                    min = i + index.get(list1[i]);
                }
            }
        }
        for (int i = 0; i < list1.length; i++) {
            if (index.containsKey(list1[i])) {
                if (i + index.get(list1[i]) == min) {
                    list.add(list1[i]);
                }
            }
        }
        String[] ans = new String[list.size()];
        int i = 0;
        for (String s : list) {
            ans[i++] = s;
        }
        return ans;




//        int min = Integer.MAX_VALUE;
//        for (int i = 0; i < list1.length; i++) {
//            String s = list1[i];
//            if (map1.containsKey(s)) {
//                int sum = i + map1.get(s);
//                min = Math.min(min, sum);
//            }
//        }
//
//        for (int i = 0; i < list1.length; i++) {
//            String s = list1[i];
//            if (map1.containsKey(s)) {
//                int sum = i + map1.get(s);
//                if (sum == min) {
//                    result.add(s);
//                }
//            }
//        }
//        String[] resArr = new String[result.size()];
//        result.toArray(resArr);
//        return resArr;
    }
}
