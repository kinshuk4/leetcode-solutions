package com.vaani.leetcode.string;

import java.util.StringTokenizer;

/**
 * 165. Compare Version Numbers
 * Medium
 * <p>
 * Compare two version numbers version1 and version2.
 * If version1 > version2 return 1; if version1 < version2 return -1;otherwise return 0.
 * <p>
 * You may assume that the version strings are non-empty and contain only digits and the . character.
 * <p>
 * The . character does not represent a decimal point and is used to separate number sequences.
 * <p>
 * For instance, 2.5 is not "two and a half" or "half way to version three", it is the fifth second-level revision of the second first-level revision.
 * <p>
 * You may assume the default revision number for each level of a version number to be 0. For example, version number 3.4 has a revision number of 3 and 4 for its first and second level revision number. Its third and fourth level revision number are both 0.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: version1 = "0.1", version2 = "1.1"
 * Output: -1
 * Example 2:
 * <p>
 * Input: version1 = "1.0.1", version2 = "1"
 * Output: 1
 * Example 3:
 * <p>
 * Input: version1 = "7.5.2.4", version2 = "7.5.3"
 * Output: -1
 * Example 4:
 * <p>
 * Input: version1 = "1.01", version2 = "1.001"
 * Output: 0
 * Explanation: Ignoring leading zeroes, both “01” and “001" represent the same number “1”
 * Example 5:
 * <p>
 * Input: version1 = "1.0", version2 = "1.0.0"
 * Output: 0
 * Explanation: The first version number does not have a third level revision number, which means its third level revision number is default to "0"
 * <p>
 * <p>
 * Note:
 * <p>
 * Version strings are composed of numeric strings separated by dots . and this numeric strings may have leading zeroes.
 * Version strings do not start or end with dots, and they will not be two consecutive dots.
 */
public class CompareVersionNumbers {
    public static void main(String[] args) throws Exception {
        System.out.println(new CompareVersionNumbers.UsingStringTokenizer().compareVersion("1.11.1", "1.11"));
    }

    public int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        int sectionV1 = Integer.parseInt(v1[0]);
        int sectionV2 = Integer.parseInt(v2[0]);
        int section = 1;

        if (sectionV1 < sectionV2) {
            return -1;
        } else if (sectionV1 > sectionV2) {
            return 1;
        } else {
            while (sectionV1 == sectionV2) {
                sectionV1 = v1.length > section ? Integer.parseInt(v1[section]) : 0;
                sectionV2 = v2.length > section ? Integer.parseInt(v2[section]) : 0;
                section++;
                if (sectionV1 < sectionV2) {
                    return -1;
                } else if (sectionV1 > sectionV2) {
                    return 1;
                } else {
                    if (v1.length <= section && v2.length <= section) {
                        return 0;
                    }
                }
            }
            return 0;
        }
    }

    static class UsingStringTokenizer {
        public int compareVersion(String version1, String version2) {
            StringTokenizer st1 = new StringTokenizer(version1, ".");
            StringTokenizer st2 = new StringTokenizer(version2, ".");
            while (st1.hasMoreTokens() && st2.hasMoreTokens()) {
                int token1 = Integer.parseInt(st1.nextToken());
                int token2 = Integer.parseInt(st2.nextToken());
                if (token1 > token2) {
                    return 1;
                } else if (token2 > token1) {
                    return -1;
                }
            }

            boolean left = st1.countTokens() > st2.countTokens();
            StringTokenizer remaining = left ? st1: st2;

            while (remaining.hasMoreTokens()){
                if (Integer.parseInt(remaining.nextToken()) > 0) {
                    return left ? 1 : -1;
                }
            }

            return 0;
        }
    }
}
