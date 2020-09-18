class Solution {
    public int maxSubArray(int[] nums) {
        int len = nums.length;
        if (len == 1) {
            return nums[0];
        }

        int sub = nums[0];
        int max = nums[0];
        for (int i = 1; i < len; i++) {
            sub = Math.max(nums[i]+sub,nums[i]);
            max = Math.max(max, sub);
        }
        return max;
    }
}