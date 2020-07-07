package com.vaani.leetcode.array;

import org.junit.Assert;

import java.util.*;

public class PrisonCellsAfterNDays {
    public int[] prisonAfterNDays(int[] cells, int N) {
        Set<String> set = new HashSet<>();
        Map<Integer, int[]> map = new HashMap<>();
        int patternSize = 0;
        boolean patternFound = false;
        for (int i = 0; i < N; i++) {
            int[] nextDayArr = nextDay(cells);
            String str = Arrays.toString(nextDayArr);
            if (!set.contains(str)) {
                set.add(str);

                patternSize++;
                map.put(patternSize, nextDayArr);
            } else {
                patternFound = true;
                break;
            }
            cells = nextDayArr;
        }

        if (patternFound) {
            N = N % patternSize;
            if (map.containsKey(N)){
                cells = map.get(N);
            }
        }

        return cells;
    }

    public int[] nextDay(int[] cells) {
        int[] result = new int[cells.length];
        for (int i = 1; i < cells.length - 1; i++) {
            if (cells[i - 1] == cells[i + 1]) {
                result[i] = 1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] input= {0,1,0,1,1,0,0,1};
        PrisonCellsAfterNDays underTest = new PrisonCellsAfterNDays();
        Assert.assertArrayEquals(new int[]{0,0,1,1,0,0,0,0}, underTest.prisonAfterNDays(input, 7));
    }
}
