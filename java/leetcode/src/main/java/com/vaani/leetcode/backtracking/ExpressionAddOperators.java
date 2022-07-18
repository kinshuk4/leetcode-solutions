package com.vaani.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string num that contains only digits and an integer target, return all possibilities to add the binary operators '+', '-', or '*' between the digits of num so that the resultant expression evaluates to the target value.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: num = "123", target = 6
 * Output: ["1*2*3","1+2+3"]
 * Example 2:
 * <p>
 * Input: num = "232", target = 8
 * Output: ["2*3+2","2+3*2"]
 * Example 3:
 * <p>
 * Input: num = "105", target = 5
 * Output: ["1*0+5","10-5"]
 * Example 4:
 * <p>
 * Input: num = "00", target = 0
 * Output: ["0*0","0+0","0-0"]
 * Example 5:
 * <p>
 * Input: num = "3456237490", target = 9191
 * Output: []
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= num.length <= 10
 * num consists of only digits.
 * -2^31 <= target <= 2^31 - 1
 */
public class ExpressionAddOperators {
    public static void main(String[] args) throws Exception {
        List<String> result = new ExpressionAddOperators().addOperators("202010201", 201);
        result.stream().forEach(System.out::println);
    }

    /**
     * <p>Solution: Backtrack and keep track of the total and product value. In case of + or - add/sub
     * curr to total and curr becomes the new product In case of * take difference of total and prod and
     * add (product of curr value with previous product and make this a new product for the next
     * iteration)
     *
     * <p>Worst-case time complexity can be O(n * (2^n-1))
     */
    public List<String> addOperators(String num, int target) {
        List<String> result = new ArrayList<>();
        backTrack("", result, 0, num, target, 0L, 0L);
        return result;
    }

    private void backTrack(
            String exp, List<String> list, int curr, String num, int target, long total, long prod) {
        if (curr == num.length()) {
            if (total == target) {
                list.add(exp);
            }
        } else {
            for (int i = curr, l = num.length(); i < l; i++) {
                String newNum = num.substring(curr, i + 1);
                if (newNum.length() > 1 && newNum.startsWith("0")) {
                    break;
                }
                long newNumL = Long.parseLong(newNum);
                if (curr == 0) {
                    backTrack(newNum, list, i + 1, num, target, newNumL, newNumL);
                } else {
                    backTrack(exp + "+" + newNum, list, i + 1, num, target, total + newNumL, newNumL);

                    backTrack(exp + "-" + newNum, list, i + 1, num, target, total - newNumL, newNumL * -1L);

                    backTrack(
                            exp + "*" + newNum,
                            list,
                            i + 1,
                            num,
                            target,
                            (total - prod + (prod * newNumL)),
                            prod * newNumL);
                }
            }
        }
    }
}
