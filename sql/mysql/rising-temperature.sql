/*
https://leetcode.com/problems/rising-temperature/
197. Rising Temperature
Easy
Given a Weather table, write a SQL query to find all dates' Ids with higher temperature compared to its previous (yesterday's) dates.

+---------+------------------+------------------+
| Id(INT) | RecordDate(DATE) | Temperature(INT) |
+---------+------------------+------------------+
|       1 |       2015-01-01 |               10 |
|       2 |       2015-01-02 |               25 |
|       3 |       2015-01-03 |               20 |
|       4 |       2015-01-04 |               30 |
+---------+------------------+------------------+
For example, return the following Ids for the above Weather table:
+----+
| Id |
+----+
|  2 |
|  4 |
+----+
*/


/*
 # Time:  O(n^2)
# Space: O(n)
 */
SELECT wt1.Id
FROM Weather wt1, Weather wt2
WHERE wt1.Temperature > wt2.Temperature AND
      TO_DAYS(wt1.RecordDate)-TO_DAYS(wt2.RecordDate)=1;


-- OR
select b.Id
from Weather a, Weather b
where (TO_DAYS(b.RecordDate) - TO_DAYS(a.RecordDate)) = 1
  and b.Temperature > a.Temperature
;