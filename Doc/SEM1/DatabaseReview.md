# 数据库复习
出于简便，中英混合书写(专有名词英语， 按授课顺序。

## 1 preliminary
数据库与普通文件管理的区别（数据库的motivations）？
数据库是什么？DBMS 是什么 DBMS 与 DB的关系？
DBMS 与其他 programming systems 的 区别？

schema和instance 的区别
data model 是什么 举例说明有那种？
什么是data independence, 又哪两种？两种的区别是什么？ 其意义是什么？
为什么数据库采用3层schema（好处？
DDL， DML 是什么
DBA是什么
简述数据库系统结构 （system architecture）

## 2 ER-model
什么是entity 与 entity set？
什么是 attribute、 domain？
什么是 relationship 与 relationship set
辨析relationship 与 attribute、entity set 与 attribute
映射基数 记法
辨析superkey, key, primary key candidate key， partial key
ER图有primary key?
什么是weak entity set, 
什么是 total participation、 existence dependency
何时可以 migrate attribute of a relation, how?
多元关系的必要性， 可否转化为Binary relations？ how?
n-nary relationship 与 n binary关系等价吗？
NULL值有什么含义， 有什么用？
什么是 relationship 的 degree?
1:1, 1:M, M:N 到relation model的映射

## SQL
NULL值运算的特性？ 如何在聚集函数中避免？

## 关系代数   
select, project 有何特性，对应SQL中的什么？
何为Union capatibility? 有何用？
natural join equijoin 区别
什么是 ralational complete？


# normalization
什么是 Integrity constraints(ICS), 有什么用
解释如下名词 key constraints, domain/entity ~, referential integrity ~
ICs 何时可能违反？ 具体那些操作会影响哪些。

何为 函数依赖（Fds)? 何为 trivial fds
armstrong axiom 包含哪些， 有哪些拓展律
何为 closure 与 closure set
FDs 等价的条件？
解释 non-additive/lossless join, Preservation of the functional dependencies.哪个更重要
解释一二三BCNF范式
三个Anomalies 的缺点
如何避免spurious tuples
1NF缺点（三个Anomalies，何时会有？
2NF缺点（三个Anomalies，何时会有？
BCNF 与3NF 区别
BCNF分解（二元的无损分解



CourseRmAlloc(A, B, C, D, E, F, 
                G, H, I)	

  F = {A -> B,         B -> A,
	A, C -> D,     A, C -> E,
	F -> G,         F, C, H, I -> A,
	A, C, H, I -> F }

a) Find all the candidate keys of CourseRmAlloc. Demonstrate that they are indeed candidate keys.
ACHI FCHI CHIB

b) Determine the highest normal form that the relation CourseRmAlloc is in, and justify your answer. What problems will arise with this relation?

c) Considering the following decomposition, Give all the candidate keys for the relations Course and RoomAlloc. State what normal form each relation is in.

         Course(A, B, C, D, E)
         RoomAlloc(F, G, H, I, A)
