package com.vaani.leetcode.greedy;

import com.vaani.dsa.ds.core.visual.Interval;

import java.util.Arrays;
import java.util.PriorityQueue;

/** https://leetcode.com/problems/meeting-rooms-ii/
 * Given an array of meeting time intervals consisting
 * of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference
 * rooms required.
 *
 * <p>For example, Given [[0, 30],[5, 10],[15, 20]], return 2.
 *
 * <p>Solution: Sort the array based on start-time of the interval. Then, use the min-com.vaani.leetcode.heap based on
 * min end time. For every interval remove the top element of the priority queue if the end time of
 * the top <= start time of the new interval. Add the new interval to the queue. The max size of the
 * priority queue attained during this process will be the answer.
 */
public class MeetingRooms2 {
    public static void main(String[] args) {
        Interval i1 = new Interval(0, 40);
        Interval i2 = new Interval(2, 10);
        Interval i3 = new Interval(10, 40);
        Interval i4 = new Interval(15, 20);
        Interval i5 = new Interval(20, 30);
        Interval i6 = new Interval(20, 40);
        Interval i7 = new Interval(1, 5);
        Interval[] intervals = {i1, i2, i3, i4, i5, i6, i7};
        System.out.println(minMeetingRooms(intervals));
    }

    public static int minMeetingRooms(Interval[] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a.start, b.start));// sort by start
        PriorityQueue<Interval> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a.end, b.end)); // minHeap by end
        int max = 0;
        for (Interval i : intervals) {
            while (!minHeap.isEmpty() && minHeap.peek().end <= i.start) {
                minHeap.poll();
            }
            minHeap.offer(i);
            max = Math.max(max, minHeap.size());
        }
        return max;
    }

    // simpler on eyes
    public static int minMeetingRooms2(Interval[] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a.start, b.start));// sort by start
        PriorityQueue<Interval> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a.end, b.end)); // minHeap by end

        minHeap.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            Interval curr = intervals[i];
            Interval earliest = minHeap.poll();

            if(curr.start >= earliest.end){
                earliest.end = curr.end; // earliest end time to current as there is no conflict
            }else {
                minHeap.add(curr);
            }
            minHeap.add(earliest);
        }

        return minHeap.size();
    }
}
