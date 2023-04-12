package com.vaani.leetcode.interval;

import static org.junit.Assert.assertEquals;

public class MaximizeWinFromTwoSegments {
    public static void main(String[] args) {
        MaximizeWinFromTwoSegments underTest = new MaximizeWinFromTwoSegments();
        assertEquals(underTest.maximizeWin(new int[]{1, 1, 2, 2, 3, 3, 5}, 2), 7);
    }
    public int maximizeWin(int[] prizePositions, int k) {
        int[] numPrizes = new int[prizePositions.length+1];
        for (int p: prizePositions) {
            numPrizes[p] += 1;
        }
        int size = numPrizes.length;
        while(numPrizes[size-1] == 0) {
            size = size -1;
        }

        int[] prefix = new int[size - k];
        for (int i = 1; i <= 1 + k; i++) {
            prefix[1] += numPrizes[i];
        }
        for (int i = 2; i < size - k;i++) {
            prefix[i] = prefix[i-1] + numPrizes[i+k] - numPrizes[i-1];
        }

        int max1 = 0, max2 = 0, max3 = 0;
        int i1 = 0, i2 = 0, i3 = 0;
        for (int i = 1; i < prefix.length; i++) {
            // greater than first
            if (prefix[i] > max1)
            {
                max3 = max2;
                i3 = i2;
                max2 = max1;
                i2 = i1;
                max1 = prefix[i];
                i1 = i;
            }

            // If arr[i] is in between first
            // and second then update second
            else if (prefix[i] > max2)
            {
                max3 = max2;
                i3 = i2;
                max2 = prefix[i];
                i2 = i;
            }

            else if (prefix[i] > max3) {
                max3 = prefix[i];
                i3 = i;
            }
        }


        if (i3 != i1 + k) {
            return max1 + max2;
        }
        int sum1 = max1+ max2 - prefix[i3];
        int sum2 = max1+ max3;
        return Math.max(sum1, sum2);


    }
}
