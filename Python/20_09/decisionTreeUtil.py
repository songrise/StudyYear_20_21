# -*- coding : utf-8 -*-
# @FileName  : decisionTreeUtil.py
# @Author    : Ruixiang JIANG (Songrise)
# @Time      : 2020-09-21
# @Github    ：https://github.com/songrise
# @Descriptions: Some useful tool for decision tree (ID3/C4.5)


import math
import numpy as np


class DTUtill:

    @classmethod
    def Information(cls, p: int, n: int) -> float:
        """
        calculate the information of two given set size.
        return an floating number.
        """
        if p == 0 and n == 0:
            raise ZeroDivisionError
        elif p == 0 or n == 0:
            return 0
        else:
            return (-(p/(p+n))*math.log(p/(p+n), 2)) - ((n/(p+n))*math.log(n/(p+n), 2))

    @classmethod
    def Entropy(cls, attr: list) -> float:
        """
        calculate the information entropy,
        attr is a 2-D array.
        """
        rol = len(attr)
        e = 0
        sumAll = np.sum(attr)
        col = len(attr[0])  # should be 2

        for row in attr:
            e += (sum(row)/sumAll) * cls.Information(*row)
        return e

    @classmethod
    def Gain(cls, attr: list) -> float:
        """
        calculate the information gain,
        attr is a 2-D array.
        """
        p = sum([row[0] for row in attr])
        n = sum([row[1] for row in attr])

        return cls.Information(p, n) - cls.Entropy(attr)


if __name__ == "__main__":

    print(DTUtill.Information(9, 5))
    print(DTUtill.Entropy([[2, 3], [4, 0], [3, 2]]))
    print(DTUtill.Gain([[2, 3], [4, 0], [3, 2]]))
