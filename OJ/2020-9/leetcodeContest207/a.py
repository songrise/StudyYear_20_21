class Solution:
    def reorderSpaces(self, text: str) -> str:
        word = 0
        new = 1
        subStr = ""
        words = []
        text+=" "
        for ch in text:
            if ch.isalpha():
                word += new
                new = 0
                subStr += ch
            if ch == " ":
                if subStr!="":
                    words.append(subStr)
                subStr = ""
                new = 1

        whiteLen = text.count(" ") -1
        if word >1:
            trail = whiteLen % (word-1)
            dilm = " "*((whiteLen)//(word-1))
        else:
            trail = whiteLen
            ans = words[0]+(" "*trail)

        ans = dilm.join(words) + (" "*trail)
        return ans
