class Solution:
    def maxCoins(self, piles: List[int]) -> int:
        piles.sort()
        piles.reverse()
        ans = 0
        for i in range(len(piles)//3):
            if i*3+1 <= len(piles) -1:
                ans += piles[i*3+1]
        print(piles)
        return ans
