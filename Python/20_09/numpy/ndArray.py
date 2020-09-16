# -*- coding : utf-8 -*-
# @FileName  : ndArray.py
# @Author    : Ruixiang JIANG (Songrise)
# @Time      : 2020-09-15
# @Github    ：https://github.com/songrise
# @Descriptions: Learn numpy ndarray basic


import numpy as np

a = np.array([[11, 12, 13, 14, 15],
              [16, 17, 18, 19, 20],
              [21, 22, 23, 24, 25],
              [26, 27, 28, 29, 30],
              [31, 32, 33, 34, 35]])

print(type(a))  # >>><class 'numpy.ndarray'>
print(a.dtype)  # >>>int64
print(a.size)  # >>>25
print(a.shape)  # >>>(5, 5)
print(a.itemsize)  # >>>8
print(a.ndim)  # >>>2
print(a.nbytes)

b = np.arange(25)
b = b.reshape((5, 5))
print(b)
print(a+b)
print(a > b)
print("a.dot(b)", a.dot(b))
print("a*b", a*b)
print("a@b", a@b)  # matrix multiplication


###########
print("#Section 2#")
a = np.arange(10)
print(a[::-1])  # reversed a

print(a.sum())  # >>>45
print(a.min())  # >>>0
print(a.max())  # >>>9
print(a.cumsum())  # >>>[ 0  1  3  6 10 15 21 28 36 45] prefix sum

##############

print("part3")


def f(x, y):
    return 10*x+y


b = np.fromfunction(f, (5, 4), dtype=int)
print(b)
# array([[0,  1,  2,  3],
#        [10, 11, 12, 13],
#        [20, 21, 22, 23],
#        [30, 31, 32, 33],
#        [40, 41, 42, 43]])

print(b[2, 3])
# 23
print(b[0:5, 1])                       # each row in the second column of b
# array([1, 11, 21, 31, 41])
print(b[:, 1])                        # equivalent to the previous example
# array([1, 11, 21, 31, 41])
# each column in the second and third row of b
print(b[1:3, :])
# array([[10, 11, 12, 13],
#        [20, 21, 22, 23]])
