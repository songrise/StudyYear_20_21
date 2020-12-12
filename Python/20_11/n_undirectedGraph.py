import math


def nbrGraph(n: int) -> int:
    """
    Count the number of undirected connected graphs that can be formed by n nodes:
    """
    dp = [1 for _ in range(n+1)]
    for i in range(1, len(dp)):
        dp[i] = dp[i-1] * factorial(i)
    print(dp)
    return dp[-1]


def factorial(n):
    if n == 1:
        return 1
    return n*factorial(n-1)


if __name__ == "__main__":
    print(nbrGraph(6))
