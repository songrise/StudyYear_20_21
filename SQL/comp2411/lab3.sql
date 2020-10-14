--  Selecting Summary Information From One Group
SELECT AVG(SAL)
FROM EMP
WHERE JOB = 'CLERK';

-- You can use group functions in arithmetic expressions
SELECT AVG(SAL+COMM)*12
FROM EMP
WHERE JOB = 'CLERK';

-- Find the highest and lowest paid employee salaries and the difference between them
SELECT MAX(SAL),MIN(SAL),MAX(SAL)-MIN(SAL)
FROM EMP;

SELECT COUNT(*),DEPTNO
    FROM DEPT 
    GROUP BY DEPTNO; 

-- Find the number of characters in the longest department name (group-5).
SELECT MAX(LENGTH(DNAME))
FROM DEPT;

-- Find the name and salary of the employee (or employees) who receive the maximum SALARY
SELECT ENAME,SAL
FROM EMP
WHERE SAL = (SELECT MAX(SAL) 
                FROM EMP);

-- Count the number of different jobs held by employees in department 30
SELECT COUNT(DISTINCT JOB)
FROM EMP
WHERE DEPTNO = 30;
-- Divide all employees into groups by department, and by jobs within department.
-- Count the employees in each group and compute each group’s average annual salary.
SELECT COUNT(*),AVG(SAL)
FROM EMP
GROUP BY JOB,DEPTNO;
-- Issue the same query as above except list the department name rather than the
-- department number
SELECT DNAME, JOB, COUNT(*), AVG(SAL)*12
FROM EMP, DEPT
WHERE DEPT.DEPTNO = EMP.DEPTNO
GROUP BY DNAME, JOB;

SELECT DNAME, JOB, COUNT(*), AVG(SAL)*12
FROM EMP JOIN DEPT USING (DEPTNO)
GROUP BY DNAME, JOB;

-- List the average annual salary for all job groups having more than 2 employees in the
-- group.
SELECT JOB, COUNT(*), AVG(SAL)*12
FROM EMP
GROUP BY JOB
HAVING COUNT(*)>2;

-- List all the departments that have at least two clerks. (group-15)
SELECT DEPTNO
FROM EMP
WHERE JOB = 'CLERK'
GROUP BY DEPTNO
HAVING COUNT(*)>=2;

-- List the job groups that have an average salary greater than the average salary of
-- managers. (group-17)

SELECT JOB,AVG(SAL)
FROM EMP
GROUP BY JOB
HAVING AVG(SAL)>
(SELECT AVG(SAL)
FROM EMP
WHERE JOB = 'MANAGER');

-- Count the number of people in department 30 who receive a salary and the number of
-- people who receive a commission. (group-18)
SELECT COUNT(SAL), COUNT(COMM)
FROM EMP
WHERE DEPTNO = 30;


-- Changing Column Headings
COLUMN ENAME HEADING 'EMPLOYEE | NAME'
RUN