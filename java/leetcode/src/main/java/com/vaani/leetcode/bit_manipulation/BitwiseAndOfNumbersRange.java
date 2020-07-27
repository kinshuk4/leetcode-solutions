package com.vaani.leetcode.bit_manipulation;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 201. Bitwise AND of Numbers Range: https://leetcode.com/problems/bitwise-and-of-numbers-range/
 * medium
 * <p>
 * Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range,
 * inclusive.
 * <p>
 * For example, given the range [5, 7], you should return 4.
 * <p>
 * Tags: Bit Manipulation
 */
public class BitwiseAndOfNumbersRange {

  private BitwiseAndOfNumbersRange b;

  /**
   * The AND result of the last bit of odd and even numbers should be 0.
   * So if m and n are not equal, check the last bit of them.
   * Count the number of iterations.
   * When m and n are equal, it means there are no more odd and even pairs within the range.
   * m has been divided by 2 for count times, so the result it m * count.
   */
  // https://www.youtube.com/watch?v=-qrpJykY2gE
  // faster - 10 ms
  public int rangeBitwiseAnd(int m, int n) {
    if (m == 0) {
      return 0;
    }
    int moveFactor = 1;
    while (m != n) {
      m >>= 1;
      n >>= 1;
      moveFactor <<= 1;
    }
    return m * moveFactor;
  }

  // slower = 10ms
  public int rangeBitwiseAnd2(int m, int n) {
    if (m == 0) {
      return 0;
    }
    int count = 0;
    while (m != n) {
      m >>= 1;
      n >>= 1;
      count++;
    }
    return m << count;
  }


  @Before
  public void setUp() {
    b = new BitwiseAndOfNumbersRange();
  }

  @Test
  public void testEdgeCases() {
    Assert.assertEquals(0, b.rangeBitwiseAnd(0, 0));
    Assert.assertEquals(0, b.rangeBitwiseAnd(0, 1));
    Assert.assertEquals(0, b.rangeBitwiseAnd(0, Integer.MAX_VALUE));
    Assert.assertEquals(Integer.MAX_VALUE, b.rangeBitwiseAnd(Integer.MAX_VALUE, Integer.MAX_VALUE));
  }

  @Test
  public void testExamples() {
    Assert.assertEquals(0, b.rangeBitwiseAnd(1, 7));
    Assert.assertEquals(4, b.rangeBitwiseAnd(5, 7));
    Assert.assertEquals(6, b.rangeBitwiseAnd(6, 7));
  }

  @After
  public void tearDown() {
    b = null;
  }

}
