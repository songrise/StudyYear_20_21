class Solution:
    def listnorcount(self, a, b):
        c = 0
        for i in range(len(a)):
            if a[i] == 0 and b[i]:
                c += 1

        return c

    def listand(self, a, b):
        return[1 for i in range(len(a)) if a[i] or b[i]]

    def findSmallestSetOfVertices(self, n: int, edges: List[List[int]]) -> List[int]:
        visible = [[0 for _ in range(n)] for __ in range(n)]
        for entry in edges:
            visible[entry[0]][entry[1]] = 1

        for i in range(n):
            crt = i
            stack = [crt]
            while stack:
                crt = stack.pop()
                for j in range(n):  # dfs
                    if visible[crt][j]:
                        stack.append(j)
                        visible[i][j] = 1
        for i in range(n):
            visible[i][i] = 1
        print(visible)

        vis = [0 for i in range(n)]
        vis_nodes = [sum(visible[i]) for i in range(n)]
        ans = [sum(visible[i]) for i in range(n)]
        stack = []
        vis_nodes[vis_nodes.index(max(vis_nodes))] = 0

        while not all(vis):
            nxt = vis_nodes.index(max(vis_nodes))
            for j in range(n):
                if vis_nodes[j] == vis_nodes[nxt]:
                    stack.append(j)
            max_i = stack[0]
            while stack:
                t = stack.pop()
                if self.listnorcount(vis, visible[t]) > self.listnorcount(vis, visible[max_i]):
                    max_i = t
            ans.append(max_i)
            vis = self.listand(vis, visible[max_i])

        ans.sort()
        return ans
