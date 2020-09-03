class Solution:
    def findSmallestSetOfVertices(self, n: int, edges: List[List[int]]) -> List[int]:
        indeg = [0 for _ in range(n)]
        for pair in edges:
            indeg[pair[1]]+=1
        ans = []
        for i in range(n):
            if not indeg[i]:
                ans.append(i)
        return ans