package Inheritance;

/**
 * -*- coding : utf-8 -*-
 * 
 * @FileName : B.java
 * @Author : Ruixiang JIANG (Songrise)
 * @Time : 2020-10-23
 * @Github ：https://github.com/songrise
 * @Descriptions: test static inheritance
 **/

class A {
    static private String information = "info in A";
}

public class B extends A {


    public static void main(String[] args) {
        System.out.println(information);
    }
}
