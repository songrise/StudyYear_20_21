# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def sumRootToLeaf(self, root: TreeNode) -> int:
        ans = 0
        stack = [root]
        while stack:
            crtNode = stack.pop(-1)
            if crtNode.left:
                crtNode.left.val = str(crtNode.val)+str(crtNode.left.val)
                stack.append(crtNode.left)
            if crtNode.right:
                crtNode.right.val = str(crtNode.val)+str(crtNode.right.val)
                stack.append(crtNode.right)
            if not (crtNode.left or crtNode.right):
                ans += int(str(crtNode.val), 2)

        return ans
