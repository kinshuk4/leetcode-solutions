package com.vaani.leetcode.binary_search;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/**
 * https://leetcode.com/problems/random-point-in-non-overlapping-rectangles/
 * 497. Random Point in Non-overlapping Rectangles
 * Medium
 * <p>
 * 237
 * <p>
 * 367
 * <p>
 * Add to List
 * <p>
 * Share
 * Given a list of non-overlapping axis-aligned rectangles rects, write a function pick which randomly and uniformily picks an integer point in the space covered by the rectangles.
 * <p>
 * Note:
 * <p>
 * An integer point is a point that has integer coordinates.
 * A point on the perimeter of a rectangle is included in the space covered by the rectangles.
 * ith rectangle = rects[i] = [x1,y1,x2,y2], where [x1, y1] are the integer coordinates of the bottom-left corner, and [x2, y2] are the integer coordinates of the top-right corner.
 * length and width of each rectangle does not exceed 2000.
 * 1 <= rects.length <= 100
 * pick return a point as an array of integer coordinates [p_x, p_y]
 * pick is called at most 10000 times.
 * Example 1:
 * <p>
 * Input:
 * ["Solution","pick","pick","pick"]
 * [[[[1,1,5,5]]],[],[],[]]
 * Output:
 * [null,[4,1],[4,1],[3,3]]
 * Example 2:
 * <p>
 * Input:
 * ["Solution","pick","pick","pick","pick","pick"]
 * [[[[-2,-2,-1,-1],[1,0,3,0]]],[],[],[],[],[]]
 * Output:
 * [null,[-1,-2],[2,0],[-2,-1],[3,0],[-2,-2]]
 * Explanation of Input Syntax:
 * <p>
 * The input is two lists: the subroutines called and their arguments. Solution's constructor has one argument, the array of rectangles rects. pick has no arguments. Arguments are always wrapped with a list, even if there aren't any.
 */
public class RandomPointInNonOverlappingRectangles {

    public static void main(String[] args) {
        int[][] rects = {{1, 1, 5, 5}};
        int num = 100;
        UsingHashMap r = new UsingHashMap(rects);
        while (num-- > 0) {
            int[] p = r.pick();
            System.out.println(p[0] + "," + p[1]);
        }
    }

    static class UsingHashMap {

        Map<int[], int[]> map = new HashMap<>();
        Random rand = new Random();
        int totalArea;

        public UsingHashMap(int[][] rects) {
            int area = 0, oldArea;
            for (int[] rect : rects) {
                oldArea = area;
                area += (rect[2] - rect[0]) * (rect[3] - rect[1]);
                map.putIfAbsent(new int[]{oldArea, area}, rect);
            }
            this.totalArea = area;
        }

        public int[] pick() {
            int area = rand.nextInt(totalArea);
            int[] rect = null;
            for (int[] areaKey : map.keySet()) {
                if (area >= areaKey[0] && area < areaKey[1]) {
                    rect = map.get(areaKey);
                    break;
                }
            }
            int x = (int) (rect[0] + rand.nextDouble() * (rect[2] - rect[0]));
            int y = (int) (rect[1] + rand.nextDouble() * (rect[3] - rect[1]));

            return new int[]{x, y};
        }
    }

    static class UsingTreeMap {
        TreeMap<Integer, int[]> map = new TreeMap<>();
        Random rand = new Random();
        int totalArea;

        public UsingTreeMap(int[][] rects) {
            this.totalArea = 0;
            for (int[] rect : rects) {
                totalArea += (rect[2] - rect[0] + 1) * (rect[3] - rect[1] + 1);
                map.put(totalArea, rect);
            }
        }

        public int[] pick() {
            int randArea = rand.nextInt(totalArea);
            int key = map.higherKey(randArea);
            int[] rect = map.get(key);

            int diff = key - randArea - 1;
            int width = (rect[2] - rect[0] + 1);

            int x = rect[0] + diff % width;
            int y = rect[1] + diff / width;

            return new int[]{x, y};
        }
    }


}