package com.vaani.leetcode.hashing;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/employee-importance/
 * 690. Employee Importance
 * Easy
 * <p>
 * You are given a data structure of employee information, which includes the employee's unique id, their importance value and their direct subordinates' id.
 * <p>
 * For example, employee 1 is the leader of employee 2, and employee 2 is the leader of employee 3. They have importance value 15, 10 and 5, respectively. Then employee 1 has a data structure like [1, 15, [2]], and employee 2 has [2, 10, [3]], and employee 3 has [3, 5, []]. Note that although employee 3 is also a subordinate of employee 1, the relationship is not direct.
 * <p>
 * Now given the employee information of a company, and an employee id, you need to return the total importance value of this employee and all their subordinates.
 * <p>
 * Example 1:
 * <p>
 * Input: [[1, 5, [2, 3]], [2, 3, []], [3, 3, []]], 1
 * Output: 11
 * Explanation:
 * Employee 1 has importance value 5, and he has two direct subordinates: employee 2 and employee 3. They both have importance value 3. So the total importance value of employee 1 is 5 + 3 + 3 = 11.
 * <p>
 * <p>
 * <p>
 * Note:
 * <p>
 * One employee has at most one direct leader and may have several subordinates.
 * The maximum number of employees won't exceed 2000.
 */
public class EmployeeImportance {
    static class Employee {
        // It's the unique id of each node;
        // unique id of this employee
        public int id;
        // the importance value of this employee
        public int importance;
        // the id of direct subordinates
        public List<Integer> subordinates;
    }


    // similar to graph
    public int getImportance(List<Employee> employees, int queryid) {
        Map<Integer, Employee> emap = employees.stream().collect(Collectors.toMap(employee -> employee.id, employee -> employee));
        return dfs(queryid, emap);
    }

    public int dfs(int eid, Map<Integer, Employee> emap) {
        Employee employee = emap.get(eid);
        int result = employee.importance;
        for (Integer subId : employee.subordinates) {
            result += dfs(subId, emap);
        }
        return result;
    }
}
