package com.vaani.leetcode.math;

import java.util.*;

/**
 * https://leetcode.com/problems/erect-the-fence/
 * 587. Erect the Fence
 * Hard

 * You are given an array trees where trees[i] = [xi, yi] represents the location of a tree in the garden.
 *
 * You are asked to fence the entire garden using the minimum length of rope as it is expensive. The garden is well fenced only if all the trees are enclosed.
 *
 * Return the coordinates of trees that are exactly located on the fence perimeter.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: points = [[1,1],[2,2],[2,0],[2,4],[3,3],[4,2]]
 * Output: [[1,1],[2,0],[3,3],[2,4],[4,2]]
 * Example 2:
 *
 *
 * Input: points = [[1,2],[2,2],[4,2]]
 * Output: [[4,2],[2,2],[1,2]]
 *
 *
 * Constraints:
 *
 * 1 <= points.length <= 3000
 * points[i].length == 2
 * 0 <= xi, yi <= 100
 * All the given points are unique.
 */
public class ErectTheFence {
    public int[][] outerTrees(int[][] trees) {
        int[] start = trees[0];
        for(int i = 1; i < trees.length; i++){
            if(trees[i][0] < start[0]){
                start = trees[i];
            }
        }
        int[] cur = start;
        Set<int[]> set = new HashSet<>();
        set.add(start);
        List<int[]> col = new ArrayList<>();
        while(true){
            int[] next = trees[0];
            for(int i = 1; i < trees.length; i++){
                if(Arrays.equals(trees[i], cur)){
                    continue;
                }
                int val = crossProduct(cur, next, trees[i]);
                if(val > 0){
                    next = trees[i];
                    col = new ArrayList<>();
                }else if(val == 0){
                    if(distance(cur, next, trees[i]) < 0){
                        col.add(next);
                        next = trees[i];
                    }else{
                        col.add(trees[i]);
                    }
                }
            }
            set.addAll(col);
            if(Arrays.equals(next, start)){
                break;
            }
            set.add(next);
            cur = next;
        }
        int n = set.size();
        int[][] res = new int[n][2];
        int i = 0;
        for (int[] ints : set) {
            res[i] = ints;
            i++;
        }
        return res;

    }
    private int distance(int[] a, int[] b, int[] c){
        int dy1 = a[1] - b[1];
        int dy2 = a[1] - c[1];
        int dx1 = a[0] - b[0];
        int dx2 = a[0] - c[0];
        return Integer.compare(dy1 * dy1 + dx1 * dx1, dy2 * dy2 + dx2 * dx2);
    }
    private int crossProduct(int[] a, int[] b, int[] c){
        int dy1 = a[1] - b[1];
        int dy2 = a[1] - c[1];
        int dx1 = a[0] - b[0];
        int dx2 = a[0] - c[0];
        return dy2 * dx1 - dy1 * dx2;
    }

}
