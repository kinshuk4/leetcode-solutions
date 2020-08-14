package com.vaani.leetcode.bit_manipulation;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * https://leetcode.com/problems/convert-a-number-to-hexadecimal/
 * 405. Convert A Number To Hexidecimal
 * <p>
 * Given an integer, write an algorithm to convert it to hexadecimal. For negative integer, two’s complement method is
 * used.
 * <p>
 * Note:
 * <p>
 * All letters in hexadecimal (a-f) must be in lowercase.
 * The hexadecimal string must not contain extra leading 0s. If the number is zero, it is represented by a single zero
 * character '0'; otherwise, the first character in the hexadecimal string will not be the zero character.
 * The given number is guaranteed to fit within the range of a 32-bit signed integer.
 * You must not use any method provided by the library which converts/formats the number to hex directly.
 * Example 1:
 * <p>
 * Input:
 * 26
 * <p>
 * Output:
 * "1a"
 * Example 2:
 * <p>
 * Input:
 * -1
 * <p>
 * Output:
 * "ffffffff"
 * <p>
 * Related Topics: Bit Manipulation
 * <p>
 * Companies: Microsoft
 */
public class ConvertANumberToHexadecimal {

  private ConvertANumberToHexadecimal c;

  /**
   * Math
   * Given a number and a base, convert the number
   */
  public String toHex(int num) {
    if (num == 0) {
      return "0";
    }
    StringBuilder res = new StringBuilder();
    while (num != 0) {
      int digit = num & 0xf;
      res.insert(0, digit < 10 ? (char) (digit + '0') : (char) (digit - 10 + 'a'));
      num >>>= 4;
    }
    return res.toString();
  }

  // submitted
  public String toHex2(int num) {
    long numValue = num;
    if (num < 0) {
      numValue = (long) (Math.pow(2, 32) + num);
    }

    StringBuilder res = new StringBuilder();
    char[] chars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    while (numValue > 0) {
      int rem = (int) (numValue % 16);
      numValue = numValue / 16;
      res.insert(0, chars[rem]);
    }
    return res.toString().equals("") ? "0" : res.toString();
  }


  @Before
  public void setUp() {
    c = new ConvertANumberToHexadecimal();
  }

  @Test
  public void testPositiveNumbers() {
    String res = c.toHex(80);
    Assert.assertEquals("50", res);
    res = c.toHex(3200);
    Assert.assertEquals("c80", res);
  }

  @After
  public void tearDown() {
    c = null;
  }
}
