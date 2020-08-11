/*
https://leetcode.com/problems/rank-scores/
178. Rank Scores
Medium
SQL Schema>
Create table If Not Exists Scores (Id int, Score DECIMAL(3,2))
Truncate table Scores
insert into Scores (Id, Score) values ('1', '3.5')
insert into Scores (Id, Score) values ('2', '3.65')
insert into Scores (Id, Score) values ('3', '4.0')
insert into Scores (Id, Score) values ('4', '3.85')
insert into Scores (Id, Score) values ('5', '4.0')
insert into Scores (Id, Score) values ('6', '3.65')
---------------------------------------------------------------

Write a SQL query to rank scores. If there is a tie between two scores, both should have the same ranking.
Note that after a tie, the next ranking number should be the next consecutive integer value. In other words, there should be no "holes" between ranks.

+----+-------+
| Id | Score |
+----+-------+
| 1  | 3.50  |
| 2  | 3.65  |
| 3  | 4.00  |
| 4  | 3.85  |
| 5  | 4.00  |
| 6  | 3.65  |
+----+-------+
For example, given the above Scores table, your query should generate the following report (order by highest score):

+-------+------+
| Score | Rank |
+-------+------+
| 4.00  | 1    |
| 4.00  | 1    |
| 3.85  | 2    |
| 3.65  | 3    |
| 3.65  | 3    |
| 3.50  | 4    |
+-------+------+

# Time:  O(n^2)
# Space: O(n)
*/
SELECT Ranks.Score, Ranks.Rank FROM Scores LEFT JOIN
       ( SELECT r.Score, @curRow := @curRow + 1  Rank 
            FROM (SELECT DISTINCT(Score), (SELECT @curRow := 0) 
                      FROM Scores ORDER by Score DESC) r
       ) Ranks 
       ON Scores.Score = Ranks.Score
       ORDER by Score DESC


/* Time:  O(n^3)
   Space: O(n) */
 SELECT Score,  (SELECT COUNT(DISTINCT(Score)) FROM  Scores b WHERE b.Score > a.Score) + 1 AS 'Rank'
       FROM Scores a
       ORDER by Score DESC;
-- OR - Note Rank with single quotes as without single quotes, mysql new versions will crib
SELECT a.Score ,
       (select count(DISTINCT b.Score) from scores b where b.Score >= a.Score) as 'Rank'
from scores a
ORDER BY a.Score desc
;

-- submitted - - Note Rank with single quotes as without single quotes, mysql new versions will crib
SELECT Score, DENSE_RANK() OVER(ORDER BY Score DESC) AS 'Rank'
FROM Scores
ORDER BY Score Desc