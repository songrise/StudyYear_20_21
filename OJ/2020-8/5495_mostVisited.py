class Solution:
    def mostVisited(self, n: int, rounds: List[int]) -> List[int]:
        cnt = [0 for _ in range(n)]
        crt = rounds[0]
        for i in range(1, len(rounds)):
            nxt = rounds[i]
            while crt < nxt:
                cnt[crt - 1] += 1
                crt += 1
                if crt == n+1:
                    crt = 1

        max_times = max(cnt)
        ans = []
        for i, v in enumerate(cnt):
            if v == max_times:
                ans.append(i+1)

        # print(ans)

        return ans
