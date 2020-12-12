package com.vaani.leetcode.array;

/**
 * 849. Maximize Distance to Closest Person
 * Medium
 * <p>
 * You are given an array representing a row of seats where seats[i] = 1 represents a person sitting in the ith seat,
 * and seats[i] = 0 represents that the ith seat is empty (0-indexed).
 * <p>
 * There is at least one empty seat, and at least one person sitting.
 * <p>
 * Alex wants to sit in the seat such that the distance between him and the closest person to him is maximized.
 * <p>
 * Return that maximum distance to the closest person.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: seats = [1,0,0,0,1,0,1]
 * Output: 2
 * Explanation:
 * If Alex sits in the second open seat (i.e. seats[2]), then the closest person has distance 2.
 * If Alex sits in any other open seat, the closest person has distance 1.
 * Thus, the maximum distance to the closest person is 2.
 * Example 2:
 * <p>
 * Input: seats = [1,0,0,0]
 * Output: 3
 * Explanation:
 * If Alex sits in the last seat (i.e. seats[3]), the closest person is 3 seats away.
 * This is the maximum distance possible, so the answer is 3.
 * Example 3:
 * <p>
 * Input: seats = [0,1]
 * Output: 1
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 2 <= seats.length <= 2 * 10^4
 * seats[i] is 0 or 1.
 * At least one seat is empty.
 * At least one seat is occupied.
 */
public class MaximizeDistanceToClosestPerson {
    static class HandlingBoundaryConditions {
        // Boundary conditions - leftmost seat, rightmost seat and seat in the middle
        public int maxDistToClosest(int[] seats) {
            int maxDistance = 0;
            int i = 0;
            // left most seat distance
            while (seats[i] == 0) {
                maxDistance++;
                i++;
            }

            //check for distance between any two occupied seats
            int currDistance = 0;
            while (i < seats.length) {
                currDistance++;
                // found the person on seat - check max distance and reset curr
                if (seats[i] == 1) {
                    maxDistance = Math.max(maxDistance, currDistance/2);
                    currDistance = 0;
                }
                i++;
            }

            // right most seat distance
            maxDistance = Math.max(maxDistance, currDistance);

            return maxDistance;
        }

    }

    static class UsingReflections {
        public int maxDistToClosest(int[] seats) {
            if (seats == null || seats.length == 0) return 0;
            int cur0 = 0;
            int res = 0;

            // Make two reflections to remove boundaries
            // For example
            // 0 0 1 0 1 0
            // becomes
            // 0 0 1 0 1 0 | 0 1 0 1 0 0 | 0 0 1 0 1 0
            for (int j = 0; j < 3; j++) {
                for (int i = 0; i < seats.length; i++) {
                    int seat = j == 1 ? seats[seats.length - 1 - i] : seats[i];
                    if (seat == 0) res = Math.max(res, ++cur0);
                    else cur0 = 0;
                }
            }
            return (res + 1) / 2;
        }
    }


}
