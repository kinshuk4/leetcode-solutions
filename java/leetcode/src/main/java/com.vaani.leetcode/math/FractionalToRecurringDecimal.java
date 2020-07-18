package com.vaani.leetcode.math;

import java.util.HashMap;

/**
 * https://leetcode.com/problems/fraction-to-recurring-decimal/
 * <p>
 * Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.
 * <p>
 * If the fractional part is repeating, enclose the repeating part in parentheses.
 * <p>
 * Example 1:
 * <p>
 * Input: numerator = 1, denominator = 2
 * Output: "0.5"
 * <p>
 * Example 2:
 * <p>
 * Input: numerator = 2, denominator = 1
 * Output: "2"
 * <p>
 * Example 3:
 * <p>
 * Input: numerator = 2, denominator = 3
 * Output: "0.(6)"
 */
public class FractionalToRecurringDecimal {
    public static void main(String[] args) {
        System.out.println(fractionToDecimal(-2147483648, -1));
    }

    public static String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }

        if (numerator % denominator == 0) {
            if (numerator == Integer.MIN_VALUE && denominator == -1) {
                // https://stackoverflow.com/questions/51953199/integer-min-value-divide-by-1
                return "2147483648";
            }
            return Integer.toString(numerator / denominator);
        }
        StringBuilder sb = new StringBuilder();
        // "+" or "-"
        sb.append(((numerator > 0) ^ (denominator > 0)) ? "-" : "");
        long numL = Math.abs((long) numerator);
        long denL = Math.abs((long) denominator);

        // integral part - check with 2/3 => 0
        sb.append(numL / denL);
        numL %= denL;

        // fractional part
        sb.append("."); // 0.
        HashMap<Long, Integer> map = new HashMap<>();
        map.put(numL, sb.length()); // 2 -> 2
        while (numL != 0) {
            numL *= 10; // 20;
            sb.append(numL / denL); //0.6
            numL %= denL; // 2
            if (map.containsKey(numL)) {
                int index = map.get(numL); // 2
                sb.insert(index, "("); //
                sb.append(")");
                break;
            } else {
                map.put(numL, sb.length());
            }
        }
        return sb.toString();
    }
}
