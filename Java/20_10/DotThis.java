/**
 * -*- coding : utf-8 -*-
 * 
 * @FileName : DotThis.java
 * @Author : Ruixiang JIANG (Songrise)
 * @Time : 2020-10-05
 * @Github ：https://github.com/songrise
 * @Descriptions: Test .this for inner class
 **/

public class DotThis {
    void f() {
        System.out.println("DotThis.f()");
    }

    /**
     * Inner
     */
    public class Inner {
        public DotThis outer() {
            return DotThis.this;
        }
    }

    public Inner inner() {
        return new Inner();
    }

    public static void main(String[] args) {
        DotThis dt = new DotThis();
        DotThis.Inner dti = dt.inner();
        dti.outer().f();
    }
}
