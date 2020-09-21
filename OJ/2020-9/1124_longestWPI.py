class Solution:

    def longestWPI(self, hours: List[int]) -> int:
        def isvalid(arr, start, end):
            large_8 = 0
            for num in arr[start:end+1]:
                if num > 8:
                    large_8 += 1

            return 2*large_8 > end - start

        maxWPI = 0
        start = 0
        for i in range(len(hours)):
            if not isvalid(hours, start, i):
                maxWPI = max(maxWPI,i -start +1)
                start = i + 1

        return maxWPI
