class Solution:
    def maxUniqueSplit(self, s: str) -> int:
        s2=s[:]

        s = "".join((lambda x: (x.sort(), x)[1])(list(s)))
        print(s)
        sub = []
        subStr = ""
        for i, ch in enumerate(s):
            subStr += ch
            if subStr not in sub:
                sub.append(subStr)
                subStr = ""

        sub2=[]
        subStr=""
        for i, ch in enumerate(s2):
            subStr += ch
            if subStr not in sub2:
                sub2.append(subStr)
                subStr = ""
        return (max(len(sub),len(sub2)))
