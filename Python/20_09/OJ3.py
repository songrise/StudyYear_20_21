case = int(input())
ans = []
for _ in range(case):
    k, m = list(map(int, input().split(" ")))
    ans.append("Wuhoo" if ((k-1) % (m+1) != 0) else "Piiiiiiiiiiiiiii")

for i in range(case):
    print(ans[i])
