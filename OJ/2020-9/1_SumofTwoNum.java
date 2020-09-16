import java.util.Arrays;

/**
 * -*- coding : utf-8 -*-
 * 
 * @FileName : 1_SumofTwoNum.java
 * @Author : Ruixiang JIANG (Songrise)
 * @Time : 2020-09-15
 * @Github ：https://github.com/songrise
 * @Descriptions: None
 **/

class Solution {
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[] { i, j };

                }
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }
}