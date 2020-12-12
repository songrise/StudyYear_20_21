"""
author:魏振东
data：2019.09.19
func:求一段文本中的字频
"""
import re  # 正则表达式库
from collections import Counter


# #
# # #去除中文和数字只保留字母
# result={k:v for k,v in result.items() if k.isalpha()}
# #
# # #排序输出
# for k in sorted(result,key=result.__getitem__,reverse=True):
#     print(k,result[k])

# 对每个字符进行统计
result = dict(Counter("the objectives of this subject are to introduce students to the concepts and applications of discrete mathematical structures and help students attain the fundamental mathematical knowledge and reasoning skills they need to be successful in upper-level computing subjects"))

# 去除中文和数字只保留字母
result = {k: v for k, v in result.items() if k.isalpha()
          and u'\u4e00' > k or k > u'\u9fff'}

# 输出检查
# print(result)

# 排序输出
cnt = 0
for k in sorted(result, key=result.__getitem__, reverse=True):
    cnt += result[k]

for k in sorted(result, key=result.__getitem__, reverse=True):
    print(k, result[k], result[k]/cnt)

0.004291845493562232
0.008583690987124463
