package com.vaani.leetcode.depth_first_search;

import java.util.*;

/**
 * https://leetcode.com/problems/keys-and-rooms/
 * <p>
 * There are N rooms and you start in room 0.  Each room has a distinct number in 0, 1, 2, ..., N-1, and each room may have some keys to access the next room.
 * <p>
 * Formally, each room i has a list of keys rooms[i], and each key rooms[i][j] is an integer in [0, 1, ..., N-1] where N = rooms.length.  A key rooms[i][j] = v opens the room with number v.
 * <p>
 * Initially, all the rooms start locked (except for room 0).
 * <p>
 * You can walk back and forth between rooms freely.
 * <p>
 * Return true if and only if you can enter every room.
 */
//DFS
public class KeysAndRooms {

    static class DFS1 {
        public boolean canVisitAllRooms(List<List<Integer>> rooms) {
            int[] visit = new int[rooms.size()];
            visit[0] = 1;
            for (int i = 0; i < rooms.get(0).size(); i++) {
                canVisitAllRoomsHelper(rooms, rooms.get(0).get(i), visit);
            }

            return !Arrays.stream(visit).anyMatch(v -> v == 0);
        }

        private void canVisitAllRoomsHelper(List<List<Integer>> rooms, int curRoom, int[] visit) {
            if (visit[curRoom] == 1)
                return;
            visit[curRoom] = 1;
            for (int i = 0; i < rooms.get(curRoom).size(); i++) {
                canVisitAllRoomsHelper(rooms, rooms.get(curRoom).get(i), visit);
            }
        }
    }

    static class DFS2 {
        public boolean canVisitAllRooms(List<List<Integer>> rooms) {
            boolean[] visited = new boolean[rooms.size()];
            visited[0] = true;
            Stack<Integer> stack = new Stack<>();
            stack.push(0);

            //At the beginning, we have a to-do list "stack" of keys to use.
            //'visit' represents at some point we have entered this room.
            while (!stack.isEmpty()) {
                int nextKey = stack.pop(); // Get the next key
                for (int key : rooms.get(nextKey)) // For every key in room # 'nextKey'...
                    if (!visited[key]) { // ...that hasn't been used yet
                        visited[key] = true; // mark that we've entered the room
                        stack.push(key); // add the key to the to-do list
                    }
            }

            for (boolean v : visited) {  // if any room hasn't been visited, return false
                if (!v) {
                    return false;
                }
            }

            return true;
        }


    }

    static class DFS3 {
        // same as DFS2 but using Set for visited and saving last for loop
        public boolean canVisitAllRooms(List<List<Integer>> rooms) {
            Set<Integer> visited = new HashSet<>();
            visited.add(0);
            Stack<Integer> stack = new Stack<>();
            stack.push(0);

            //At the beginning, we have a to-do list "stack" of keys to use.
            //'visit' represents at some point we have entered this room.
            while (!stack.isEmpty()) {
                int nextKey = stack.pop(); // Get the next key
                for (int key : rooms.get(nextKey)) // For every key in room # 'nextKey'...
                    if (!visited.contains(key)) { // ...that hasn't been used yet
                        visited.add(key); // mark that we've entered the room
                        stack.push(key); // add the key to the to-do list
                    }
            }

            return visited.size() == rooms.size();
        }


    }

    static class BFS1 {
        public static boolean canVisitAllRooms(List<List<Integer>> rooms) {
            Queue<Integer> roomToVisit = new LinkedList<Integer>();
            boolean[] visited = new boolean[rooms.size()];
            int numVisited = 0;
            roomToVisit.offer(0);
            visited[0] = true;
            while (!roomToVisit.isEmpty()) {
                int cur = roomToVisit.poll();
                numVisited++;
                for (int i = 0; i < rooms.get(cur).size(); i++) {
                    int nextRoom = rooms.get(cur).get(i);
                    if (!visited[nextRoom]) {
                        roomToVisit.offer(nextRoom);
                        visited[nextRoom] = true;
                    }
                }
            }
            return numVisited == rooms.size();
        }
    }

}
