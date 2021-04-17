def DA(A: int, B: int, X: int):
    return X ^ (not B)


def DB(A: int, B: int, X: int):
    return A or X


def Z(A: int, B: int, X: int):
    return A ^ B


A_and_B = [[0, 0], [0, 1], [1, 0], [1, 1]]


def state(s):
    if s == "00":
        return "S0"
    if s == "01":
        return "S1"
    if s == "10":
        return "S2"
    if s == "11":
        return "S3"


for i, v in enumerate(A_and_B):
    s1 = state(str(DA(*v, 0))+str(DB(*v, 0)))
    s2 = state(str(DA(*v, 1))+str(DB(*v, 1)))
    z1 = (str(Z(*v, 0)))
    z2 =(str(Z(*v, 1)))
    print("{} {} {} {}".format(s1, s2, z1, z2))
