# -*- coding : utf-8 -*-
# @FileName  : addWhiteSpace.py
# @Author    : Ruixiang JIANG (Songrise)
# @Time      : 2020-09-18
# @Github    ：https://github.com/songrise
# @Descriptions: append two white space to a file (.md).


with open("C:\\Users\\11385\\Documents\\GitHub\\StudyYear_20_21\\Doc\\Log.md", "r+", encoding="UTF-8") as i:
    with open("C:\\Users\\11385\\Documents\\GitHub\\StudyYear_20_21\\Doc\\temp_Log.md", "r+", encoding="UTF-8") as o:
        while True:
            line = i.readline()
            if not line:
                break
            end = line.index('\n')
            line = line[:end]+"  "+line[end:]

            o.writelines(line)
