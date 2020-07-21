package com.vaani.leetcode.string;

/**
 * Given a valid (IPv4) IP address, return a defanged version of that IP address.
 * <p>
 * A defanged IP address replaces every period "." with "[.]".
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: address = "1.1.1.1"
 * Output: "1[.]1[.]1[.]1"
 * <p>
 * Example 2:
 * <p>
 * Input: address = "255.100.50.0"
 * Output: "255[.]100[.]50[.]0"
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The given address is a valid IPv4 address.
 */
public class DefangingAnIPAddress {
    public String defangIPaddr(String address) {
        char[] result = new char[address.length() + 6];

        int i = 0;
        for (char ch : address.toCharArray()) {
            if (ch == '.') {
                result[i++] = '[';
                result[i++] = '.';
                result[i++] = ']';
            } else {
                result[i++] = ch;
            }
        }
        return new String(result);

    }
}
