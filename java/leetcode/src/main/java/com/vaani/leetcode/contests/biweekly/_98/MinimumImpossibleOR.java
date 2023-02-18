package com.vaani.leetcode.contests.biweekly._98;

import java.util.ArrayList;
import java.util.*;
import java.util.stream.Collectors;

public class MinimumImpossibleOR {
    public int minImpossibleOR(int[] nums) {
        Set<Integer> set = Arrays.stream(nums).boxed().collect(Collectors.toSet());
        int ans = 1;
        while(set.contains(ans)) {
            ans = ans * 2;
        }
        return ans;
    }
}
