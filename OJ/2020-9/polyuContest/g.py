def isValid(base: str, case: str):
    for i in range(26):
        temp = ""
        for j in range(len(base)):
            ch = ord(base[j])
            ch = chr(97 + ((ch-97+i) % 26))
            temp += ch
        if case == temp:
            return True
    return False


caseNum = int(input())
case = input().split(" ")

base = case[0]
dp = [0 for _ in range(caseNum)]
ans = []

for i in range(caseNum):
    if isValid(base, case[i]):
        dp[i] = 1


if all(dp):
    print("true")
else:
    for i, v in enumerate(dp):
        if not v:
            ans.append(i)
    ans = list(map(str, ans))
    print(" ".join(ans))
