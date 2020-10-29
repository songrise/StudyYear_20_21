/**
 * -*- coding : utf-8 -*-
 * 
 * @FileName : CtorSequence.java
 * @Author : Ruixiang JIANG (Songrise)
 * @Time : 2020-10-23
 * @Github ：https://github.com/songrise
 * @Descriptions: demonstrate the sequence of constructors
 **/

package Inheritance;

class Art {
    Art() {
        System.out.println("Art constructor");
    }
}

class Drawing extends Art {
    Drawing() {
        System.out.println("Drawing constructor");

    }
}

public class Cartoon extends Drawing {
    Cartoon() {
        System.out.println("Cartoon constructor");
    }

    public static void main(String[] args) {
        new Cartoon();
    }
}
