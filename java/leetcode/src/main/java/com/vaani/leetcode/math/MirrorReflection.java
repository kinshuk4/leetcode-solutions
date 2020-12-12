package com.vaani.leetcode.math;

/**
 * 858. Mirror Reflection
 * Medium
 * <p>
 * There is a special square room with mirrors on each of the four walls.
 * Except for the southwest corner, there are receptors on each of the remaining corners, numbered 0, 1, and 2.
 * <p>
 * The square room has walls of length p,
 * and a laser ray from the southwest corner first meets the east wall at a distance q from the 0th receptor.
 * <p>
 * Return the number of the receptor that the ray meets first.
 * (It is guaranteed that the ray will meet a receptor eventually.)
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: p = 2, q = 1
 * Output: 2
 * Explanation: The ray meets receptor 2 the first time it gets reflected back to the left wall.
 * <p>
 * Note:
 * <p>
 * 1 <= p <= 1000
 * 0 <= q <= p
 */
public class MirrorReflection {
    public int mirrorReflection(int p, int q) {
        int vertical = 0;         //Vertical distance
        boolean isRight = true;  //Side we are on currently  i.e. left/right
        boolean up = true;       //Are we moving up or down currently
        while (true) {
            if (up) {
                vertical += q; //if moving up increment vertical distance
            } else {
                vertical -= q; //if moving down decrement vertical distance
            }

            if (vertical == 0) {
                return 0;
            } else if (vertical == p) {
                if (isRight) {
                    return 1;
                } else {
                    return 2;
                }
            } else if (vertical > p) {   //if we go higher than box
                up = false;     //move down
                vertical = 2 * p - vertical; //update the moved distance
            } else if (vertical < 0) { //if we go below the box
                up = true;  //move up
                vertical = -vertical; //update distance moved
            }
            isRight = !isRight;  //change side each time
        }
    }
}
