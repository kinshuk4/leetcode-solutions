package com.vaani.leetcode.biweekly;

import java.util.LinkedList;
import java.util.List;

public class CategorizeBoxAccordingToCriteria {
    public String categorizeBox(int length, int width, int height, int mass) {
        final int DIM_BULKY = 10000;
        final long VOL_BULKY = 1000000000;
        List<String> ans = new LinkedList<>();
        if (length >= DIM_BULKY || width >= DIM_BULKY || height >= DIM_BULKY) {
            ans.add("BULKY");
        }else {
            long vol = length;
            vol = vol * width * height;
            if (vol >= VOL_BULKY) {
                ans.add("BULKY");
            }
        }
        if (mass >= 100) {
            ans.add("Heavy");
        }

        return switch (ans.size()) {
            case 1 -> ans.get(0);
            case 2 -> "Both";
            case 0 -> "Neither";
            default -> "Neither";
        };
    }
}
