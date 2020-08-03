package com.vaani.leetcode.binary_search;

/**
 * https://leetcode.com/problems/find-smallest-letter-greater-than-target/
 * 744. Find Smallest Letter Greater Than Target
 * Easy
 * Given a list of sorted characters letters containing only lowercase letters, and given a target letter target, find the smallest element in the list that is larger than the given target.
 * <p>
 * Letters also wrap around. For example, if the target is target = 'z' and letters = ['a', 'b'], the answer is 'a'.
 * <p>
 * Examples:
 * Input:
 * letters = ["c", "f", "j"]
 * target = "a"
 * Output: "c"
 * <p>
 * Input:
 * letters = ["c", "f", "j"]
 * target = "c"
 * Output: "f"
 * <p>
 * Input:
 * letters = ["c", "f", "j"]
 * target = "d"
 * Output: "f"
 * <p>
 * Input:
 * letters = ["c", "f", "j"]
 * target = "g"
 * Output: "j
 * <p>
 * <p>
 * "
 * <p>
 * Input:
 * letters = ["c", "f", "j"]
 * target = "j"
 * Output: "c"
 * <p>
 * Input:
 * letters = ["c", "f", "j"]
 * target = "k"
 * Output: "c"
 * Note:
 * letters has a length in range [2, 10000].
 * letters consists of lowercase letters, and contains at least 2 unique letters.
 * target is a lowercase letter.
 */


public class FindSmallestLetterGreaterThanTarget {
    static class UsingHashSet {
        /*
         * Let's scan through letters and record if we see a letter or not. We could do this with an array of size 26, or with a Set structure.
         * Then, for every next letter (starting with the letter that is one greater than the target), let's check if we've seen it. If we have, it must be the answer.
         */
        public char nextGreatestLetter(char[] letters, char target) {
            boolean[] seen = new boolean[26];
            for (char c : letters) {
                seen[c - 'a'] = true;
            }

            while (true) {
                target++;
                if (target > 'z') {
                    target = 'a';
                }
                if (seen[target - 'a']) {
                    return target;
                }
            }
        }
    }

    static class UsingBinarySearch {
        // O(lg n)
        public char nextGreatestLetter(char[] letters, char target) {
            int n = letters.length;

            int lo = 0;
            //hi starts at 'n' rather than the usual 'n - 1'.
            //It is because the terminal condition is 'lo < hi' and if hi starts from 'n - 1',
            //we can never consider value at index 'n - 1'
            int hi = n;

            //Terminal condition is 'lo < hi', to avoid infinite loop when target is smaller than the first element
            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;
                if (letters[mid] > target) {
                    hi = mid;
                } else {
                    lo = mid + 1;
                }
            }

            //Because lo can end up pointing to index 'n', in which case we return the first element
            return letters[lo % n];
        }
    }
}
