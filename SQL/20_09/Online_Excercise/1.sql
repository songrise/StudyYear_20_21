-- practice


-- 1.查询" 01 "课程比" 02 "课程成绩高的学生的信息及课程分数

SELECT * FROM (SELECT * FROM SC WHERE SC.CID = '01') AS A,
            (SELECT * FROM SC WHERE SC.CID = '02') AS B
            WHERE A.SID = B.SID AND A.SCORE > B.SCORE;


-- 查询同时存在" 01 "课程和" 02 "课程的情况
SELECT * FROM (SELECT * FROM SC WHERE SC.CID = '01') AS A,
            (SELECT * FROM SC WHERE SC.CID = '02') AS B
            WHERE A.SID = B.SID;
