package com.vaani.leetcode.string;

/**
 * https://leetcode.com/problems/add-strings/
 * Given two non-negative numbers num1 and num2 represented as string, return the sum of num1 and num2.
 * Note:
 * The length of both num1 and num2 is < 5100.
 * Both num1 and num2 contains only digits 0-9.
 * Both num1 and num2 does not contain any leading zero.
 * You must not use any built-in BigInteger library or convert the inputs to integer directly.
 */
public class AddStrings {

    // submitted Simpler and takes 4 ms
    @SuppressWarnings("Duplicates")
    public static String addStrings(String num1, String num2) {
        StringBuilder result = new StringBuilder();
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        int carry = 0;


        while (i >= 0 || j >= 0) {
            int n1 = i >= 0 ? num1.charAt(i) - '0' : 0;
            int n2 = j >= 0 ? num2.charAt(j) - '0' : 0;

            int sum = (n1 + n2 + carry) % 10;
            carry = (n1 + n2 + carry) / 10;

            result.append(sum);
            i--;
            j--;
        }

        if (carry == 1) {
            result.append("1");
        }

        return result.reverse().toString();
    }

    // StringBuilder with inserting at offset 0, so no need to reverse, Takes 5 ms
    @SuppressWarnings("Duplicates")
    public static String addStrings2(String num1, String num2) {
        StringBuilder result = new StringBuilder();

        int i = num1.length() - 1;
        int j = num2.length() - 1;
        int carry = 0;


        while (i >= 0 || j >= 0) {
            int n1 = i >= 0 ? num1.charAt(i) - '0' : 0;
            int n2 = j >= 0 ? num2.charAt(j) - '0' : 0;

            int sum = (n1 + n2 + carry) % 10;
            carry = (n1 + n2 + carry) / 10;

            result.insert(0, sum);
            i--;
            j--;
        }

        if (carry == 1) {
            result.insert(0, "1");
        }

        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(addStrings("123", "4566"));
    }
}
