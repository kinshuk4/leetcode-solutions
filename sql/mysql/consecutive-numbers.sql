/*
https://leetcode.com/problems/consecutive-numbers/
180. Consecutive Numbers
Medium
Write a SQL query to find all numbers that appear at least three times consecutively.
Sql Schema>
Create table If Not Exists Logs (Id int, Num int)
Truncate table Logs
insert into Logs (Id, Num) values ('1', '1')
insert into Logs (Id, Num) values ('2', '1')
insert into Logs (Id, Num) values ('3', '1')
insert into Logs (Id, Num) values ('4', '2')
insert into Logs (Id, Num) values ('5', '1')
insert into Logs (Id, Num) values ('6', '2')
insert into Logs (Id, Num) values ('7', '2')
------------------------------------------------------

+----+-----+
| Id | Num |
+----+-----+
| 1  |  1  |
| 2  |  1  |
| 3  |  1  |
| 4  |  2  |
| 5  |  1  |
| 6  |  2  |
| 7  |  2  |
+----+-----+
For example, given the above Logs table, 1 is the only number that appears consecutively for at least three times.
+-----------------+
| ConsecutiveNums |
+-----------------+
| 1               |
+-----------------+
*/
/*
# Time:  O(n)
# Space: O(n)
# Solution 1
# Write your MySQL query statement below*/
SELECT DISTINCT(Num) AS ConsecutiveNums
FROM (
    SELECT
    Num,
    @counter := IF(@prev = Num, @counter + 1, 1) AS how_many_cnt_in_a_row,
    @prev := Num
    FROM Logs y, (SELECT @counter:=1, @prev:=NULL) vars
) sq
WHERE how_many_cnt_in_a_row >= 3;

-- Solution 2
Select DISTINCT l1.Num as ConsecutiveNums
from Logs l1, Logs l2, Logs l3
where l1.Id=l2.Id-1
  and l2.Id=l3.Id-1
  and l1.Num=l2.Num
  and l2.Num=l3.Num
;
