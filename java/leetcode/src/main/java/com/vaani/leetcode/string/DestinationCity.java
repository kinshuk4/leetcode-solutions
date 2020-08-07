package com.vaani.leetcode.string;

import java.util.*;

/**
 * https://leetcode.com/problems/destination-city/
 * 1436. Destination City
 * Easy
 * <p>
 * You are given the array paths, where paths[i] = [cityAi, cityBi] means there exists a direct path going from cityAi to cityBi. Return the destination city, that is, the city without any path outgoing to another city.
 * <p>
 * It is guaranteed that the graph of paths forms a line without any loop, therefore, there will be exactly one destination city.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: paths = [["London","New York"],["New York","Lima"],["Lima","Sao Paulo"]]
 * Output: "Sao Paulo"
 * Explanation: Starting at "London" city you will reach "Sao Paulo" city which is the destination city. Your trip consist of: "London" -> "New York" -> "Lima" -> "Sao Paulo".
 * <p>
 * Example 2:
 * <p>
 * Input: paths = [["B","C"],["D","B"],["C","A"]]
 * Output: "A"
 * Explanation: All possible trips are:
 * "D" -> "B" -> "C" -> "A".
 * "B" -> "C" -> "A".
 * "C" -> "A".
 * "A".
 * Clearly the destination city is "A".
 * <p>
 * Example 3:
 * <p>
 * Input: paths = [["A","Z"]]
 * Output: "Z"
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= paths.length <= 100
 * paths[i].length == 2
 * 1 <= cityAi.length, cityBi.length <= 10
 * cityAi != cityBi
 * All strings consist of lowercase and uppercase English letters and the space character.
 */
public class DestinationCity {
    static class UsingMap {
        public String destCity(List<List<String>> paths) {
            if (paths == null || paths.size() == 0) {
                return "";
            }
            Map<String, String> fromSet = new HashMap<>();
            for (List<String> path : paths) {
                fromSet.put(path.get(0), path.get(1));
            }
            for (String city : fromSet.values()) {
                if (!fromSet.containsKey(city)) {
                    return city;
                }
            }
            return "";
        }
    }

    static class UsingSet {
        public String destCity(List<List<String>> paths) {
            Set<String> set = new HashSet<>();
            // to Route
            paths.forEach(route -> set.add(route.get(1)));

            // remove fromRoute
            paths.forEach(route -> set.remove(route.get(0)));

            return set.iterator().next();
        }

        // submitted
        public String destCity2(List<List<String>> paths) {
            Set<String> set = new HashSet<>();
            // from Route
            paths.forEach(route -> set.add(route.get(0)));

            for (var route : paths) {
                if(!set.contains(route.get(1))){
                    return route.get(1);
                }
            }
            return null;
        }


    }

}
