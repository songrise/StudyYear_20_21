
/**
 * -*- coding : utf-8 -*-
 * 
 * @FileName : ForEach.java
 * @Author : Ruixiang JIANG (Songrise)
 * @Time : 2020-09-14
 * @Github ：https://github.com/songrise
 * @Descriptions: None
 **/

// package songrise.practice;
import java.util.Arrays;

public class ForEach {
    public static void main(String[] args) {
        int[] a = { 6, 5, 4, 3, 2, 1 };
        int[] b = Arrays.copyOf(a, a.length);
        for (int num : a) {
            System.out.print(num);
        }
        for (int num : b) {
            System.out.print(num);
        }
    }
}
