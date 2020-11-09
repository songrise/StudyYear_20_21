import heapq


def dj(adj: list, src: int, end: int) -> list:
    """heap dijkstra"""
    visited = [0 for _ in range(len(adj))]
    queue = []
    ans = []
    for i in range(len(adj)):
        heapq.heappush(queue, (adj[src][i], i))

    while queue:
        min_ = queue.pop(0)
        visited[] = 1
        
    return ans
