--  Find all the manager (in any department), and all the clerks in department 10.
select ename 
from emp
where job = 'MANAGER'
or (job = 'CLERK' and deptno = 10);


-- Create a new table MY_EMP from existing table EMP.
create table MY_EMP as
select * from emp;

-- Update the salary of the President in the MY_EMP table.

update MY_EMP
set sal = sal*1.1
where job = 'PRESIDENT';

-- Delete the tuples in MY_EMP where the employee’s JOB is CLERK.
delete from my_emp
where job ='CLERK';