package com.vaani.leetcode.design;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * https://leetcode.com/problems/moving-average-from-data-stream/
 * Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.
 * For example,
 * MovingAverage m = new MovingAverage(3);
 * m.next(1) = 1
 * m.next(10) = (1 + 10) / 2
 * m.next(3) = (1 + 10 + 3) / 3
 * m.next(5) = (10 + 3 + 5) / 3
 */
public class MovingAverageFromDataStream {
	static class UsingQueue {
		public static class MovingAverage {

			LinkedList<Integer> queue;
			int size;
			int sum;

			/**
			 * Initialize your data structure here.
			 */
			public MovingAverage(int size) {
				this.queue = new LinkedList<Integer>();
				this.size = size;
				this.sum = 0;
			}

			public double next(int val) {
				queue.offer(val);
				if (queue.size() > this.size) {
					sum -= queue.poll();
				}
				sum += val;
				return (double) sum / queue.size();
			}
		}
	}

	static class UsingDeque {
		static class MovingAverage {
			int size, windowSum = 0, count = 0;
			Deque<Integer> queue = new ArrayDeque<>();

			public MovingAverage(int size) {
				this.size = size;
			}

			public double next(int val) {
				count++;
				// calculate the new sum by shifting the window
				queue.add(val);
				int head = count > size ? (int) queue.poll() : 0;

				windowSum = windowSum - head + val;

				return windowSum * 1.0 / Math.min(size, count);
			}
		}
	}

	static class UsingArray {
		public static class MovingAverage {
			private int[] window;
			private int n, insert;
			private long sum;

			/**
			 * Initialize your data structure here.
			 */
			public MovingAverage(int size) {
				window = new int[size];
				insert = 0;
				sum = 0;
			}

			public double next(int val) {
				if (n < window.length) n++;
				sum -= window[insert];
				sum += val;
				window[insert] = val;
				insert = (insert + 1) % window.length;

				return (double) sum / n;
			}
		}
	}

}
