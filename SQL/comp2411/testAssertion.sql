drop table test;
drop table valid;
DROP TRIGGER TRIGGER_TEST;

create table test(
    i numeric(1,0),
    a numeric(1,0),
    b numeric(1,0),
    primary key (i)
);

CREATE TABLE VALID(
    i numeric(1,0),
    v numeric(1,0),
    foreign key (i) references test
);

CREATE OR REPLACE TRIGGER TRIGGER_TEST 
AFTER INSERT OR UPDATE OF A,B ON test
FOR EACH ROW 
BEGIN
        IF :NEW.A IS NOT NULL AND :NEW.B IS NOT NULL THEN
            UPDATE VALID
            SET V = 1
            WHERE I = :NEW.I;
        END IF;
END;
/

CREATE OR REPLACE TRIGGER TRIGGER_TEST 
AFTER INSERT ON test
FOR EACH ROW 
    BEGIN
        IF :NEW.A IS NOT NULL AND :NEW.B IS NOT NULL THEN
            UPDATE VALID
            SET V = 1
            WHERE I = :NEW.I;
        END IF;
END;
/

insert into valid values(null,0);
SELECT * FROM VALID; 
insert into test values(0,1,NULL);
UPDATE TEST SET B = 2 WHERE I = 0;

CREATE OR REPLACE TRIGGER TRIGGER_TEST 
BEFORE UPDATE OF VALID ON test
FOR EACH ROW 
BEGIN   
    RAISE_APPLICATION_ERROR(-20001, 'Invalid operaiton!');
END;

CREATE OR REPLACE
-- !

create table STUDENT   ---创建student表
(
  id        NUMBER(19), --id
  stu_no    VARCHAR2(20), --学号
  stu_name  VARCHAR2(32), --姓名
  stu_age   NUMBER,  --年龄
  stu_major VARCHAR2(32) --专业
);


create table STU_LOG   ---创建stu_log表，用于记录对student表的操作日志
(
  log_id     NUMBER,  --日志id
  log_action VARCHAR2(100),  --操作名称
  log_date   DATE,  --操作时间
  log_message   VARCHAR2(32) --
);

CREATE SEQUENCE seq_test 
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

-- 创建触发器：实现id的隐式自增
create or replace trigger modify_stu 
before insert on student
for each row
declare
next_id number;
begin
  select seq_test.nextval into next_id from dual;
  :new.id :=next_id;
end;

create or replace trigger modify_stu
after insert or delete or update of stu_name
on student
for each row
  begin 
    if inserting then
      insert into stu_log values(1,'insert',sysdate,:new.stu_name);
    elsif deleting then
       insert into stu_log values(2,'delete',sysdate,:old.stu_name);
    elsif updating then
      insert into stu_log values(3,'update_old',sysdate,:old.stu_name);
      insert into stu_log values(4,'update_new',sysdate,:new.stu_name);
     end if;
end;


create or replace trigger modify_stu
before insert or update or delete on student
begin
   if deleting then
     raise_application_error(-20001,'该表不允许删除数据');
   elsif updating then
     raise_application_error(-20002,'该表不允许修改数据');
    elsif inserting then
     raise_application_error(-20003,'该表不允许插入数据');
    end if;
end;