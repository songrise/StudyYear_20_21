class Solution:
    def thousandSeparator(self, n: int) -> str:
        strint = str(n)
        len_ = len(strint)
        if len_ <= 3:
            return strint
        strint = strint[::-1]
        i = 0
        three_arr = []
        while (i+1)*3 <= len_ - 1:
            three_arr.append(strint[i*3:(i+1)*3])
            i += 1
        three_arr.append(strint[i*3:])
        ans = ".".join(three_arr)

        return ans[::-1]
