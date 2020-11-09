class Solution:
    def maxUniqueSplit(self, s: str) -> int:
        n = len(s)

        def dfs(i, res):
            if i == n:
                self.ans = max(self.ans, len(res))
            for j in range(i, n):
                tmp = s[i:j+1]
                if tmp not in res:
                    dfs(j+1, res | {tmp})
        self.ans = 0
        dfs(0, set())
        return self.ans
