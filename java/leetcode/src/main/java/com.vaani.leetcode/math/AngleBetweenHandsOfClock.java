package com.vaani.leetcode.math;

/**
 * https://leetcode.com/problems/angle-between-hands-of-a-clock/
 * Given two numbers, hour and minutes. Return the smaller angle (in degrees) formed between the hour and the minute hand.
 * Input: hour = 12, minutes = 30
 * Output: 165
 */
public class AngleBetweenHandsOfClock {
    public double angleClock(int hour, int minutes) {
        double totalDegrees = 360;
        double numHours = 12;
        double numMinutes = 60;


        double degreePerMinute = totalDegrees / numMinutes;
        double degreePerHour = totalDegrees / numHours;
        double degreeChangeInHourPerMinute = degreePerHour / numMinutes;

        // when minute hands move, hour hand also moves
        hour = hour % 12;// 12 is treated as 0 only

        double minuteDegrees = degreePerMinute * minutes;
        double hourDegrees = hour * degreePerHour + minutes * degreeChangeInHourPerMinute;

        double diff = Math.abs(minuteDegrees - hourDegrees);

        return Math.min(diff, totalDegrees - diff);
    }
}
