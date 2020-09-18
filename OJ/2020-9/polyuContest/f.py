def findAllPath(graph, start, end, path=[]):
    # path.append(start)
    path = path + [start]
    if start == end:
        return path

    paths = []  # 存储所有路径
    for node in graph[start]:
        if node not in path:
            newpaths = findAllPath(graph, node, end, path)
            for newpath in newpaths:
                paths.append(newpath)
    return paths


node, line = list(map(int, (input().split())))
target = node - 1

capacity = [[0 for _ in range(node)] for __ in range(node)]
for i in range(line):
    cur, nxt, degree = list(map(int, (input().split())))
    capacity[cur][nxt] = degree
    capacity[nxt][cur] = degree

queue = [0]
g = {}
for cur in range(node):
    nxt = []
    for i, v in enumerate(capacity[cur]):
        if v != 0:
            nxt.append(i)
        g[cur] = nxt


path = findAllPath(g, 0, target)+[0]
max_ = 100000000
dp = []
crt_max = max_
for i in range(1, len(path)):
    if path[i] != 0:
        crt_max = min(crt_max, capacity[path[i-1]][path[i]])
    else:
        dp.append(crt_max)
        crt_max = max_

print(max(dp))
