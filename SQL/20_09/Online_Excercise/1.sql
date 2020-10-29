-- practice


-- 1.查询" 01 "课程比" 02 "课程成绩高的学生的信息及课程分数

SELECT * FROM (SELECT * FROM SC WHERE SC.CID = '01') AS A,
            (SELECT * FROM SC WHERE SC.CID = '02') AS B
            WHERE A.SID = B.SID AND A.SCORE > B.SCORE;


SELECT B.* 
FROM SC A, SC B
WHERE A.CID = '01' AND B.CID = '02' AND A.SID = B.SID AND B.SCORE > A.SCORE;

-- 查询同时存在" 01 "课程和" 02 "课程的情况
SELECT * FROM (SELECT * FROM SC WHERE SC.CID = '01') AS A,
            (SELECT * FROM SC WHERE SC.CID = '02') AS B
            WHERE A.SID = B.SID;

-- 1.2 查询存在" 01 "课程但可能不存在" 02 "课程的情况(不存在时显示为 null )
