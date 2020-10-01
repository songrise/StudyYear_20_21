# -*- coding : utf-8 -*-
# @FileName  : collectionTest.py
# @Author    : Ruixiang JIANG (Songrise)
# @Time      : 2020-09-23
# @Github    ：https://github.com/songrise
# @Descriptions: test python collections

import collections

s = collections.deque()
s.append("a")
s.append("b")
s.append("c")
print(s.pop())
print(s)

c = collections(s)
print(c)
