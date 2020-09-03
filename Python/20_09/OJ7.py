case = int(input())
attributes = [[0, 0, 0] for _ in range(case)]
for i in range(case):
    attributes[i] = list(map(int, input().split(" ")))

attributes.sort(reverse = True)

bestIndex = 0
ans = 0
for i, v in enumerate(attributes):
    noBetter = 1
    if attributes[bestIndex][0] > v[0] and attributes[bestIndex][1] > v[1] and attributes[bestIndex][2] > v[2]:
        noBetter = 0
    else:
        for k, e in enumerate(attributes):
            if e[0] > v[0] and e[1] > v[1] and e[2] > v[2]:
                bestIndex = k
                noBetter = 0
    ans += noBetter

print(ans)
