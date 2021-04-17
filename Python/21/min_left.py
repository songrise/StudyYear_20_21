from itertools import combinations
stack = []
S = 800
x, y, z = [36], [49], [59]
# S = 775
# x, y, z = [26], [57], [62]
# S = 570
# x, y, z = [29], [39], [49]

min_ = 100000


def dfs(arr: list):
    global S, x, y, z
    # print(arr)
    if (S - sum(arr)) < min(x[0], y[0], z[0]):
        if (S - sum(arr)) == 2:
            print(arr)
        return S - sum(arr)  # remaining space
    c = dfs(arr+z) if S - sum(arr) >= z[0] else S - sum(arr)
    a = dfs(arr+x) if S - sum(arr) >= x[0] else S - sum(arr)
    b = dfs(arr+y) if S - sum(arr) >= y[0] else S - sum(arr)
    return min(a, b, c)


# print(dfs([]))


def dfs2(arr, x_cnt, y_cnt, z_cnt):
    global S, x, y, z
    # print(arr)
    if (S - sum(arr)) < min(x[0], y[0], z[0]):
        if (S - sum(arr)) == 1:
            print(arr)
        return S - sum(arr)  # remaining space
    a = dfs2(arr+x, x_cnt-1, y_cnt, z_cnt) if S - \
        sum(arr) >= x[0] and x_cnt > 0 else S - sum(arr)
    b = dfs2(arr+y, x_cnt, y_cnt-1, z_cnt) if S - \
        sum(arr) >= y[0] and y_cnt > 0 else S - sum(arr)
    c = dfs2(arr+z,  x_cnt, y_cnt, z_cnt-1) if S - \
        sum(arr) >= z[0] and z_cnt > 0 else S - sum(arr)
    return min(a, b, c)


print(dfs2([], 9, 9, 9))
