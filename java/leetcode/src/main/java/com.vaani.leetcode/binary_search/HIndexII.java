package com.vaani.leetcode.binary_search;

/**
 * 11/12/2017.
 *
 * <p>Follow up for H-Index: What if the citations com.vaani.leetcode.array is sorted in ascending order? Could you
 * optimize your algorithm?
 *
 * @see com.vaani.leetcode.array.HIndex
 */
public class HIndexII {
  /**
   * Main method
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    int[] A = {1, 1, 1, 1, 1, 15, 20};
    System.out.println(new HIndexII().hIndex(A));
  }

  public int hIndex(int[] citations) {
    if (citations.length == 0) return 0;
    int s = 0, e = citations.length;
    int ans = -1;
    while (s < e) {
      int m = (s + e) >>> 1;
      int cit = citations.length - m;
      if (citations[m] > cit) {
        if (ans < cit) {
          ans = cit;
        }
        e = m;
      } else {
        if (ans < citations[m]) {
          ans = citations[m];
        }
        s = m + 1;
      }
    }
    return ans;
  }
}
