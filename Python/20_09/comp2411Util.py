# -*- coding : utf-8 -*-
# @FileName  : comp2411Util.py
# @Author    : Ruixiang JIANG (Songrise)
# @Time      : 2020-10-23
# @Github    ：https://github.com/songrise
# @Descriptions: None

index = 1
for i in range(1, 6):
    inst_id = "I000" + str(i)
    for j in range(4):
        print("INSERT INTO PROPOSE VALUES ('{}',{:0>5d});".format(inst_id, index))
        index += 1


for i in range(19):
    stu_id = ("S000" if len(str(i+1)) == 1 else "S00") + str(1+i)

    proj_id = [(j-1)*4 + (i % 4) + 1 for j in range(1, 6)]

    print("INSERT INTO PREFER VALUES ('{}',{:0>5d},{:0>5d},{:0>5d},{:0>5d},{:0>5d});".format(
        stu_id, *proj_id))
