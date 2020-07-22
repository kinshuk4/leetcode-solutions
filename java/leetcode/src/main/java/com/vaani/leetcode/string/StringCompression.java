package com.vaani.leetcode.string;

/**
 * 12/04/2018. Given an array of characters, compress it in-place.
 *
 * <p>The length after compression must always be smaller than or equal to the original array.
 *
 * <p>Every element of the array should be a character (not int) of length 1.
 *
 * <p>After you are done modifying the input array in-place, return the new length of the array.
 *
 * <p>Follow up: Could you solve it using only O(1) extra space?
 *
 * <p>Example 1: Input: ["a","a","b","b","c","c","c"]
 *
 * <p>Output: Return 6, and the first 6 characters of the input array should be:
 * ["a","2","b","2","c","3"]
 *
 * <p>Explanation: "aa" is replaced by "a2". "bb" is replaced by "b2". "ccc" is replaced by "c3".
 * Example 2: Input: ["a"]
 *
 * <p>Output: Return 1, and the first 1 characters of the input array should be: ["a"]
 *
 * <p>Explanation: Nothing is replaced. Example 3: Input:
 * ["a","b","b","b","b","b","b","b","b","b","b","b","b"]
 *
 * <p>Output: Return 4, and the first 4 characters of the input array should be: ["a","b","1","2"].
 *
 * <p>Explanation: Since the character "a" does not repeat, it is not compressed. "bbbbbbbbbbbb" is
 * replaced by "b12". Notice each digit has it's own entry in the array. Note: All characters have
 * an ASCII value in [35, 126]. 1 <= len(chars) <= 1000.
 *
 * <p>Solution O(N) time complexity and O(1) space complexity. Maintain read and write pointers.
 * Read from read pointer and increment count when a repetition is found, when there is no
 * repetition write the count value using write pointer.
 */
public class StringCompression {

    /**
     * Main method
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        char[] A = {'a', 'a', 'b', 'b', 'c', 'c', 'c'};
        System.out.println(new StringCompression().compress(A));
    }

    // when we have ['a], then we just return [a] and not [a1]
    // so, length of repeatin char should be strictly > 1
    public int compress(char[] chars) {
        int count = 0;
        int i = 0;
        int p = 0;
        for (int j = 0; j < chars.length; j++) {
            if (chars[i] == chars[j]) {
                count++;
            } else {
                p = updateCharsWithCount(chars, count, i, p);
                i = j;
                count = 1;
            }
        }
        p = updateCharsWithCount(chars, count, i, p);
        return p;
    }

    private int updateCharsWithCount(char[] chars, int count, int i, int p) {
        chars[p] = chars[i];
        p++;
        if (count > 1) {
            String countStr = String.valueOf(count);
            for (int l = 0; l < countStr.length(); l++) {
                chars[p++] = countStr.charAt(l);
            }
        }
        return p;
    }


    public int compressCleaner(char[] chars) {
        int n = chars.length;
        if(n <= 1){
            return n;
        }
        int i = 0;
        int idx = 0;
        while (i < n){
            int j = i;
            while (j < n && chars[j] == chars[i]){
                j++;
            }
            chars[idx++] = chars[i];
            // the substring with repeating chars should have size strictly greater than 1
            if(j - i > 1){
                 String count = Integer.toString(j - i);
                 for (char ch: count.toCharArray()){
                     chars[idx++] = ch;
                 }
            }

            // update i
            i = j;
        }
        return idx;
    }
}
