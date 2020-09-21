/**
* -*- coding : utf-8 -*-
* @FileName  : package.java
* @Author    : Ruixiang JIANG (Songrise)
* @Time      : 2020-09-15
* @Github    ：https://github.com/songrise
* @Descriptions: None
**/

package packageTest;

class Apackage {

    private int i = 0;
    public int j = 0;

    Apackage() {
        this.i = 1;
        this.j = 2;
    }

    void show() {
        System.out.printf("i = %d j = %d", this.i, this.j);
    }
}
