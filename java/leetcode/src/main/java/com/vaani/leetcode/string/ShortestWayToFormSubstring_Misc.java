package com.vaani.leetcode.string;

import java.util.*;

/** From any string, we can form a subsequence of that string by deleting some number of characters (possibly no deletions)
 * Given 2 strings target and source, find the minimum number of steps i.e. number of subsequences of source, to make target a substring of source. In 1 step you can take a substring of source and append it at the end of source.
 *
 * Example 1:
 *
 * Input: target = "abcd", source = "dbcfda"
 * Output: 2
 * Explanation:
 * The target string can be contracted as follow "dbcfda" + "bc" + "d" = "dbcfdabcd"
 * So the minimum number of steps is 2.
 *
 * Example 2:
 *
 * Input: target = "abc", source = "abdabb"
 * Output: -1
 *
 * Example 3:
 *
 * Input: target = "abcd", source = "fabcda"
 * Output: 0
 */
public class ShortestWayToFormSubstring_Misc {
}
