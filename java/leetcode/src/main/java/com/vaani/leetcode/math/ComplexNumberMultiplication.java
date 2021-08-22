package com.vaani.leetcode.math;

/**
 * https://leetcode.com/problems/complex-number-multiplication/
 * 537. Complex Number Multiplication
 * Medium
 * <p>
 * A complex number can be represented as a string on the form "real+imaginaryi" where:
 * <p>
 * real is the real part and is an integer in the range [-100, 100].
 * imaginary is the imaginary part and is an integer in the range [-100, 100].
 * i2 == -1.
 * Given two complex numbers num1 and num2 as strings, return a string of the complex number that represents their multiplications.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: num1 = "1+1i", num2 = "1+1i"
 * Output: "0+2i"
 * Explanation: (1 + i) * (1 + i) = 1 + i2 + 2 * i = 2i, and you need convert it to the form of 0+2i.
 * Example 2:
 * <p>
 * Input: num1 = "1+-1i", num2 = "1+-1i"
 * Output: "0+-2i"
 * Explanation: (1 - i) * (1 - i) = 1 + i2 - 2 * i = -2i, and you need convert it to the form of 0+-2i.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * num1 and num2 are valid complex numbers.
 */
public class ComplexNumberMultiplication {
    public String complexNumberMultiply(String num1, String num2) {
        int r1 = Integer.parseInt(num1.substring(0, num1.indexOf("+")));
        int i1 = Integer.parseInt(num1.substring(num1.indexOf("+") + 1, num1.length() - 1));
        int r2 = Integer.parseInt(num2.substring(0, num2.indexOf("+")));
        int i2 = Integer.parseInt(num2.substring(num2.indexOf("+") + 1, num2.length() - 1));

        int r3 = r1 * r2 + i1 * i2 * -1;
        int i3 = r1 * i2 + r2 * i1;

        return r3 + "+" + i3 + "i";
    }
}
