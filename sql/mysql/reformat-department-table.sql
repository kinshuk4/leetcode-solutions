/*

https://leetcode.com/problems/reformat-department-table/
 1179. Reformat Department Table
Easy
SQL Schema>
Table: Department

+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| id            | int     |
| revenue       | int     |
| month         | varchar |
+---------------+---------+
(id, month) is the primary key of this table.
The table has information about the revenue of each department per month.
The month has values in ["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"].


Write an SQL query to reformat the table such that there is a department id column and a revenue column for each month.

The query result format is in the following example:

Department table:
+------+---------+-------+
| id   | revenue | month |
+------+---------+-------+
| 1    | 8000    | Jan   |
| 2    | 9000    | Jan   |
| 3    | 10000   | Feb   |
| 1    | 7000    | Feb   |
| 1    | 6000    | Mar   |
+------+---------+-------+

Result table:
+------+-------------+-------------+-------------+-----+-------------+
| id   | Jan_Revenue | Feb_Revenue | Mar_Revenue | ... | Dec_Revenue |
+------+-------------+-------------+-------------+-----+-------------+
| 1    | 8000        | 7000        | 6000        | ... | null        |
| 2    | 9000        | null        | null        | ... | null        |
| 3    | null        | 10000       | null        | ... | null        |
+------+-------------+-------------+-------------+-----+-------------+

Note that the result table has 13 columns (1 for the department id + 12 for the months).
*/
-- Using Pivot table
SELECT Id
     , [Jan] Jan_Revenue
     , [Feb] Feb_Revenue
     , [Mar] Mar_Revenue
     , [Apr] Apr_Revenue
     , [May] May_Revenue
     , [Jun] Jun_Revenue
     , [Jul] Jul_Revenue
     , [Aug] Aug_Revenue
     , [Sep] Sep_Revenue
     , [Oct] Oct_Revenue
     , [Nov] Nov_Revenue
     , [Dec] Dec_Revenue
FROM Department PIVOT
         (
          SUM(Revenue)
    FOR MONTH
    IN ([Jan], [Feb],[Mar],[Apr],[May],[Jun],[Jul],[Aug],[Sep],[Oct], [Nov], [DEC])
             ) AS Pivot_Table


SELECT id,
       sum(CASE WHEN month = 'Jan' THEN revenue ELSE NULL END) AS Jan_Revenue,
       sum(CASE WHEN month = 'Feb' THEN revenue ELSE NULL END) AS Feb_Revenue,
       sum(CASE WHEN month = 'Mar' THEN revenue ELSE NULL END) AS Mar_Revenue,
       sum(CASE WHEN month = 'Apr' THEN revenue ELSE NULL END) AS Apr_Revenue,
       sum(CASE WHEN month = 'May' THEN revenue ELSE NULL END) AS May_Revenue,
       sum(CASE WHEN month = 'Jun' THEN revenue ELSE NULL END) AS Jun_Revenue,
       sum(CASE WHEN month = 'Jul' THEN revenue ELSE NULL END) AS Jul_Revenue,
       sum(CASE WHEN month = 'Aug' THEN revenue ELSE NULL END) AS Aug_Revenue,
       sum(CASE WHEN month = 'Sep' THEN revenue ELSE NULL END) AS Sep_Revenue,
       sum(CASE WHEN month = 'Oct' THEN revenue ELSE NULL END) AS Oct_Revenue,
       sum(CASE WHEN month = 'Nov' THEN revenue ELSE NULL END) AS Nov_Revenue,
       sum(CASE WHEN month = 'Dec' THEN revenue ELSE NULL END) AS Dec_Revenue
FROM department
GROUP BY id
ORDER BY id;

--- Using Group by
select id,
       sum(case month when 'Jan' then revenue else Null end) as Jan_Revenue,
       sum(case month when 'Feb' then revenue else Null end) as Feb_Revenue,
       sum(case month when 'Mar' then revenue else Null end) as Mar_Revenue,
       sum(case month when 'Apr' then revenue else Null end) as Apr_Revenue,
       sum(case month when 'May' then revenue else Null end) as May_Revenue,
       sum(case month when 'Jun' then revenue else Null end) as Jun_Revenue,
       sum(case month when 'Jul' then revenue else Null end) as Jul_Revenue,
       sum(case month when 'Aug' then revenue else Null end) as Aug_Revenue,
       sum(case month when 'Sep' then revenue else Null end) as Sep_Revenue,
       sum(case month when 'Oct' then revenue else Null end) as Oct_Revenue,
       sum(case month when 'Nov' then revenue else Null end) as Nov_Revenue,
       sum(case month when 'Dec' then revenue else Null end) as Dec_Revenue
from Department
group by id;
