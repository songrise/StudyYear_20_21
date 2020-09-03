# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    def sumNumbers(self, root: TreeNode) -> int:
        stack = [root]
        num = ''
        ans = 0
        while stack:
            crt = stack.pop(-1)
            if crt:
                num += str(crt.val) 
                stack.append(crt.left)
                stack.append(crt.right)
                if not (crt.left or crt.right):  # leaf
                    ans += int(num)
                    print(num)
            num  = num[:-1]

        return ans
