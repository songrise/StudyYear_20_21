class Solution:

    def maxProductPath(self, grid: List[List[int]]) -> int:
        row = len(grid)
        col = len(grid[0])

        dp = [[0 for _ in range(col)]for __ in range(row)]
        for i in range(col-1):
            for j in range(row-1):
                if i == 0 and j == 0:
                    dp[0][0] = grid[0][0]
                    continue
                if i == 0:
                    dp[i][j] = dp[i][j-1]*grid[i][j]
                elif j == 0:
                    dp[i][j] = dp[i-1][j]*grid[i][j]
                else:
                    dp[i][j] = max(dp[i-1][j]*grid[i][j],
                                   dp[i][j-1]*grid[i][j])
        return dp[-1][-1] if dp[-1][-1]>= 0 else -1
